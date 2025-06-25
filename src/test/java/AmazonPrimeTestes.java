import controllers.ClienteController;
import enums.Planos;
import models.*;
import org.junit.Test;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.*;

public class AmazonPrimeTestes {
    @Test
    public void compraFreteGratis(){
        //arrange
        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");
        AmazonPrime amazon = new AmazonPrime(Planos.MENSAL, 24.90);

        cliente.setAmazonPrime(amazon);
        cliente.inscreverPrime(LocalDate.parse("2025-05-22"));

        Compra compra = new Compra(cliente, LocalDate.now());

        //act
        boolean resultado = compra.validarFreteGratis();

        //assert
        assertTrue(resultado);
    }

    @Test
    public void compraSemFreteGratis(){
        //arrange
        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");
        AmazonPrime amazon = new AmazonPrime(Planos.MENSAL, 24.90);

        cliente.setAmazonPrime(amazon);
        cliente.inscreverPrime(LocalDate.parse("2025-03-22"));
        cliente.cancelarPrime(LocalDate.parse("2025-05-22"));

        Compra compra = new Compra(cliente, LocalDate.now());

        //act
        boolean resultado = compra.validarFreteGratis();

        //assert
        assertFalse(resultado);
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
