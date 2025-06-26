package mocks;

import models.Cupom;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CupomDaoMock {
    public List<Cupom> getCupons(){
        List<Cupom> cupons = new ArrayList<Cupom>();
        Cupom cupom = new Cupom(1, "25Tudo", LocalDate.now().plusDays(7), 25);
        Cupom cupom2 = new Cupom(2, "10Casa", LocalDate.now().plusDays(14), 10);

        cupons.add(cupom);
        cupons.add(cupom2);

        return cupons;
    }
}
