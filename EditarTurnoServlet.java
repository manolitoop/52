
import modelo.ConexionDB;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/EditarTurnoServlet")
public class EditarTurnoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        try (Connection conn = ConexionDB.Conectar()) {
            String query = "SELECT * FROM turnos WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                request.setAttribute("id", rs.getInt("id"));
                request.setAttribute("nombre", rs.getString("nombre_empleado"));
                request.setAttribute("fecha", rs.getDate("fecha").toString());
                request.setAttribute("horaInicio", rs.getTime("hora_inicio").toString());
                request.setAttribute("horaFin", rs.getTime("hora_fin").toString());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // Redirige a la página de edición de turnos con los datos cargados
        request.getRequestDispatcher("editar-turno.jsp").forward(request, response);
    }
}
