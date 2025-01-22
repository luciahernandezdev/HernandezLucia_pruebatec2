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

@WebServlet("/listadoTurno")
public class TurnoServlet extends HttpServlet {
    //Dependencias del controlador de Turno
    private final TurnoController turnoController = new TurnoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtiene la lista de todos los turnos desde el controlador
        List<Turno> turnos = turnoController.findAll();

        //Establece la lista de turnos como un atributo de la solicitud
        request.setAttribute("turnos", turnos);

        //Redirige a la página JSP para mostrar la lista de turnos
        request.getRequestDispatcher("turno.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtiene los parámetros de filtrado del formulario
        String estado = request.getParameter("tipoEstado");
        String fecha = request.getParameter("fecha");

        //Filtra los turnos según los parámetros proporcionados
        List<Turno> turnosFiltrados = turnoController.filtrarTurno(estado, fecha);

        //Establece la lista de turnos filtrados como un atributo de la solicitud
        request.setAttribute("turnos", turnosFiltrados);

        //Redirige a la página JSP para mostrar la lista de turnos filtrados
        request.getRequestDispatcher("turno.jsp").forward(request, response);
    }
}
