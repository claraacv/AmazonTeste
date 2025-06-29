// src/main/java/controllers/CompraController.java
package controllers;

import models.*;
import enums.StatusCompra;

public class CompraController {

    public boolean iniciarCompra(Cliente cliente, CarrinhoCompras carrinho) {
        if (cliente == null || !cliente.validarLogin(cliente.getEmailTelefone(), cliente.getSenha())) {
            return false;
        }
        if (carrinho == null || carrinho.getItens().isEmpty()) return false;

        for (Produto p : carrinho.getItens().keySet()) {
            if (p.getQuantidadeEstoque() <= 0) return false;
        }
        return true;
    }

    public StatusCompra finalizarCompra(Cliente cliente, CarrinhoCompras carrinho,
                                        String metodoPagamento, EnderecoEntrega endereco) {
        boolean dadosValidos = metodoPagamento != null && !metodoPagamento.isBlank()
                && endereco != null;

        boolean compraPodeContinuar = iniciarCompra(cliente, carrinho);

        if (compraPodeContinuar && dadosValidos) {
            return StatusCompra.SUCESSO;
        } else {
            return StatusCompra.FALHA_ENDERECO_OU_PAGAMENTO;
        }
    }
}
