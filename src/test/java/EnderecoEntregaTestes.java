import models.EnderecoEntrega;
import org.junit.Test;

import static org.junit.Assert.*;

public class EnderecoEntregaTestes {

    @Test
    public void deveValidarEnderecoValido(){
        //Arrange
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega("Brasil", "Thiago Rafael Schulze", 5547912345678L, 89150000, "Rua do Thiago", 15, "", "Centro");

        //Act
        boolean resultado = enderecoEntrega.validarEnderecoEntrega();

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void deveValidarEnderecoInvalido(){
        //Arrange
        EnderecoEntrega enderecoEntrega = new EnderecoEntrega("Brasil", "Thiago Rafael Schulze", 5547912345678L, 891500000, "Rua do Thiago", 15, "", "Centro");

        //Act
        boolean resultado = enderecoEntrega.validarEnderecoEntrega();

        //Assert
        assertFalse(resultado);
    }

}
