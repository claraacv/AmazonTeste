package models;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CarrinhoTestes {

    @Test
    void adicionaPrimeiroProduto() {
        Produto camisa = new Produto("Camisa", 200.0, 1, "Roupas");
        CarrinhoCompras carrinho = new CarrinhoCompras();

        assertTrue(carrinho.adicionarProduto(camisa));
        assertEquals(1, carrinho.getQuantidade(camisa));
        assertEquals(200.0, carrinho.getValorTotal());
    }

    @Test
    void atualizaQuantidadeEValor() {
        Produto camisa = new Produto("Camisa", 200.0, 1, "Roupas");
        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarProduto(camisa);

        assertTrue(carrinho.adicionarProduto(camisa));
        assertEquals(2, carrinho.getQuantidade(camisa));
        assertEquals(400.0, carrinho.getValorTotal());
    }

    @Test
    void removeProdutoELimpaCarrinho() {
        Produto computador = new Produto("Computador", 10.0, 1, "Eletrônicos");
        CarrinhoCompras carrinho = new CarrinhoCompras();
        carrinho.adicionarProduto(computador);

        assertTrue(carrinho.removerProduto(computador));
        assertEquals(0, carrinho.getQuantidade(computador));
        assertEquals(0.0, carrinho.getValorTotal());
    }
}
