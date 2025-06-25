package controllers;

import mocks.ClienteDaoMock;
import models.Cliente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class ClienteControllerMock {

    private ClienteDaoMock dao;

    public ClienteControllerMock(ClienteDaoMock dao) {
        this.dao = dao;
    }

    public String recuperarSenha(String email, LocalDate dataSolicitacao, LocalDate dataRecuperacao){
        Cliente cliente = dao.buscarPorEmail(email);
        if (cliente == null){
            return "Usuário não encontrado.";
        }

        long dias = ChronoUnit.DAYS.between(dataSolicitacao, dataRecuperacao);
        if (dias > 1) {
            return "Recuperação de senha expirada";
        }

        return "Senha recuperada com sucesso.";
    }
}