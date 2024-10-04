
<!DOCTYPE html>
<html>
<head>
    <title>Registrar Turno</title>
</head>
<link href="css/styles.css" rel="stylesheet" type="text/css"/>
<link href="css/CSS.INDEX.css" rel="stylesheet" type="text/css"/>
<body>
   
    <h2>Registrar Nuevo Turno</h2>
    <form action="TurnoServlet" method="post">
        Turno Realizado: <input type="text" name="nombre"><br>
        Fecha: <input type="date" name="fecha"><br>
        Hora de Inicio: <input type="time" name="hora_inicio"><br>
        Hora de Fin: <input type="time" name="hora_fin"><br>
        <input type="submit" value="Registrar"> 
    </form>
    <br> <br>
   
   <a href="index.jsp" class="button-link">Volver</a>
</body>
</html>
