package servlet;

import conexion.ConexionMySQL;
import entidades.Proyecto;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class ProyectoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Proyecto> proyectos = new ArrayList<>();
        String sql = "SELECT id, nombre_proyecto, descripcion_tecnica, fecha_inicio, "
                + "fecha_culminacion, asesor_tecnico, presupuesto_asignado FROM proyectos";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql);
             ResultSet resultado = sentencia.executeQuery()) {
            while (resultado.next()) {
                Proyecto proyecto = new Proyecto(
                        resultado.getString("nombre_proyecto"),
                        resultado.getString("descripcion_tecnica"),
                        resultado.getDate("fecha_inicio").toLocalDate(),
                        resultado.getDate("fecha_culminacion").toLocalDate(),
                        resultado.getString("asesor_tecnico"),
                        resultado.getBigDecimal("presupuesto_asignado"));
                proyecto.setId(resultado.getInt("id"));
                proyectos.add(proyecto);
            }
        } catch (SQLException e) {
            throw new ServletException("No se pudo consultar la lista de proyectos.", e);
        }

        request.setAttribute("proyectos", proyectos);
        request.getRequestDispatcher("/proyectos.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");

        String nombre = texto(request.getParameter("nombre"));
        String descripcionTecnica = texto(request.getParameter("descripcionTecnica"));
        String fechaInicioTexto = texto(request.getParameter("fechaInicio"));
        String fechaCulminacionTexto = texto(request.getParameter("fechaCulminacion"));
        String asesorTecnico = texto(request.getParameter("asesorTecnico"));
        String presupuestoTexto = texto(request.getParameter("presupuesto"));

        String error = null;
        if (nombre.length() < 4 || nombre.length() > 60) {
            error = "El nombre del proyecto es obligatorio y debe tener entre 4 y 60 caracteres.";
        } else if (fechaInicioTexto.isEmpty()) {
            error = "La fecha de inicio es obligatoria.";
        } else if (fechaCulminacionTexto.isEmpty()) {
            error = "La fecha de culminación es obligatoria.";
        } else if (asesorTecnico.isEmpty() || asesorTecnico.split("\\s+").length < 2) {
            error = "Ingresa los nombres y apellidos del asesor técnico.";
        } else if (presupuestoTexto.isEmpty()) {
            error = "El presupuesto asignado es obligatorio.";
        }

        LocalDate fechaInicio = null;
        LocalDate fechaCulminacion = null;
        BigDecimal presupuesto = null;
        if (error == null) {
            try {
                fechaInicio = LocalDate.parse(fechaInicioTexto);
                fechaCulminacion = LocalDate.parse(fechaCulminacionTexto);
            } catch (DateTimeParseException e) {
                error = "Ingresa fechas válidas para el proyecto.";
            }
        }
        if (error == null) {
            try {
                presupuesto = new BigDecimal(presupuestoTexto);
            } catch (NumberFormatException e) {
                error = "Ingresa un presupuesto numérico válido.";
            }
        }

        if (error != null) {
            request.setAttribute("error", error);
            doGet(request, response);
            return;
        }

        Proyecto proyecto = new Proyecto(nombre, descripcionTecnica, fechaInicio,
                fechaCulminacion, asesorTecnico, presupuesto);
        String sql = "INSERT INTO proyectos (nombre_proyecto, descripcion_tecnica, fecha_inicio, "
                + "fecha_culminacion, asesor_tecnico, presupuesto_asignado) VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conexion = ConexionMySQL.obtenerConexion();
             PreparedStatement sentencia = conexion.prepareStatement(sql)) {
            sentencia.setString(1, proyecto.getNombre());
            sentencia.setString(2, proyecto.getDescripcionTecnica());
            sentencia.setDate(3, Date.valueOf(proyecto.getFechaInicio()));
            sentencia.setDate(4, Date.valueOf(proyecto.getFechaCulminacion()));
            sentencia.setString(5, proyecto.getAsesorTecnico());
            sentencia.setBigDecimal(6, proyecto.getPresupuesto());
            sentencia.executeUpdate();
        } catch (SQLException e) {
            throw new ServletException("No se pudo registrar el proyecto.", e);
        }

        response.sendRedirect(request.getContextPath() + "/ProyectoServlet");
    }

    private String texto(String valor) {
        return valor == null ? "" : valor.trim();
    }
}
