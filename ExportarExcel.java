import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.ConexionDB;

@WebServlet("/ExportarExcel")
public class ExportarExcel extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Crear un nuevo workbook de Excel
        Workbook workbook = new XSSFWorkbook();
        Sheet sheet = workbook.createSheet("Turnos");

        // Crear la cabecera del archivo Excel
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("Nombre del Empleado");
        headerRow.createCell(2).setCellValue("Fecha");
        headerRow.createCell(3).setCellValue("Hora de Inicio");
        headerRow.createCell(4).setCellValue("Hora de Fin");

        // Conectar a la base de datos MySQL y obtener los datos
        try (Connection conn = ConexionDB.Conectar()) {
            String sql = "SELECT id, nombre_empleado, fecha, hora_inicio, hora_fin FROM turnos";
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            // Llenar el Excel con los datos de la base de datos
            int rowNum = 1;
            while (rs.next()) {
                Row row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(rs.getInt("id"));
                row.createCell(1).setCellValue(rs.getString("nombre_empleado"));
                row.createCell(2).setCellValue(rs.getString("fecha"));
                row.createCell(3).setCellValue(rs.getString("hora_inicio"));
                row.createCell(4).setCellValue(rs.getString("hora_fin"));
            }

            // Ajustar el tamaño de las columnas
            for (int i = 0; i < 5; i++) {
                sheet.autoSizeColumn(i);
            }

            // Configurar la respuesta para que el navegador descargue el archivo Excel
            response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            response.setHeader("Content-Disposition", "attachment; filename=turnos.xlsx");

            // Escribir el archivo Excel en la salida de respuesta
            try (OutputStream out = response.getOutputStream()) {
                workbook.write(out);
            } finally {
                workbook.close();  // Cerrar el workbook
            }

        } catch (SQLException e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }
}
