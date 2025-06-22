package daos;

import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import models.Cliente;

import java.util.List;

public class ClienteDao extends dao{

    public boolean salvar(Cliente c){
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
        return em.createQuery("SELECT c FROM Cliente c", Cliente.class).getResultList();
    }

    public Cliente getClienteById(long id){
        return em.find(Cliente.class, id);
    }

    public Cliente buscarPorEmail(String email) {
        try {
            return em.createQuery("SELECT c FROM Cliente c WHERE c.emailTelefone = :email", Cliente.class)
                    .setParameter("email", email)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}
