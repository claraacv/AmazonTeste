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

        Cliente cliente = new Cliente("testeAv@gmail.com");
        cliente.setNome("Avaliação Válida I");

        Produto produto = new Produto("Mochila", 199.90, 10);

        Avaliacao avaliacao = new Avaliacao(cliente, produto, "Mochila muito bonita");
        avaliacao.setEstrelas(5);
        avaliacao.setComentario("Produto muito bom, mochila reforçada de boa qualidade");

        AvaliacaoController controller = new AvaliacaoController();

        //act
        boolean resultado = controller.validarAvaliacao(avaliacao);

        //assert
        assertTrue(resultado);
    }

    @Test
    public void avaliacaoValidaSemEstrelas(){
        //arrange
        Cliente cliente = new Cliente("av2@gmail.com");
        cliente.setNome("Avaliação II");

        Produto produto = new Produto("Mochila", 199.90, 10);

        Avaliacao avaliacao = new Avaliacao(cliente, produto, "Produto de alta qualidade");

        avaliacao.setComentario("Produto muito bom, mochila reforçada de boa qualidade");

        AvaliacaoController controller = new AvaliacaoController();

        //act
        boolean resultado = controller.validarAvaliacao(avaliacao);

        //assert
        assertTrue(resultado);
    }
}
