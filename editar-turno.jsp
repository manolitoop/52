<!DOCTYPE html>
<html>
<head>
    <title>Editar Turno</title>
</head>
<link href="css/styles.css" rel="stylesheet" type="text/css"/>
<body>
    <h2>Editar Turno</h2>
    <form action="ActualizarTurnoServlet" method="post">
        <input type="hidden" name="id" value="${id}">
        Nombre del Empleado: <input type="text" name="nombre" value="${nombre}"><br>
        Fecha: <input type="date" name="fecha" value="${fecha}"><br>
        Hora de Inicio: <input type="time" name="hora_inicio" value="${horaInicio}"><br>
        Hora de Fin: <input type="time" name="hora_fin" value="${horaFin}"><br>
        <input type="submit" value="Actualizar">
    </form>
</body>
</html>
