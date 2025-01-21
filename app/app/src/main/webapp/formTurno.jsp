<%@ page import="java.util.List, com.example.entities.Ciudadano" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<body>
    <%@ include file="partials/header.jsp" %>
    <%@ include file="partials/head.jsp" %>
    <div class="container mt-5" "container-form">
        <h1>Crear turnos</h1>
        <fieldset class="border p-4">
            <form action="/app/crearTurno" method="post">
                <div class="form-group">
                    <label for="cliente">Ciudadano:</label>
                    <select id="cliente" name="cliente" class="form-control">
                        <% List<Ciudadano> ciudadanos = (List<Ciudadano>) request.getAttribute("ciudadanos");
                        if (ciudadanos != null && !ciudadanos.isEmpty()) {
                            for (Ciudadano ciudadano : ciudadanos) { %>
                                <option value="<%=ciudadano.getId()%>"><%=ciudadano.getNombre()%> <%=ciudadano.getApellido()%></option>
                            <% }
                        } else { %>
                            <option value="">No hay ciudadanos disponibles</option>
                        <% } %>
                    </select>
                </div>
                <div class="form-group">
                    <label for="descripcion">Trámite a realizar:</label>
                    <input type="text" name="descripcion" placeholder="Ingrese descripción" id="descripcion" class="form-control">
                </div>
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" id="fecha" name="fecha" class="form-control">
                </div>
                <div class="form-group">
                    <label for="tipoEstado">Selecciona Estado:</label>
                    <select id="tipoEstado" name="tipoEstado" class="form-control">
                        <option value="ESPERA">En espera</option>
                        <option value="ATENDIDO">Atendido</option>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Crear turno</button>
            </form>
        </fieldset>
    </div>
    <%@ include file="partials/footer.jsp" %>
</body>
</html>
