package controllers;

import models.Avaliacao;

public class AvaliacaoController {
    public boolean validarAvaliacao(Avaliacao avaliacao) {
        if(avaliacao.getComentario() == null || avaliacao.getComentario().isEmpty()){
            return false;
        }
        return true;
    }
}
