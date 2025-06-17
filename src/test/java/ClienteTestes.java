import controllers.ClienteController;
import models.Cliente;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ClienteTestes {
    @Test
    public void emailTelefoneInvalidoAbaixo(){
        //arrange
        Cliente cliente = new Cliente("4799987086");
        ClienteController controller = new ClienteController();
        String emailTelefone = cliente.getEmailTelefone();

        //act
        boolean resultado = controller.validarEmailTelefone(emailTelefone);

        //assert
        assertFalse(resultado);
    }

    @Test
    public void emailTelefoneValido(){
        //arrange
        Cliente cliente = new Cliente("47999870867");
        ClienteController controller = new ClienteController();
        String emailTelefone = cliente.getEmailTelefone();

        //act
        boolean resultado = controller.validarEmailTelefone(emailTelefone);

        //assert
        assertTrue(resultado);
    }

    @Test
    public void emailTelefoneInvalidoAcima(){
        //arrange
        Cliente cliente = new Cliente("479998708679");
        ClienteController controller = new ClienteController();
        String emailTelefone = cliente.getEmailTelefone();

        //act
        boolean resultado = controller.validarEmailTelefone(emailTelefone);

        //assert
        assertFalse(resultado);
    }

    @Test
    public void salvarCliente(){
        //arrange
        Cliente cliente = new Cliente("udesc@gmail.com");
        cliente.setNome("Udesc");
        cliente.setSenha("udesc123");
        cliente.setSegundaSenha("udesc123");

        ClienteController controller = new ClienteController();

        //act
        boolean resultado = controller.salvar(cliente);
        List<Cliente> clientes = controller.listar();

        //assert
        assertTrue(resultado);
        //ver um assert pra verificar o valor que retornou da lista
        assertEquals(1,clientes.size());
    }

    @Test
    public void naoSalvarCliente(){
        //arrange
        Cliente cliente = new Cliente("udesc@gmail.com");
        cliente.setSenha("udesc123");
        cliente.setSegundaSenha("udesc123");

        ClienteController controller = new ClienteController();

        //act
        boolean resultado = controller.salvar(cliente);
        List<Cliente> clientes = controller.listar();

        //assert
        assertFalse(resultado);
        //ver um assert pra verificar o valor que retornou da lista
        assertEquals(1,clientes.size());
    }
}
