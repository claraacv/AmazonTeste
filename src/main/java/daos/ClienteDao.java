package daos;

import jakarta.persistence.EntityManager;
import models.Cliente;

import java.util.List;

public class ClienteDao extends dao{

    public boolean salvar(Cliente c){
        EntityManager em = emf.createEntityManager();
        try{
            em.getTransaction().begin();
            em.persist(c);
            em.getTransaction().commit();
            return true;
        }catch(Exception e){
            if(em.getTransaction().isActive()){
                em.getTransaction().rollback();
            }
            return false;
        }
    }

    public List<Cliente> listar(){
        EntityManager em = emf.createEntityManager();
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }
}
