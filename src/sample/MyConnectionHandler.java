package sample;

import sun.security.util.Password;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static javax.swing.Action.NAME;
import static sun.plugin.javascript.navig.JSType.URL;

public class MyConnectionHandler {

    private static final String URL = "jdbc:mysql://127.0.0.1:3306/kcs";

    private static final String LOGIN_NAME = "root";

    private static final String PASSWORD = "";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, LOGIN_NAME, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Neprisijunge " + e);
        }
        return connection;
    }



}
