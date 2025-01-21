package com.example.servlets;

import com.example.controllers.TurnoController;
import com.example.entities.Turno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/listadoTurno")
public class TurnoServlet extends HttpServlet {
    private static final Logger LOGGER = Logger.getLogger(TurnoServlet.class.getName());
    private TurnoController turnoController = new TurnoController();

    // Obtener el listado de turnos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Turno> turnosList = turnoController.findAll();
            request.setAttribute("turnosList", turnosList);
            request.getRequestDispatcher("turno.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al obtener el listado de turnos: " + e.getMessage(), e);
            request.setAttribute("error", "Hubo un error al obtener el listado de turnos. Por favor, inténtelo de nuevo.");
            request.getRequestDispatcher("turno.jsp").forward(request, response);
        }
    }

    // Filtrar turnos
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estadoTurno = request.getParameter("tipoEstado");
        String fechaTurno = request.getParameter("fecha");

        try {
            List<Turno> turnosFiltrados = turnoController.filtroTurno(estadoTurno, fechaTurno);
            request.setAttribute("turnosFiltrados", turnosFiltrados);
            request.getRequestDispatcher("turno.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al filtrar los turnos: " + e.getMessage(), e);
            request.setAttribute("error", "Hubo un error al filtrar los turnos. Por favor, inténtelo de nuevo.");
            request.getRequestDispatcher("turno.jsp").forward(request, response);
        }
    }
}
