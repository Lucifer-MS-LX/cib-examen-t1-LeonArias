package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionMySQL {
    private static final String URL = "jdbc:mysql://localhost:3306/proyectos_inversion_publica";
    private static final String USUARIO = "proyectos_app";
    private static final String CONTRASENA = "DemoProyectos2026!";

    public static Connection obtenerConexion() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("No se encontró el driver JDBC de MySQL.", e);
        }
        return DriverManager.getConnection(URL, USUARIO, CONTRASENA);
    }
}
