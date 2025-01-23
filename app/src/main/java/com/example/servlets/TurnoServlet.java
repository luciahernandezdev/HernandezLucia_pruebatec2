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
    /**
     * TurnoServlet gestiona la visualización y filtrado de turnos en la aplicación
     */
    private final TurnoController turnoController = new TurnoController();

    //Método goGet obtiene todos los turnos utilizando TurnoController, los establece como un atributo
    //en la solicitud(request) y reenvía la solicitud a la pagina turno.jsp para su visualización.
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Turno> turnos = turnoController.findAll();

        request.setAttribute("turnos", turnos);
        request.getRequestDispatcher("turno.jsp").forward(request, response);
    }

    //Método doPost toma los párametros tipoEstado y fecha del formulario enviado por el usuario,
    //filtra los turnos utilizando TurnoController, ya actualiza la solicitud con los turnos filtrados antes de reenviar
    //a turno.jsp
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String estado = request.getParameter("tipoEstado");
        String fecha = request.getParameter("fecha");

        List<Turno> turnosFiltrados = turnoController.filtrarTurno(estado, fecha);

        request.setAttribute("turnos", turnosFiltrados);
        request.getRequestDispatcher("turno.jsp").forward(request, response);
    }
}
