import models.*;
import org.junit.Test;

import java.time.LocalDate;

import static java.time.LocalDate.now;
import static org.junit.Assert.assertEquals;

public class MissaoTestes {
    @Test
    public void missaoExpirada(){
        //arrange
        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");

        Missao missao = new Missao();
        missao.setCredito(20);
        missao.setNome("Compre na loja de Cuidados Pessoais e da Casa e ganhe R$20 em créditos");

        //act
        cliente.cadastrarMissao(missao);

        Compra compra = new Compra(cliente, cliente.getDataMissao().plusDays(8));

        double resultado = compra.finalizarVenda();
        //assert
        assertEquals(0, resultado, 0.00001);
    }

    @Test
    public void missaoAtiva(){
        //arrange
        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");

        Missao missao = new Missao();
        missao.setCredito(20.0);
        missao.setNome("Compre na loja de Cuidados Pessoais e da Casa e ganhe R$20 em créditos");

        //act
        cliente.cadastrarMissao(missao);

        Compra compra = new Compra(cliente, cliente.getDataMissao().plusDays(7));
        double resultado = compra.finalizarVenda();

        //assert
        assertEquals(20, resultado, 0.00001);
    }

    @Test
    public void clienteNaoInscrito(){
        //arrange
        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setNome("Ana Clara");
        cliente.setSenha("123");

        Missao missao = new Missao();
        missao.setCredito(20.0);
        missao.setNome("Compre na loja de Cuidados Pessoais e da Casa e ganhe R$20 em créditos");

        //act
        cliente.cadastrarMissao(missao);

        Compra compra = new Compra(cliente, cliente.getDataMissao().minusDays(1));

        double resultado = compra.finalizarVenda();

        //assert
        assertEquals(0, resultado, 0.00001);
    }
}
