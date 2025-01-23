package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.persistence.GenericoJPA;

import java.util.List;

public class CiudadanoController {
    /**
     * Define el controlador CiudadanoController para gestionar operaciones con la entidad
     * ciudadano. Utiliza GenericoJPA para realizar operaciones CRUD.
     * El método findAll permite obtener una lista de todos los ciudadanos.
     */
    private final GenericoJPA<Ciudadano, Long> genericoJPA;

    public CiudadanoController() {
        this.genericoJPA = new GenericoJPA<>(Ciudadano.class);
    }

    public List<Ciudadano> findAll() {
        return genericoJPA.findAll();
    }


}
