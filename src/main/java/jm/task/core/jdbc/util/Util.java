package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class Util {
    // JDBC
    private static final String urlAddress = "jdbc:mysql://localhost:3306/MySql"; //?useSSL=false&serverTimezone=Europe/Moscow";
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
    public static void closeConnectionJDBC() {
        try {
            connection.close();
        } catch (SQLException e) {
            System.err.println("Wrong with closeConnectionJDBC " + e.getErrorCode());
        }
    }
    //Hibernate
    public static SessionFactory getFactory () {

        Properties settings = new Properties();
        settings.put(Environment.URL, urlAddress);
        settings.put(Environment.USER, userName);
        settings.put(Environment.PASS, password);
        settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");
        settings.put(Environment.SHOW_SQL, "true");

        Configuration configuration = new Configuration();
        configuration.setProperties(settings);
        configuration.addAnnotatedClass(User.class);

        StandardServiceRegistry standardServiceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        SessionFactory sessionFactory = configuration.buildSessionFactory(standardServiceRegistry);

        System.out.println("Session Ok!");
        return sessionFactory;
    }
}
