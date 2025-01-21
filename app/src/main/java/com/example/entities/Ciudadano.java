package com.example.entities;

import jakarta.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Ciudadano {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    @Column(nullable = false)
    private String apellido;


    @OneToMany(mappedBy = "ciudadano",
            fetch = FetchType.EAGER,
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Turno> turnos = new HashSet<>();


    public Ciudadano() {
    }

    public Ciudadano(Long id, String nombre, String apellido, Set<Turno> turnos) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.turnos = turnos;
    }

    //Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public Set<Turno> getTurnos() {
        return turnos;
    }

    public void setTurnos(Set<Turno> turnos) {
        this.turnos = turnos;
    }

    //toString()
    @Override
    public String toString() {
        return "Ciudadano{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                ", turnos=" + turnos +
                '}';
    }
}

