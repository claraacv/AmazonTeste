import models.Cupom;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class CupomTestes {

    @Test
    public void deveValidarCupomComDataValida() {

        //Arrange
        Cupom cupom = new Cupom(1, "JUNHO25", LocalDate.of(2025, 6, 30), 0.05);

        //Act
        boolean resultado = cupom.validarCupom();

        //Assert
        assertTrue(resultado);
    }

    @Test
    public void naoDeveValidarCupomComDataVencida() {

        //Arrange
        Cupom cupom = new Cupom(2, "PASCOA", LocalDate.of(2025, 4, 30), 0.10);

        //Act
        boolean resultado = cupom.validarCupom();

        //Assert
        assertFalse(resultado);
    }

    


}
