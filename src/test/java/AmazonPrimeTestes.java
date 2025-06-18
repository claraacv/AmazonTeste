import controllers.ClienteController;
import models.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AmazonPrimeTestes {
    @Test
    public void compraFreteGratis(){
        //arrange
        ClienteController controllerCliente = new ClienteController();
        Cliente cliente = controllerCliente.getClienteById(1);
        Compra compra = new Compra(cliente, LocalDate.now());
        Produto produto = new Produto("Mochila", 150.99, 50);
        ItemCompra item = new ItemCompra(produto, 1);
        AmazonPrime amazon = new AmazonPrime("22/05/2025", cliente);

        //arrange
        boolean resultado = compra.validarFreteGratis();

        //assert
        assertTrue(resultado);
    }

    @Test
    public void compraSemFreteGratis(){
        //arrange
        ClienteController controllerCliente = new ClienteController();
        Cliente cliente = controllerCliente.getClienteById(1);
        Compra compra = new Compra(cliente, LocalDate.now());
        Produto produto = new Produto("Mochila", 150.99, 50);
        ItemCompra item = new ItemCompra(produto, 1);
        AmazonPrime amazon = new AmazonPrime("22/03/2025", cliente);

        //act
        amazon.cancelar("22/05/2025");
        boolean resultado = compra.validarFreteGratis();

        //assert
        assertTrue(resultado);
    }

    @Test
    public void assinarAmazonPrimeComCartaoValido(){

        //Arrange
        Cliente cliente = new Cliente();
        AmazonPrime amazonPrime = new AmazonPrime("Cartão de Crédito", cliente);
        String tipoPagamento = "Cartão de Crédito";
        String numeroCartao = "1234567890123456";
        String nomeCartao = "Thiago Rafael Schulze";
        String dataExpiracao = "10/2029";
        int codigoSeguranca = 777;

        //Act
        String resultado = amazonPrime.validarCartao(tipoPagamento, numeroCartao, nomeCartao, dataExpiracao, codigoSeguranca);

        //Assert
        assertEquals("Assinatura Realizada com Sucesso!", resultado);
    }

    @Test
    public void assinarAmazonPrimeComCartaoExpirado(){

        //Arrange
        Cliente cliente = new Cliente();
        AmazonPrime amazonPrime = new AmazonPrime("Cartão de Crédito", cliente);
        String tipoPagamento = "Cartão de Crédito";
        String numeroCartao = "1234567890123456";
        String nomeCartao = "Thiago Rafael Schulze";
        LocalDate mesAnterior = LocalDate.now().minusMonths(1);
        String dataExpiracao = mesAnterior.format(DateTimeFormatter.ofPattern("MM/yyyy"));
        int codigoSeguranca = 777;

        //Act
        String resultado = amazonPrime.validarCartao(tipoPagamento, numeroCartao, nomeCartao, dataExpiracao, codigoSeguranca);

        //Assert
        assertEquals("Pagamento não Autorizado! Verifique os dados de seu cartão.", resultado);
    }

    @Test
    public void assinarAmazonPrimeComCodigoDeSegurancaInvalido(){

        //Arrange
        Cliente cliente = new Cliente();
        AmazonPrime amazonPrime = new AmazonPrime("Cartão de Crédito", cliente);
        String tipoPagamento = "Cartão de Crédito";
        String numeroCartao = "1234567890123456";
        String nomeCartao = "Thiago Rafael Schulze";
        String dataExpiracao = "10/2029";
        int codigoSeguranca = 1000;

        //Act
        String resultado = amazonPrime.validarCartao(tipoPagamento, numeroCartao, nomeCartao, dataExpiracao, codigoSeguranca);

        //Assert
        assertEquals("Pagamento não Autorizado! Verifique os dados de seu cartão.", resultado);
    }


}
