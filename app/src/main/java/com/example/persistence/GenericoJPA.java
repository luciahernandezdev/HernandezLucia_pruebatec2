package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import java.util.List;
import java.util.Optional;

public class GenericoJPA<T, ID> {

    /**
     * Se usa JPA para realizar operaciones CRUD sobre cualquier tipo de entidad,
     * crea una entidad, obtiene todas las entidades, encuentra una entidad por su ID,
     * actualiza una entidad existente y elimina una entidad por su ID.
     */
    private final Class<T> entityClass;

    public GenericoJPA(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    public void create(T entity) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            em.persist(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al crear la entidad: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public List<T> findAll() {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            TypedQuery<T> query = em.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e", entityClass);
            return query.getResultList();
        } catch (Exception e) {
            System.err.println("Error al buscar todas las entidades: " + e.getMessage());
            return List.of();
        } finally {
            em.close();
        }
    }

    public Optional<T> findOne(Integer id){
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            return Optional.ofNullable(em.find(this.entityClass,id));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            em.close();
        }
        return Optional.empty();
    }


    public void update(T entity) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al actualizar la entidad: " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }

    public void delete(ID id) {
        EntityManager em = ConfigJPA.getEntityManagerFactory();
        try {
            em.getTransaction().begin();
            T entity = em.find(entityClass, id);
            if (entity != null) {
                em.remove(entity);
                em.getTransaction().commit();
            } else {
                em.getTransaction().rollback();
                System.err.println("El id " + id + " no existe.");
            }
        } catch (Exception e) {
            em.getTransaction().rollback();
            throw new RuntimeException("Error al eliminar la entidad por id " + id + ": " + e.getMessage(), e);
        } finally {
            em.close();
        }
    }
}
