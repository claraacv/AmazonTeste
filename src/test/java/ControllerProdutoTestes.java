import controllers.ProdutoController;
import models.Produto;
import org.junit.jupiter.api.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ControllerProdutoTestes {

    private ProdutoController controller;
    private List<Produto> base;

    @BeforeEach
    void setup() {
        controller = new ProdutoController();
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
}
