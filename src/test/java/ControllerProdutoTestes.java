import controllers.ProdutoController;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Produto;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ControllerProdutoTestes {

    private ProdutoController controller;
    private List<Produto> base;
    private EntityManagerFactory emf;

    @BeforeAll
    public void setupAll() {
        emf = Persistence.createEntityManagerFactory("AmazonPU");
        controller = new ProdutoController();
    }

    @AfterAll
    public void tearDownAll() {
        if (emf != null) {
            emf.close();
        }
    }

    @BeforeEach
    void setup() {
        base = List.of(
                new Produto("TV",      300.0,  5, "Eletrônicos"),
                new Produto("Celular", 600.0, 10, "Eletrônicos"),
                new Produto("Camisa",  450.0, 20, "Roupas")
        );
    }

    //CT32 - JULIA
    @Test
    public void filtraPorCategoria() {
        List<Produto> filtrados = controller.filtrarPorCategoria(base, "Eletrônicos");

        assertEquals(2, filtrados.size(), "Deve retornar  2 produtos");
        assertTrue(filtrados.stream().allMatch(p -> "Eletrônicos".equals(p.getCategoria())),
                "Todos os produtos devem pertencer à categoria Eletrônicos");
    }

    //ct33 - julia
    @Test
    public void filtraPorPreco() {
        List<Produto> filtrados = controller.filtrarPorPreco(base, 300.0, 550.0);

        assertEquals(2, filtrados.size());

        for (Produto p : filtrados) {
            assertTrue(p.getValor() >= 300.0 && p.getValor() <= 550.0);
        }
    }

    //  Testes de integração com banco de dados

    @Test
    //CT - julia
    public void testeCadastrarProduto() {
        Produto produto = new Produto("Teclado Mecânico", 400.0, 10, "Periféricos");

        boolean sucesso = controller.cadastrarProduto(produto);
        assertTrue(sucesso, "Deve cadastrar produto com sucesso");

        EntityManager em = emf.createEntityManager();
        Produto encontrado = em.find(Produto.class, produto.getId());
        em.close();

        assertNotNull(encontrado, "Produto deve estar no banco");
        assertEquals("Teclado Mecânico", encontrado.getNome());
    }

    @Test
    //CT - Julia
    public void testeAtualizarProduto() {
        Produto produto = new Produto("Teclado Mecânico", 400.0, 10, "Periféricos");
        controller.cadastrarProduto(produto);

        produto.setCategoria("Eletrônicos");
        produto.setValor(550.0);

        boolean sucesso = controller.atualizarProduto(produto);
        assertTrue(sucesso, "Deve atualizar produto com sucesso");

        EntityManager em = emf.createEntityManager();
        Produto atualizado = em.find(Produto.class, produto.getId());
        em.close();

        assertEquals("Eletrônicos", atualizado.getCategoria());
        assertEquals(550.0, atualizado.getValor());
    }

    @Test
    //CT - Julia
    public void testeRemoverProduto() {
        Produto produto = new Produto("Teclado Mecânico", 400.0, 10, "Periféricos");
        controller.cadastrarProduto(produto);

        boolean sucesso = controller.removerProduto(produto.getId());
        assertTrue(sucesso, "Deve remover produto com sucesso");

        EntityManager em = emf.createEntityManager();
        Produto removido = em.find(Produto.class, produto.getId());
        em.close();

        assertNull(removido, "Produto não deve mais existir no banco");
    }
}
