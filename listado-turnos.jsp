
<%@ page import="java.sql.*" %>
<%@ page import="modelo.ConexionDB" %>

<!DOCTYPE html>
<html>
<head>
    <title>Listado de Turnos</title>
</head>
<link href="css/styles.css" rel="stylesheet" type="text/css"/>
<link href="css/CSS.INDEX.css" rel="stylesheet" type="text/css"/>
<body>
    <h2>Turnos Registrados</h2>
    <table border="1">
        <tr>
            <th>ID</th>
            <th>Turno Realizado</th>
            <th>Fecha</th>
            <th>Hora de Inicio</th>
            <th>Hora de Fin</th>
            <th>Acciones</th>
        </tr>

        <%
            try (Connection conn = ConexionDB.Conectar()) {
                String query = "SELECT * FROM turnos";
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery();
                while (rs.next()) {
        %>
                    <tr>
                        <td><%= rs.getInt("id") %></td>
                        <td><%= rs.getString("nombre_empleado") %></td>
                        <td><%= rs.getDate("fecha") %></td>
                        <td><%= rs.getTime("hora_inicio") %></td>
                        <td><%= rs.getTime("hora_fin") %></td>
                        <td>
                            <a href="EditarTurnoServlet?id=<%= rs.getInt("id") %>">Editar</a> |
                            <a href="EliminarTurnoServlet?id=<%= rs.getInt("id") %>" onclick="return confirm('¿Estás seguro de eliminar este turno?')">Eliminar</a>
                        </td>
                    </tr>
                    
        <%
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        %>
    </table>
    <a href="index.jsp" class="button-link">Volver</a>
</body>
</html>
