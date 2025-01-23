package com.example.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Turno {

    //Atributos
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private LocalDate fecha;

    @Column(nullable = false)
    private String descripcion;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private TipoEstado estado;

    public enum TipoEstado {
        ESPERA,
        ATENDIDO;
    }

    /**
     *Establece la relcion de muchos a uno con la entidad ciudadano,
     * asegurando que ciudadano_id no sea nula en la bbdd y que se cargue de inmediato.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "ciudadano_id", nullable = false)
    private Ciudadano ciudadano;

    //Constructores
    public Turno() {
    }

    public Turno(Long id, LocalDate fecha, String descripcion, TipoEstado estado, Ciudadano ciudadano) {
        this.id = id;
        this.fecha = fecha;
        this.descripcion = descripcion;
        this.estado = estado;
        this.ciudadano = ciudadano;
    }

    //Getters y setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoEstado getEstado() {
        return estado;
    }

    public void setEstado(TipoEstado estado) {
        this.estado = estado;
    }

    public Ciudadano getCiudadano() {
        return ciudadano;
    }

    public void setCiudadano(Ciudadano ciudadano) {
        this.ciudadano = ciudadano;
    }

    //toString()
    @Override
    public String toString() {
        return "Turno{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", descripcion='" + descripcion + '\'' +
                ", estado=" + estado +
                ", ciudadano=" + ciudadano +
                '}';
    }
}
