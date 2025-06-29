
package controllers;

import enums.StatusCompra;
import models.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class CompraControllerTestes {

    // CT 35 - JULIA
    @Test
    public void clienteAutenticadoECarrinhoComEstoque_devePermitirCompra() {
        Cliente cliente = new Cliente("julia@teste.com");
        cliente.setSenha("123");

        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarProduto(new Produto("Echo", 200, 2, "Eletrônicos"));

        CompraController controller = new CompraController();

        assertTrue(controller.iniciarCompra(cliente, carrinho));
    }

    //CT 36 - JULIA
    @Test
    public void clienteAutenticadoComCarrinhoVazio_deveNegarCompra() {
        Cliente cliente = new Cliente("julia@teste.com");
        cliente.setSenha("123");

        CarrinhoCompras carrinho = new CarrinhoCompras();

        CompraController controller = new CompraController();

        assertFalse(controller.iniciarCompra(cliente, carrinho));
    }

    //CT 37 - JULIA
    @Test
    public void clienteAutenticadoComProdutoSemEstoque_deveNegarCompra() {
        Cliente cliente = new Cliente("julia@teste.com");
        cliente.setSenha("123");

        Produto produto = new Produto("Kindle", 400, 0, "Leitura");
        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarProduto(produto);

        CompraController controller = new CompraController();

        assertFalse(controller.iniciarCompra(cliente, carrinho));
    }

    @Test
    //CT 38 - JULIA
    void finalizarCompraComDadosValidos_deveRetornarSucesso() {
        // Cliente autenticado
        Cliente cliente = new Cliente("julia@teste.com");
        cliente.setSenha("123"); // validado via validarLogin()

        // Carrinho com item válido e estoque disponível
        CarrinhoCompras carrinho = new CarrinhoCompras();
        Produto produto = new Produto("Produto A", 120.0, 5, "Geral");
        carrinho.adicionarProduto(produto);

        // Método de pagamento
        String metodoPagamento = "Cartão de Crédito";

        // Endereço (esse é uma simulação)
        EnderecoEntrega endereco = new EnderecoEntrega(
                "Brasil", "Julia P", 999999999, 88000000,
                "Rua A", 123, "", "Centro"
        );

        // Executar compra
        CompraController controller = new CompraController();
        StatusCompra status = controller.finalizarCompra(cliente, carrinho, metodoPagamento, endereco);

        // Assertt
        assertEquals(StatusCompra.SUCESSO, status);
    }
}
