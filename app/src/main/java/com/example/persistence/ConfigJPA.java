package com.example.persistence;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class ConfigJPA {
    /**
     EntityManagerFactory: crea una única instancia estática de EntityManagerFactory usando
     Persistence.createEntityManagerFactory("examplePU"), donde "examplePU" es el nombre de la unidad de persistencia
     definida en el archivo persistence.xml.
     getEntityManagerFactory(): Método estático que devuelve una instancia de EntityManager a partir del EntityManagerFactory.
     cerrar(): Método estático que cierra la instancia de EntityManagerFactory.
     **/
    private static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("examplePU");

    public static EntityManager getEntityManagerFactory() {
        return emf.createEntityManager();
    }

    public static void cerrar(){
        emf.close();
    }
}
