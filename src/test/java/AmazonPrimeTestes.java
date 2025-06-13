import controllers.ClienteController;
import models.*;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertTrue;

public class AmazonPrimeTestes {
    @Test
    public void compraFreteGratis(){
        //arrange
        ClienteController controllerCliente = new ClienteController();
        Cliente cliente = controllerCliente.getClienteById(1);
        Compra compra = new Compra(cliente, LocalDate.now());
        Produto produto = new Produto("Mochila", 150.99, 50);
        ItemCompra item = new ItemCompra(produto, 1);
        AmazonPrime amazon = new AmazonPrime("22/05/2025", cliente);

        //arrange
        boolean resultado = compra.validarFreteGratis();

        //assert
        assertTrue(resultado);
    }

    @Test
    public void compraSemFreteGratis(){
        //arrange
        ClienteController controllerCliente = new ClienteController();
        Cliente cliente = controllerCliente.getClienteById(1);
        Compra compra = new Compra(cliente, LocalDate.now());
        Produto produto = new Produto("Mochila", 150.99, 50);
        ItemCompra item = new ItemCompra(produto, 1);
        AmazonPrime amazon = new AmazonPrime("22/03/2025", cliente);

        //act
        amazon.cancelar("22/05/2025");
        boolean resultado = compra.validarFreteGratis();

        //assert
        assertTrue(resultado);
    }
}
