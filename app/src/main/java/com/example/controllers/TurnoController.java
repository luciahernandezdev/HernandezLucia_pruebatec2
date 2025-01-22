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

    // Constructor que inicializa la comunicación con la base de datos para Turno
    public TurnoController() {
        this.turnoJPA = new GenericoJPA<>(Turno.class);
    }

    //Método para obtener todos los turnos
    public List<Turno> findAll() {
        return turnoJPA.findAll();
    }

    //Método para crear un nuevo turno
    public void create(String fecha, String descripcion, String estado, Ciudadano ciudadano) throws InvalidTurno {
        try {
            //Convierte el estado desde String a la enumeración(estado) correspondiente
            Turno.TipoEstado nombreEstado = elegirEstado(estado);
            validarTurno(fecha, descripcion);
            //Crea una nueva instancia de Turno
            Turno nuevo = new Turno(null, LocalDate.parse(fecha), descripcion, nombreEstado, ciudadano);
            //Guarda el turno en la base de datos
            turnoJPA.create(nuevo);
        } catch (Exception e) {
            //Maneja excepciones y lanza un error especifico
            throw new InvalidTurno("Error al crear el turno: " + e.getMessage(), e);
        }
    }

    //Método para validar los datos del turno
    private void validarTurno(String fecha, String descripcion) throws InvalidTurno {
        LocalDate parsedFecha = LocalDate.parse(fecha);
        //Valida que la descripción no esté vacía
        if (descripcion.isEmpty()) {
            throw new InvalidTurno("No puedes dejar campos vacíos");
        }
        //Valida que la fecha no sea anterior a la fecha actual
        if (parsedFecha.isBefore(LocalDate.now())) {
            throw new InvalidTurno("No puedes introducir la fecha " + LocalDate.now());
        }
    }

    //Método para filtrar turnos por estado y fecha
    public List<Turno> filtrarTurno(String estado, String fecha) {
        //Obtiene todos los turnos
        List<Turno> todosTurnos = turnoJPA.findAll();
        Turno.TipoEstado tipo = elegirEstado(estado);
        List<Turno> filtrarTurno;

        if (!fecha.isEmpty()) {
            LocalDate parsedFecha = LocalDate.parse(fecha);
            //Filtra turnos por fecha y estado
            filtrarTurno = todosTurnos.stream()
                    .filter(turno -> parsedFecha.isBefore(turno.getFecha()) && turno.getEstado().equals(tipo))
                    .collect(Collectors.toList());
        } else {
            //Si no hay fecha, devuelve todos los turnos
            filtrarTurno = todosTurnos;
            System.out.println("No hay turnos en esa fecha");
        }

        return filtrarTurno;
    }

    //Método que convierte el estado desde String a la enumeración correspondiente
    private Turno.TipoEstado elegirEstado(String estado) {
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
