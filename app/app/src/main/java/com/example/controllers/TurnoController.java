package com.example.controllers;

import com.example.entities.Ciudadano;
import com.example.entities.Turno;
import com.example.exceptions.InvalidTurno;
import com.example.persistence.GenericoJPA;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class TurnoController {
    private static final Logger LOGGER = Logger.getLogger(TurnoController.class.getName());
    private GenericoJPA<Turno> genericoJPA;

    public TurnoController() {
        this.genericoJPA = new GenericoJPA<>(Turno.class);
    }

    //Obtener todos los turnos
    public List<Turno> findAll() {
        return genericoJPA.findAll();
    }

    //Crear un nuevo turno
    public void create(String fecha, String descripcion, String estado, Ciudadano ciudadano) {
        Turno.TipoEstado nombreEstado = convertirEstado(estado);

        try {
            validarTurno(fecha, descripcion);
            Turno nuevo = new Turno(null, LocalDate.parse(fecha), descripcion, nombreEstado, ciudadano);
            genericoJPA.create(nuevo);
        } catch (InvalidTurno e) {
            LOGGER.log(Level.SEVERE, "Error al crear el turno: " + e.getMessage(), e);
        } finally {
            LOGGER.log(Level.INFO, "Operación finalizada");
        }
    }

    //Validar datos del turno
    private void validarTurno(String fecha, String descripcion) throws InvalidTurno {
        if (descripcion.isEmpty() || fecha.isEmpty()) {
            throw new InvalidTurno("No puedes dejar campos vacíos");
        }

        if (LocalDate.parse(fecha).isBefore(LocalDate.now())) {
            throw new InvalidTurno("No puedes introducir una fecha anterior a " + LocalDate.now());
        }
    }

    //Filtrar turnos por estado y fecha
    public List<Turno> filtroTurno(String estado, String fecha) {
        List<Turno> todosTurnos = genericoJPA.findAll();
        Turno.TipoEstado tipo = convertirEstado(estado);
        List<Turno> filtrar;

        if (!fecha.isEmpty()) {
            filtrar = todosTurnos.stream()
                    .filter(turno -> LocalDate.parse(fecha).isBefore(turno.getFecha())
                            && turno.getEstado().equals(tipo))
                    .collect(Collectors.toList());
        } else {
            filtrar = todosTurnos;
            LOGGER.log(Level.INFO, "No hay turnos de esa fecha ni del estado que presenta");
        }

        return filtrar;
    }

    private Turno.TipoEstado convertirEstado(String estado) {
        return estado.equalsIgnoreCase("ESPERA") ? Turno.TipoEstado.ESPERA : Turno.TipoEstado.ATENDIDO;
    }
}
