package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;

import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GenericoJPA<T> {

    private static final Logger LOGGER = Logger.getLogger(GenericoJPA.class.getName());
    private final Class<T> entityClass;

    public GenericoJPA(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T nueva) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            em.persist(nueva);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error al crear la entidad: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public Optional<T> findOne(Integer id) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            return Optional.ofNullable(em.find(this.entityClass, id));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al buscar la entidad por id: " + e.getMessage(), e);
            return Optional.empty();
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<T> cq = cb.createQuery(this.entityClass);
        Root<T> root = cq.from(this.entityClass);
        CriteriaQuery<T> all = cq.select(root);
        TypedQuery<T> allQuery = em.createQuery(all);
        return allQuery.getResultList();
    }

    public void update(T updateEntidad) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            em.merge(updateEntidad);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error al actualizar la entidad: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void delete(Integer id) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            Optional<T> elementoEncontrado = Optional.ofNullable(em.find(this.entityClass, id));
            if (elementoEncontrado.isPresent()) {
                em.remove(elementoEncontrado.get());
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                LOGGER.log(Level.WARNING, "El id " + id + " de la tabla " + this.entityClass.getSimpleName() + " no existe.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            LOGGER.log(Level.SEVERE, "Error al eliminar la entidad por id " + id + ": " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
