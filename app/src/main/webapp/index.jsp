<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="es">
<body>
    <%@ include file="partials/head.jsp" %>
    <%@ include file="partials/header.jsp" %>
    <div class="container mt-5">
        <div class="jumbotron text-center">
            <h1 class="display-4">Bienvenido a la Gestión de Turnos</h1>
            <p class="lead">Administra tus turnos de manera fácil y eficiente.</p>
            <hr class="my-4">
            <p>Utiliza el menú de navegacion para ver, crear y gestionar turnos.</p>
            <a class="btn btn-primary btn-lg" href="/app/listadoTurno" role="button">Ver Turnos</a>
            <a class="btn btn-primary btn-lg" href="/app/crearTurno" role="button">Crear Turnos</a>
        </div>
    </div>
</body>
</html>
