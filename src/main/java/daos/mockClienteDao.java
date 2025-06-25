package daos;

import models.Cliente;

import java.util.ArrayList;
import java.util.List;

public class mockClienteDao {

    public List<Cliente> getClientes(){
        List<Cliente> clientes = new ArrayList<>();
        clientes.add(new Cliente("ana@gmail.com"));
        clientes.add(new Cliente("udesc@gmail.com"));
        Cliente cliente = new Cliente("clara@gmail.com");
        cliente.setId(1);
        clientes.add(cliente);

        return clientes;
    }
}
