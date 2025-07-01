import controllers.ClienteController;
import controllers.ClienteControllerMock;
import mocks.ClienteDaoMock;
import mocks.ClienteDaoMockAdapter;
import models.Cliente;
import org.junit.Test;

import java.time.LocalDate;
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
        assertFalse("Telefone inválido",resultado);
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
        assertTrue("Telefone válido",resultado);
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
        assertFalse("Telefone inválido",resultado);
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
        Long id = cliente.getId();

        //assert
        assertEquals(cliente, controller.getClienteById(id));

        assertTrue("Cliente salvo",resultado);

        assertEquals(1,clientes.size());
    }

    @Test
    public void naoSalvarCliente(){
        //arrange
        Cliente cliente = new Cliente("udesc@gmail.com");
        cliente.setNome("Udesc");
        cliente.setSenha("udesc123");
        cliente.setSegundaSenha("udesc12");

        ClienteController controller = new ClienteController();

        //act
        boolean resultado = controller.salvar(cliente);
        List<Cliente> clientes = controller.listar();

        //assert
        assertFalse("Cliente não salvo", resultado);

        assertNull(cliente.getId());
        //ver um assert pra verificar o valor que retornou da lista
        assertEquals(1,clientes.size());
    }

    @Test
    public void validarLoginComSucesso(){

        //Arrange
        Cliente cliente = new Cliente();

        //Act
        boolean resultado = cliente.validarLogin("thiago@gmail.com", "senhaCorreta123");

        // Assert
        assertTrue("Login realizado com sucesso!", resultado);
    }

    @Test
    public void validarLoginComEmailInvalido(){

        //Arrange
        Cliente cliente = new Cliente();

        //Act
        boolean resultado = cliente.validarLogin("thiagogmail.com", "senhaCorreta123");

        // Assert
        assertFalse("Login realizado com sucesso!", resultado);
    }

    @Test
    public void deveRecuperarSenhaComTokenValido() {
        ClienteControllerMock controller = new ClienteControllerMock(new ClienteDaoMock());

        String email = "thiago@gmail.com";
        LocalDate dataSolicitacao = LocalDate.of(2025, 5, 25);
        LocalDate dataRecuperacao = LocalDate.of(2025, 5, 25);

        String mensagem = controller.recuperarSenha(email, dataSolicitacao, dataRecuperacao);

        assertEquals("Senha recuperada com sucesso.", mensagem);
    }

    @Test
    public void naoDeveRecuperarSenhaComTokenExpirado() {
        ClienteControllerMock controller = new ClienteControllerMock(new ClienteDaoMock());

        String email = "thiago@gmail.com";
        LocalDate dataSolicitacao = LocalDate.of(2025, 5, 25);
        LocalDate dataRecuperacao = LocalDate.of(2025, 5, 27);

        String mensagem = controller.recuperarSenha(email, dataSolicitacao, dataRecuperacao);

        assertEquals("Recuperação de senha expirada", mensagem);
    }
}
