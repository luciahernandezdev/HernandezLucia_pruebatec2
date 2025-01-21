<%@ page import="java.util.List, com.example.entities.Turno, com.example.entities.Ciudadano" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">

<body>
    <%@ include file="partials/head.jsp" %>
    <%@ include file="partials/header.jsp" %>
    <div class="container">

        <div class="row justify-content-center">
            <div class="col-md-8">
                <fieldset class="border p-4">
                    <form action="/app/crearTurno" method="post">
                        <div class="form-group">
                            <label for="ciudadano">Ciudadano:</label>
                            <select id="ciudadano" name="ciudadano" class="form-control">
                                <%
                                List<Ciudadano> listaCiudadanos = (List<Ciudadano>) request.getAttribute("ciudadanos");
                                if (listaCiudadanos != null) {
                                    for (Ciudadano ciudadano : listaCiudadanos) { %>
                                        <option value="<%= ciudadano.getId() %>"><%= ciudadano.getNombre() %> <%= ciudadano.getApellido() %></option>
                                    <% }
                                } else { %>
                                    <option value="">No hay ciudadanos disponibles</option>
                                <% } %>
                            </select>
                        </div>
                         <div class="form-group">
                            <label for="fecha">Fecha:</label>
                            <input type="date" id="fecha" name="fecha" class="form-control">
                          </div>
                        <div class="form-group">
                            <label for="tramite">Trámite:</label>
                            <input type="text" placeholder="Trámite que desea a realizar" id="tramite"class="form-control">
                        </div>

                        <div class="form-group">
                            <label for="tipoEstado">Selecciona Estado:</label>
                            <select id="tipoEstado" name="tipoEstado" class="form-control disenho_select">
                                <option value="ESPERA">En espera</option>
                                <option value="ATENDIDO">Atendido</option>
                            </select>
                        </div>
                        <button type="submit" class="btn btn-primary">Crear turno</button>
                    </form>
                </fieldset>
            </div>
        </div>
    </div>
</body>

</html>
