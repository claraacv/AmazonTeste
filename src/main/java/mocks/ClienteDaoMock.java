package mocks;

import models.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoMock {

    public Cliente buscarPorEmail(String email) {
        if (email.equals("thiago@gmail.com")) {
            Cliente cliente = new Cliente(email);
            cliente.setNome("Thiago");
            cliente.setSenha("senhaCorreta123");
            cliente.setSegundaSenha("senhaCorreta123");
            return cliente;
        }
        return null;
    }

    public boolean salvar(Cliente cliente) {
        return true;
    }

    public List<Cliente> listar() {
        return new ArrayList<>();
    }

    public Cliente getClienteById(long id) {
        return null;
    }
}
