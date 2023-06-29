package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // JDBC
    private static final String urlAddress = "jdbc:mysql://localhost:3306/MySql?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String userName = "PreProjectUser";
    private static final String password = "1234";
    private static Connection connection = null;

    public static Connection createConnection() {

        try {
            connection = DriverManager.getConnection(urlAddress, userName, password);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e.getErrorCode());
        }
        return connection;
    }
    public static void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Wrong with closeConnection " + e.getErrorCode());
        }
    }
}
