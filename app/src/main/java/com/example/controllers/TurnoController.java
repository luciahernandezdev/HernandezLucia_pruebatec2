package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.entities.Turno;
import com.example.exceptions.InvalidTurno;
import com.example.persistence.GenericoJPA;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class TurnoController {
    private final GenericoJPA<Turno, Long> turnoJPA;

    public TurnoController() {
        this.turnoJPA = new GenericoJPA<>(Turno.class);
    }

    public List<Turno> findAll() {
        return turnoJPA.findAll();
    }

    public void create(String fecha, String descripcion, String estado, Ciudadano ciudadano) throws InvalidTurno {
        try {
            Turno.TipoEstado nombreEstado = elegirEnumeracion(estado);
            validarTurno(fecha, descripcion);
            Turno nuevo = new Turno(null, LocalDate.parse(fecha), descripcion, nombreEstado, ciudadano);
            turnoJPA.create(nuevo);
        } catch (Exception e) {
            throw new InvalidTurno("Error al crear el turno: " + e.getMessage(), e);
        }
    }

    private void validarTurno(String fecha, String descripcion) throws InvalidTurno {
        LocalDate parsedFecha = LocalDate.parse(fecha);
        if (descripcion.isEmpty()) {
            throw new InvalidTurno("No puedes dejar campos vacíos");
        }
        if (parsedFecha.isBefore(LocalDate.now())) {
            throw new InvalidTurno("No puedes introducir la fecha " + LocalDate.now());
        }
    }

    public List<Turno> filtrarTurno(String estado, String fecha) {
        List<Turno> todosTurnos = turnoJPA.findAll();
        Turno.TipoEstado tipo = elegirEnumeracion(estado);
        List<Turno> filtrarTurno;

        if (!fecha.isEmpty()) {
            LocalDate parsedFecha = LocalDate.parse(fecha);
            filtrarTurno = todosTurnos.stream()
                    .filter(turno -> parsedFecha.isBefore(turno.getFecha()) && turno.getEstado().equals(tipo))
                    .collect(Collectors.toList());
        } else {
            filtrarTurno = todosTurnos;
            System.out.println("No hay turnos en esa fecha");
        }

        return filtrarTurno;
    }

    private Turno.TipoEstado elegirEnumeracion(String estado) {
        switch (estado.toUpperCase()) {
            case "ESPERA":
                return Turno.TipoEstado.ESPERA;
            case "ATENDIDO":
                return Turno.TipoEstado.ATENDIDO;
            default:
                throw new IllegalArgumentException("Estado inválido: " + estado);
        }
    }
}
