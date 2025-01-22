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

@WebServlet("/crearTurno") //Define el patrón de URL para el servlet
public class TurnoFormServlet extends HttpServlet {

    // Inyección de dependencias utilizando `final` para asegurar que no se reasignen
    CiudadanoController ciudadanoController = new CiudadanoController();
    TurnoController turnoController = new TurnoController();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //Obtiene la lista de ciudadanos desde el controlador
        List<Ciudadano> listaCiudadanos = ciudadanoController.findAll();

        if (listaCiudadanos.isEmpty()) {
            //Establece un mensaje de error si no hay ciudadanos registrados
            request.setAttribute("error", "No hay ciudadanos registrados para asignar turnos.");
        }
        //Pasa la lista de ciudadanos al JSP
        request.setAttribute("ciudadanos", listaCiudadanos);
        request.getRequestDispatcher("formTurno.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //Obtiene los parametros del formulario
        String fecha = req.getParameter("fecha");
        String descripcion = req.getParameter("descripcion");
        String estado = req.getParameter("tipoEstado");
        String ciudadanoIDStr = req.getParameter("ciudadano");

        //Valida que ningun campo este vacio
        if (fecha == null || descripcion == null || ciudadanoIDStr == null || fecha.isEmpty() || descripcion.isEmpty() || ciudadanoIDStr.isEmpty()) {
            //Establece un mensaje de error si esta vacio
            req.setAttribute("error", "Todos los campos son obligatorios.");
            req.getRequestDispatcher("formTurno.jsp").forward(req, resp);
            return;
        }

        try {
            //Conversion de ID de ciudadano a LONG
            Long ciudadanoID = Long.valueOf(ciudadanoIDStr);
            //Crea nueva instancia de Ciudadano con el ID proporcionado
            Ciudadano ciudadano = new Ciudadano(ciudadanoID, null, null, null);
            //Crea el turno con los datos proporcionados
            turnoController.create(fecha, descripcion, estado, ciudadano);
            // Redirigir a la lista de turnos
            resp.sendRedirect(req.getContextPath() + "/listadoTurno");

        } catch (NumberFormatException e) {
            //Manejo de excepción para ID de ciudadano inválido
            req.setAttribute("error", "ID de ciudadano inválido.");
            req.getRequestDispatcher("formTurno.jsp").forward(req, resp);
        } catch (InvalidTurno e) {
            //Manejo de excepción para errores específicos al crear el turno
            req.setAttribute("error", "Error al crear el turno: " + e.getMessage());
            req.getRequestDispatcher("formTurno.jsp").forward(req, resp);
        }
    }
}
