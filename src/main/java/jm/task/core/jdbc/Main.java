package jm.task.core.jdbc;

import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

public class Main {
    public static void main(String[] args) {
         //реализуйте алгоритм здесь
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan","Ivanov",(byte) 18);
        userService.saveUser("Peter","Petrov",(byte) 21);
        userService.saveUser("Viktor","Viktorov",(byte) 35);
        userService.saveUser("Nikolay", "Nikolaev",(byte) 40);
        userService.removeUserById(1);

        System.out.println("\nрузультат работы метода \" toString \" класса User: \n" +
                userService.getAllUsers().toString() + "\n");
        userService.cleanUsersTable();
        userService.dropUsersTable();

        //Util.closeConnectionJDBC();

    }
}
