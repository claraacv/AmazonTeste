import controllers.AvaliacaoController;
import controllers.ClienteController;
import models.Avaliacao;
import models.Cliente;
import models.Produto;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class ProdutoTestes {

    @Test
    public void avaliacaoValida(){
        //arrange
        Avaliacao avaliacao = new Avaliacao();

        ClienteController controllerCliente = new ClienteController();
        Cliente cliente = controllerCliente.getClienteById(1);

        Produto produto = new Produto("Mochila", 199.90, 10);

        avaliacao.setEstrelas(5);
        avaliacao.setComentario("Produto muito bom, mochila reforçada de boa qualidade");

        AvaliacaoController controller = new AvaliacaoController();

        //act
        boolean resultado = controller.validarAvaliacao(avaliacao);

        //assert
        assertTrue(resultado);
    }

    @Test
    public void avaliacaoInvalida(){
        //arrange
        Avaliacao avaliacao = new Avaliacao();

        ClienteController controllerCliente = new ClienteController();
        Cliente cliente = controllerCliente.getClienteById(1);

        Produto produto = new Produto("Mochila", 199.90, 10);

        avaliacao.setComentario("Produto muito bom, mochila reforçada de boa qualidade");

        AvaliacaoController controller = new AvaliacaoController();

        //act
        boolean resultado = controller.validarAvaliacao(avaliacao);

        //assert
        assertFalse(resultado);
    }
}
