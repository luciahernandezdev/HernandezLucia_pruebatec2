package com.example.servlets;

import com.example.controllers.CiudadanoController;
import com.example.controllers.TurnoController;
import com.example.entities.Ciudadano;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/crearTurno")
public class TurnoFormServlet extends HttpServlet {

    private static final Logger LOGGER = Logger.getLogger(TurnoFormServlet.class.getName());

    private CiudadanoController ciudadanoController = new CiudadanoController();
    private TurnoController turnoController = new TurnoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ciudadano> listadoCiudadanos = ciudadanoController.findAll();
        request.setAttribute("ciudadanos", listadoCiudadanos);
        request.getRequestDispatcher("formTurno.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fecha = req.getParameter("fecha");
        String descripcion = req.getParameter("descripcion");
        String estado = req.getParameter("tipoEstado");
        Long ciudadanoId = Long.valueOf(req.getParameter("cliente"));
        Ciudadano ciudadano = new Ciudadano(ciudadanoId, null, null, null);

        try {
            turnoController.create(fecha, descripcion, estado, ciudadano);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error al crear el turno: " + e.getMessage(), e);
            req.setAttribute("error", "Hubo un error al crear el turno. Por favor, inténtelo de nuevo.");
            req.getRequestDispatcher("formTurno.jsp").forward(req, resp);
            return;
        }

        resp.sendRedirect(req.getContextPath() + "/listadoTurno");
    }
}
