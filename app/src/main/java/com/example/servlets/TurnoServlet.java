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
    TurnoController turnoController = new TurnoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse resp) throws ServletException, IOException {

        List<Turno> turnos = turnoController.findAll();

        request.setAttribute("turnos", turnos);

        request.getRequestDispatcher("turno.jsp").forward(request, resp);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String estado = request.getParameter("tipoEstado");
        String fecha = request.getParameter("fecha");


        List<Turno> turnosFiltrados = turnoController.filtrarTurno(estado, fecha);

        request.setAttribute("turnos", turnosFiltrados);
        request.getRequestDispatcher("turno.jsp").forward(request, response);
    }
}
