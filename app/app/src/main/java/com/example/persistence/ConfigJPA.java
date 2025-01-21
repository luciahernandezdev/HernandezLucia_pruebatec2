package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigJPA {
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");

    public static EntityManager getEntityManagerFactory() {
        return emf.createEntityManager();
    }

    public static void cerrar(){
        emf.close();
    }
}
