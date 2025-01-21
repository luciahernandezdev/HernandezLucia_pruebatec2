package com.example.servlets;

import com.example.controllers.UsuarioController;
import com.example.entities.Usuario;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    UsuarioController usuarioController = new UsuarioController();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombreUsuario");
        String contrasenia = request.getParameter("contrasenia");

        Usuario usuario = usuarioController.findByNombreUsuario(nombreUsuario);

        if (usuario != null && usuario.getContrasenia().equals(contrasenia)) {
            request.getSession().setAttribute("usuario", usuario);
            response.sendRedirect(request.getContextPath() + "/listadoTurno");
        } else {
            request.setAttribute("error", "Nombre de usuario o contraseña incorrectos.");
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
}
