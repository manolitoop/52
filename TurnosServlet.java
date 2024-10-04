


import modelo.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/TurnoServlet")
public class TurnosServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String nombre = request.getParameter("nombre");
        String fecha = request.getParameter("fecha");
        String horaInicio = request.getParameter("hora_inicio");
        String horaFin = request.getParameter("hora_fin");

        try (Connection conn = ConexionDB.Conectar()) {
            String query = "INSERT INTO turnos (nombre_empleado, fecha, hora_inicio, hora_fin) VALUES (?, ?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, nombre);
            stmt.setString(2, fecha);
            stmt.setString(3, horaInicio);
            stmt.setString(4, horaFin);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        response.sendRedirect("listado-turnos.jsp"); // Redirige después de registrar
    }
}