package com.example.servlets;

import com.example.controllers.CiudadanoController;
import com.example.controllers.TurnoController;
import com.example.entities.Ciudadano;
import com.example.exceptions.InvalidTurno;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/crearTurno")
public class TurnoFormServlet extends HttpServlet {
    /**
     * TurnoFormServlet maneja la creación de turnos de una aplicación utilizando los controladores
     * CiudadanoController y TurnoController para obtener datos de ciudadanos y crear nuevos turnos.
     */
    CiudadanoController ciudadanoController = new CiudadanoController();
    TurnoController turnoController = new TurnoController();

    //Método doGet obtiene y muestra una lista de ciudadanos en el formulario JSP
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Ciudadano> listaCiudadanos = ciudadanoController.findAll();
        if (listaCiudadanos.isEmpty()) {
            request.setAttribute("error", "No hay ciudadanos registrados para asignar turnos.");
        }

        request.setAttribute("ciudadanos", listaCiudadanos);
        request.getRequestDispatcher("formTurno.jsp").forward(request, response);
    }

    //Método doPost procesa los datos enviados por el formulario, valida los campos, crea un nuevo turno
    //y maneja excepciones de errores en la entrada de datos o en la creación del turno.
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String fecha = req.getParameter("fecha");
        String descripcion = req.getParameter("descripcion");
        String estado = req.getParameter("tipoEstado");
        String ciudadanoIDStr = req.getParameter("ciudadano");

        if (fecha == null || descripcion == null || ciudadanoIDStr == null || fecha.isEmpty() || descripcion.isEmpty() || ciudadanoIDStr.isEmpty()) {

            req.setAttribute("error", "Todos los campos son obligatorios.");
            req.getRequestDispatcher("formTurno.jsp").forward(req, resp);
            return;
        }

        try {
            Long ciudadanoID = Long.valueOf(ciudadanoIDStr);
            Ciudadano ciudadano = new Ciudadano(ciudadanoID, null, null, null);
            turnoController.create(fecha, descripcion, estado, ciudadano);

            resp.sendRedirect(req.getContextPath() + "/listadoTurno");

        } catch (NumberFormatException e) {
            req.setAttribute("error", "ID de ciudadano inválido.");
            req.getRequestDispatcher("formTurno.jsp").forward(req, resp);

        } catch (InvalidTurno e) {
            req.setAttribute("error", "Error al crear el turno: " + e.getMessage());
            req.getRequestDispatcher("formTurno.jsp").forward(req, resp);
        }
    }
}
