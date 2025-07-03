package controllers;

import daos.ClienteDao;
import models.Cliente;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    private ClienteDao dao;

    public ClienteController(){
        dao = new ClienteDao();
    }
    public boolean validarEmailTelefone(String emailTelefone){
        if(emailTelefone.length()==11 || emailTelefone.contains("@")){
            return true;
        } else{
            return false;
        }
    }

    public ClienteController(ClienteDao dao) {
        this.dao = dao;
    }

    public boolean salvar(Cliente cliente) {
        String emailTelefone = cliente.getEmailTelefone();
        String nome = cliente.getNome();
        String senha = cliente.getSenha();
        String segundaSenha = cliente.getSegundaSenha();

        if (!validarEmailTelefone(emailTelefone)) {
            System.out.println("Email inválido: " + emailTelefone);
            return false;
        }

        if (nome == null || nome.isEmpty()) {
            System.out.println("Nome vazio");
            return false;
        }
        if (senha == null || senha.isEmpty()) {
            System.out.println("Senha vazia");
            return false;
        }
        if (segundaSenha == null || segundaSenha.isEmpty()) {
            System.out.println("Segunda senha vazia");
            return false;
        }

        if (!senha.equals(segundaSenha)) {
            System.out.println("Senhas diferentes");
            return false;
        }

        return dao.salvar(cliente);
    }

    public Cliente getClienteById(long id) {
        return dao.getClienteById(id);
    }

    public List<Cliente> listar() {
        return dao.listar();
    }


}
