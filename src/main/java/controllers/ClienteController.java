package controllers;

import models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    public boolean validarEmailTelefone(String emailTelefone){
        if(emailTelefone.length()==11){
            return true;
        } else{
            return false;
        }
    }

    public boolean salvar(Cliente cliente) {
        return true;
    }

    public Cliente getClienteById(int i) {
        return new Cliente("udesc@gmail.com");
    }

    public List<Cliente> listar() {
        List<Cliente> clientes = new ArrayList<>();
        return clientes;
    }
}
