package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.persistence.GenericoJPA;

import java.util.List;

public class CiudadanoController {
    GenericoJPA<Ciudadano> genericoJPA;


    public CiudadanoController() {
        this.genericoJPA = new GenericoJPA<>(Ciudadano.class);
    }

    //Mostrar todos los ciudadanos para meterlos en los select
    public List<Ciudadano> findAll() {
        return genericoJPA.findAll();
    }
}
