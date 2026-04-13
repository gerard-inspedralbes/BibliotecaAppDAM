package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private static final String URL = "jdbc:mysql://localhost:3306/la_meva_bd";
    private static final String USER = "admin";
    private static final String PASSWORD = "1234";

    // Constructor privat
    private DBConnection() {
        try {
            this.connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Connexió establerta correctament");
        } catch (SQLException e) {
            throw new RuntimeException("Error connectant a la BBDD", e);
        }
    }

    // Punt d'accés global
    public static DBConnection getInstance() {
        if (instance == null) {
            instance = new DBConnection();
        }
        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}