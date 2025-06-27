package controllers;

import models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoController {

    /**
     * RT18 · filtrar por categoria
     */
    public List<Produto> filtrarPorCategoria(List<Produto> todos, String categoria) {
        if (todos == null || categoria == null) {
            return List.of();
        }
        return todos.stream()
                .filter(p -> categoria.equals(p.getCategoria()))
                .collect(Collectors.toList());
    }


    // RT18 · filtrar por faixa de preço [min, max]

    public List<Produto> filtrarPorPreco(List<Produto> todos, double min, double max) {
        if (todos == null) {
            return List.of();
        }
        return todos.stream()
                .filter(p -> p.getValor() >= min && p.getValor() <= max)
                .collect(Collectors.toList());
    }
}
