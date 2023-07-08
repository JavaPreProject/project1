package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MainApp {
   public static void main(String[] args) {
      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);

      Car car0 = new Car("modelOne",1);
      Car car1 = new Car("modelTwo", 2);
      Car car2 = new Car("modelThree", 3);
      Car car3 = new Car("modelFour", 4);

      userService.add(car0);
      userService.add(car1);
      userService.add(car2);
      userService.add(car3);

      userService.add(new User("User1", "Lastname1", "user1@mail.ru", userService.listCars().get(0)));
      userService.add(new User("User2", "Lastname2", "user2@mail.ru", userService.listCars().get(1)));
      userService.add(new User("User3", "Lastname3", "user3@mail.ru", userService.listCars().get(2)));
      userService.add(new User("User4", "Lastname4", "user4@mail.ru", userService.listCars().get(3)));

      for (User user : userService.listUsers()) {
         System.out.println("id = " + user.getId());
         System.out.println("First Name = " + user.getFirstName());
         System.out.println("Last Name = " + user.getLastName());
         System.out.println("e-mail = " + user.getEmail());
         System.out.println();
      }
      User resultUser = userService.findCarHolder("modelFour", 4);
      System.out.println("\nОтвет: " + resultUser + "\nездит на: " + resultUser.getCar());

      context.close();
   }
}
