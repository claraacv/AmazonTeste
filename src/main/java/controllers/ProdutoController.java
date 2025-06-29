package controllers;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import models.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoController {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("AmazonPU");
    /**
     * RT18 · filtrar por categoria Unitario
     */
    public List<Produto> filtrarPorCategoria(List<Produto> todos, String categoria) {
        if (todos == null || categoria == null) {
            return List.of();
        }
        return todos.stream()
                .filter(p -> categoria.equals(p.getCategoria()))
                .collect(Collectors.toList());
    }


    // RT18 · filtrar por faixa de preço [min, max] Unitario

    public List<Produto> filtrarPorPreco(List<Produto> todos, double min, double max) {
        if (todos == null) {
            return List.of();
        }
        return todos.stream()
                .filter(p -> p.getValor() >= min && p.getValor() <= max)
                .collect(Collectors.toList());
    }

    public boolean cadastrarProduto(Produto produto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(produto);
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

    // Atualizar produto (merge)
    public boolean atualizarProduto(Produto produto) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.merge(produto);
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

    // Remover produto pelo id
    public boolean removerProduto(Long produtoId) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            Produto produto = em.find(Produto.class, produtoId);
            if (produto != null) {
                em.remove(produto);
                em.getTransaction().commit();
                return true;
            } else {
                em.getTransaction().rollback();
                return false;
            }
        } catch (Exception e) {
            if (em.getTransaction().isActive())
                em.getTransaction().rollback();
            return false;
        } finally {
            em.close();
        }
    }

}
