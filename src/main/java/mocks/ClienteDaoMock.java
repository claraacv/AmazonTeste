package mocks;

import daos.ClienteDao;
import models.Cliente;

public class ClienteDaoMock extends ClienteDao {

    @Override
    public Cliente buscarPorEmail(String email) {
        Cliente cliente = new Cliente(email);
        cliente.setNome("Thiago");
        cliente.setSenha("senhaCorreta123");
        cliente.setSegundaSenha("senhaCorreta123");
        return cliente;
    }
}
