package com.example.controllers;

import com.example.entities.Usuario;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceContext;

public class UsuarioController {
        @PersistenceContext
        private EntityManager em;

        public void create(String nombreUsuario, String contrasenia) {
            Usuario usuario = new Usuario();
            usuario.setNombreUsuario(nombreUsuario);
            usuario.setContrasenia(contrasenia);
            em.persist(usuario);
        }

        public Usuario findByNombreUsuario(String nombreUsuario) {
            try {
                return em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = :nombreUsuario", Usuario.class)
                        .setParameter("nombreUsuario", nombreUsuario)
                        .getSingleResult();
            } catch (NoResultException e) {
                return null;
            }
        }


}
