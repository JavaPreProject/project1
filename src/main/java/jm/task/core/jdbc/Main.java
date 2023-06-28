package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

public class Main {
    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Roman","Longer",(byte) 25);
        userService.saveUser("Ivan","Phedorov",(byte) 30);
        userService.saveUser("Viktor","Philippov",(byte) 55);
        userService.saveUser("Dmitriy", "Kopchenkov",(byte) 14);
        System.out.println("\nрузультат работы метода \" toString \" класса User:");
        System.out.println(userService.getAllUsers().toString());

        userService.cleanUsersTable();

        userService.dropUsersTable();


    }
}
