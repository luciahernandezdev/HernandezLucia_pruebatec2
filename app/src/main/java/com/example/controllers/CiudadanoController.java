package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.persistence.GenericoJPA;

import java.util.List;

public class CiudadanoController {
    private final GenericoJPA<Ciudadano, Long> genericoJPA;

    public CiudadanoController() {
        this.genericoJPA = new GenericoJPA<>(Ciudadano.class);
    }

    public List<Ciudadano> findAll() {
        return genericoJPA.findAll();
    }


}
