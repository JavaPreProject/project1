package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov",(byte) 18);
        userService.saveUser("Peter","Petrov",(byte) 21);
        userService.saveUser("Viktor","Viktorov",(byte) 35);
        userService.saveUser("Nikolay", "Nikolaev",(byte) 40);
        System.out.println("\nрузультат работы метода \" toString \" класса User:");
        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
