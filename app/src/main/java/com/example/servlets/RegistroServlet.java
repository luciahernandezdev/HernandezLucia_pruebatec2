package com.example.servlets;

import com.example.controllers.UsuarioController;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/registrar")
public class RegistroServlet extends HttpServlet {
    UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");

        if (nombreUsuario == null || contrasenia == null || nombreUsuario.isEmpty() || contrasenia.isEmpty()) {
            request.setAttribute("error", "Todos los campos son obligatorios.");
            request.getRequestDispatcher("registro.jsp").forward(request, response);
            return;
        }

        usuarioController.create(nombreUsuario, contrasenia);
        response.sendRedirect(request.getContextPath() + "/login.jsp");
    }
}
