import models.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class MissaoTestes {
    @Test
    public void missaoExpirada(){
        //arrange
        Missao missao = new Missao();
        missao.setCredito(20);
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataUsada = dataAtual.minusDays(8);
        missao.setData(dataUsada);
        missao.setNome("Compre na loja de Cuidados Pessoais e da Casa e ganhe R$20 em créditos");

        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");

        Produto produto = new Produto("Malibu Renasce",45.90,100);

        Produto produto2 = new Produto("O Pequeno Príncipe",20.90,100);

        ItemCompra item = new ItemCompra(produto, 1);
        ItemCompra item2 = new ItemCompra(produto2, 3);

        Compra compra = new Compra(cliente, LocalDate.now());

        //act
        int resultado = compra.finalizarVenda();

        //assert
        assertEquals(0, resultado);
    }

    @Test
    public void missaoAtiva(){
        //arrange
        Missao missao = new Missao();
        missao.setCredito(20);
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataUsada = dataAtual.minusDays(7);
        missao.setData(dataUsada);
        missao.setNome("Compre na loja de Cuidados Pessoais e da Casa e ganhe R$20 em créditos");

        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");

        Produto produto = new Produto("Malibu Renasce",45.90,100);

        Produto produto2 = new Produto("O Pequeno Príncipe",20.90,100);

        ItemCompra item = new ItemCompra(produto, 1);
        ItemCompra item2 = new ItemCompra(produto2, 3);

        Compra compra = new Compra(cliente, LocalDate.now());

        //act
        int resultado = compra.finalizarVenda();

        //assert
        assertEquals(20, resultado);
    }

    @Test
    public void clienteNaoInscrito(){
        //arrange
        Missao missao = new Missao();
        missao.setCredito(20);
        LocalDate dataAtual = LocalDate.now();
        LocalDate dataUsada = dataAtual.plusDays(1);
        missao.setData(dataUsada);
        missao.setNome("Compre na loja de Cuidados Pessoais e da Casa e ganhe R$20 em créditos");

        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");

        Produto produto = new Produto("Malibu Renasce",45.90,100);

        Produto produto2 = new Produto("O Pequeno Príncipe",20.90,100);

        ItemCompra item = new ItemCompra(produto, 1);
        ItemCompra item2 = new ItemCompra(produto2, 3);

        Compra compra = new Compra(cliente, LocalDate.now());

        //act
        int resultado = compra.finalizarVenda();

        //assert
        assertEquals(0, resultado);
    }
}
