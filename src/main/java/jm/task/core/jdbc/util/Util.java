package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String urlAddress = "jdbc:mysql://localhost:3306/MySql?useSSL=false&serverTimezone=Europe/Moscow";
    private static final String userName = "PreProjectUser";
    private static final String password = "1234";

    public static Connection createConnection() {
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(urlAddress,userName,password);
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            System.err.println(e.getErrorCode());
        }
        return connection;
    }
}
