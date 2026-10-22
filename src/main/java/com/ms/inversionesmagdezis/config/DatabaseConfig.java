package main.java.com.ms.inversionesmagdezis.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {

    // Actualizamos el nombre exacto de tu base de datos aquí
    private static final String URL = "jdbc:mysql://localhost:3306/inversiones_magdezis_in4bv";
    private static final String USER = "root"; 
    private static final String PASSWORD = "%IndiVA4"; 

    public DatabaseConfig() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Conexión a la base de datos exitosa.");
        } catch (SQLException e) {
            System.out.println("Error al conectar a la base de datos: " + e.getMessage());
        }
        return connection;
    }
}
