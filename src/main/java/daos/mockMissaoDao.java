package daos;

import models.Cliente;
import models.Missao;

public class mockMissaoDao {
    public Missao getMissaoById(long id, double valor, String nome){
        Missao missao = new Missao(valor, nome);
        missao.setId(id);
        if(missao.getId() == id){
            return missao;
        }
        return null;
    }
}
