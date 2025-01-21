<!DOCTYPE html>
<html>
<head>
    <title>Registro</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
</head>
<body>
<%@ include file="partials/header.jsp" %>
    <%@ include file="partials/head.jsp" %>
    <div class="container">
        <h2>Registro de Usuario</h2>
        <form action="/registrar" method="post">
            <div class="form-group">
                <label for="nombreUsuario">Nombre de Usuario:</label>
                <input type="text" class="form-control" id="nombreUsuario" name="nombreUsuario">
            </div>
            <div class="form-group">
                <label for="contraseña">Contraseña:</label>
                <input type="password" class="form-control" id="contraseña" name="contraseña">
            </div>
            <button type="submit" class="btn btn-primary">Registrar</button>
        </form>
    </div>
</body>
</html>
