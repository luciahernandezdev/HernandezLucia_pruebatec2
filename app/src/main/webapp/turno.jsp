<%@ page import="java.util.List, com.example.entities.Turno, com.example.entities.Ciudadano" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>

<html lang="en">

<body>
    <%@ include file="partials/header.jsp" %>
    <%@ include file="partials/head.jsp" %>

    <div class="container">
        <fieldset class="filter-form">
            <form action="/app/listadoTurno" method="post">
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" id="fecha" name="fecha" class="form-control" />
                </div>

                <div class="form-group">
                    <label for="tipoEstado">Estado del turno:</label>
                    <select id="tipoEstado" name="tipoEstado" class="form-control">
                        <option value="ESPERA">En espera</option>
                        <option value="ATENDIDO">Atendido</option>
                    </select>
                </div>

                <button type="submit" class="btn btn-primary">Filtrar</button>
            </form>
            </br>
        </fieldset>

        <table class="table table-striped turnos-table">
            <thead class="thead-dark">
                <tr>
                    <th>Nombre completo</th>
                    <th>Descripción</th>
                    <th>Fecha del Turno</th>
                    <th>Estado</th>
                </tr>
            </thead>
            <tbody>
                <%
                List<Turno> turnos = (List<Turno>) request.getAttribute("turnos");

                // Verificar si la lista de turnos es null o vacía
                if (turnos != null && !turnos.isEmpty()) {
                    // Iterar sobre la lista de turnos
                    for (Turno turno : turnos) {
                %>
                <tr>
                    <td>
                        <%= turno.getCiudadano().getNombre() %> <%= turno.getCiudadano().getApellido() %>
                    </td>
                    <td>
                        <%= turno.getDescripcion() %>
                    </td>
                    <td>
                        <%= turno.getFecha() %>
                    </td>
                    <td>
                        <%= turno.getEstado() %>
                    </td>
                </tr>
                <%
                    }
                } else {
                %>
                <tr>
                    <td colspan="4">No se encontraron turnos.</td>
                </tr>
                <%
                }
                %>
            </tbody>
        </table>
    </div>

</body>

</html>
