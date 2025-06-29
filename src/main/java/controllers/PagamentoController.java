package controllers;

import jakarta.persistence.*;
import models.MetodoPagamento;

public class PagamentoController {

    private final EntityManagerFactory emf;

    public PagamentoController() {
        emf = Persistence.createEntityManagerFactory("AmazonPU");
    }

    public boolean cadastrar(MetodoPagamento mp) {
        if (!mp.validarValidade()) {
            return false;
        }

        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(mp);
            em.getTransaction().commit();
            return true;
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }
}
