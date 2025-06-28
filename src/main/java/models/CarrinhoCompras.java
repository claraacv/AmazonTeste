package models;

import java.util.HashMap;
import java.util.Map;

public class CarrinhoCompras {
    private final Map<Produto, Integer> itens = new HashMap<>();
    private double valorTotal = 0.0;

    /**
     * RT15 – adiciona 1 unidade do produto no carrinho.
     *     */
    public boolean adicionarProduto(Produto produto) {
        if (produto == null) {
            return false;
        }
        itens.merge(produto, 1, Integer::sum);
        valorTotal += produto.getValor();
        return true;
    }

    public int getQuantidade(Produto produto) {
        return itens.getOrDefault(produto, 0);
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public boolean removerProduto(Produto produto) {
        if (produto == null || !itens.containsKey(produto)) {
            return false;
        }
        int qtdAtual = itens.get(produto);
        if (qtdAtual > 1) {
            itens.put(produto, qtdAtual - 1);
        } else {
            itens.remove(produto);
        }
        valorTotal -= produto.getValor();
        return true;
    }

    public Map<Produto, Integer> getItens() {
        return java.util.Collections.unmodifiableMap(itens);
    }

}
