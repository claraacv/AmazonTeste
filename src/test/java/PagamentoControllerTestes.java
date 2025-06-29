import controllers.PagamentoController;
import jakarta.persistence.*;
import models.MetodoPagamento;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

public class PagamentoControllerTestes {

    private PagamentoController controller;
    private EntityManagerFactory emf;

    @BeforeEach
    void setup() {
        controller = new PagamentoController();
        emf = Persistence.createEntityManagerFactory("AmazonPU");
    }

    @AfterEach
    void limpar() {
        emf.close();
    }

    // CT41 - JULIA
    @Test
    public void deveCadastrarMetodoPagamentoValido() {
        MetodoPagamento mp = new MetodoPagamento(
                "Cartão de Crédito",
                "4111111111111111",
                "Julia Pavanello",
                "12/2027",
                "123"
        );

        boolean sucesso = controller.cadastrar(mp);
        assertTrue(sucesso, "Deve retornar true para cadastro válido");

        EntityManager em = emf.createEntityManager();
        MetodoPagamento encontrado = em.find(MetodoPagamento.class, mp.getId());
        em.close();

        assertNotNull(encontrado, "Método de pagamento deve estar no banco");
        assertEquals("Julia Pavanello", encontrado.getNomeTitular());
    }

    // CT42 - Julia
    @Test
    public void naoDeveCadastrarCartaoComValidadeVencida() {
        MetodoPagamento mp = new MetodoPagamento(
                "Cartão de Crédito",
                "4111111111111111",
                "Julia Pavanello",
                "12/2023",
                "789"
        );

        boolean sucesso = controller.cadastrar(mp);
        assertFalse(sucesso, "Não deve cadastrar cartão vencido");

        EntityManager em = emf.createEntityManager();
        MetodoPagamento encontrado = em.find(MetodoPagamento.class, mp.getNumeroCartao());
        em.close();

        assertNull(encontrado, "Cartão vencido não deve ser salvo no banco");
    }
}
