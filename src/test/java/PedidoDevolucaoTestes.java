import models.PedidoDevolucao;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class PedidoDevolucaoTestes {

    @Test
    public void devePermitirDevolucaoDentroDoPrazo(){
        //Arrange
        PedidoDevolucao pedidoDevolucao = new PedidoDevolucao(1, LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 25), "Fone Bluetooth", "Produto com Defeito", "Devolução em Agência dos Correios", LocalDate.of(2025, 5, 28), "Entregue");

        //Act
        boolean resultado = pedidoDevolucao.devolverPedido();

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void naoDevePermitirDevolucaoForaDoPrazo(){
        //Arrange
        PedidoDevolucao pedidoDevolucao = new PedidoDevolucao(1, LocalDate.of(2025, 5, 20), LocalDate.of(2025, 5, 25), "Fone Bluetooth", "Produto com Defeito", "Devolução em Agência dos Correios", LocalDate.of(2025, 6, 25), "Entregue");

        //Act
        boolean resultado = pedidoDevolucao.devolverPedido();

        //Assert
        assertFalse(resultado);
    }

    @Test
    public void naoDevePermitirDevolucaoDeProdutoNaoEntregue(){
        //Arrange
        PedidoDevolucao pedidoDevolucao = new PedidoDevolucao(2, LocalDate.of(2025, 5, 20), null, "Tênis Nike", "Demora na Entrega", "Devolução em Agência dos Correios", LocalDate.of(2025, 5, 23), "Em Entrega");

        //Act
        boolean resultado = pedidoDevolucao.devolverPedido();

        //Assert
        assertFalse(resultado);
    }
}
