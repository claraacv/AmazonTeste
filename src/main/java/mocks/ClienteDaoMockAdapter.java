package mocks;

import daos.ClienteDao;
import models.Cliente;

import java.util.List;

public class ClienteDaoMockAdapter extends ClienteDao {

    public ClienteDaoMockAdapter() {
        this.emf = null;
        this.em = null;
    }

    @Override
    public Cliente buscarPorEmail(String email) {
        if (email.equals("thiago@gmail.com")) {
            Cliente c = new Cliente(email);
            c.setNome("Thiago");
            c.setSenha("123");
            c.setSegundaSenha("123");
            return c;
        }
        return null;
    }

    @Override
    public boolean salvar(Cliente c) {
        return true;
    }
}