<%@ page import="java.util.List, com.example.entities.Turno, com.example.entities.Ciudadano" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">

<body>
    <%@ include file="partials/header.jsp" %>
    <%@ include file="partials/head.jsp" %>
    <div class="container">
        <div class="div-container">
            <h1 class="mt-4">Buscar turnos</h1>
            <fieldset class="border p-4">
                <form action="/app/listadoTurno" method="post" class="form-inline">
                    <div class="form-group mr-3">
                        <label for="fecha" class="mr-2">Fecha: </label>
                        <input type="date" id="fecha" name="fecha" class="form-control" />
                    </div>
                    <div class="form-group mr-3">
                        <label for="tipoEstado" class="mr-2">Estado del turno: </label>
                        <select id="tipoEstado" name="tipoEstado" class="form-control">
                            <option value="ESPERA">En espera</option>
                            <option value="ATENDIDO">Ya Atendido</option>
                        </select>
                    </div>
                    <button type="submit" class="btn btn-primary">Filtrar</button>
                </form>
            </fieldset>
            <div class="table-responsive mt-4">
                <%
                String error = (String) request.getAttribute("error");
                if (error != null) {
                %>
                <div class="alert alert-danger"><%= error %></div>
                <%
                }
                %>
                <table class="table table-striped turnos-table">
                    <thead class="thead-dark">
                        <tr>
                            <th>Nombre completo</th>
                            <th>Descripción</th>
                            <th>Fecha</th>
                            <th>Estado</th>
                        </tr>
                    </thead>
                    <tbody>
                        <% List<Turno> listado = (List<Turno>) request.getAttribute("listado"); %>
                        <p class="total">Num. turnos: <%= listado != null ? listado.size() : 0 %></p> <!-- Muestra el número de turnos -->
                        <% if (listado != null && !listado.isEmpty()) {
                            for (Turno turno : listado) { %>
                            <tr>
                                <td><%= turno.getCiudadano().getNombre() %> <%= turno.getCiudadano().getApellido() %></td>
                                <td><%= turno.getDescripcion() %></td>
                                <td><%= turno.getFecha() %></td>
                                <td><%= turno.getEstado() %></td>
                            </tr>
                        <% }
                        } else { %>
                        <tr>
                            <td colspan="4">No se encontraron turnos.</td>
                        </tr>
                        <% } %>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
    <%@ include file="partials/footer.jsp" %>
</body>
</html>
