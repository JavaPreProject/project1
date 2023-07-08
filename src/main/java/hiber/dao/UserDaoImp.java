package hiber.dao;

import hiber.model.Car;
import hiber.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImp implements UserDao {

   @Autowired
   private SessionFactory sessionFactory;

   @Override
   public void add(User user) {
      sessionFactory.getCurrentSession().save(user);
   }

   @Override
   @SuppressWarnings("unchecked")
   public List<User> listUsers() {
      TypedQuery<User> query=sessionFactory.getCurrentSession().createQuery("FROM User");
      return query.getResultList();
   }

   @Override
   public void add(Car car) {
      sessionFactory.getCurrentSession().save(car);
   }

   @Override
   public List<Car> listCars() {
      TypedQuery<Car> typedQuery = sessionFactory.getCurrentSession().createQuery("FROM Car", Car.class);
      return typedQuery.getResultList();
   }

   @Override
   public User findCarHolder(String carName, int carSeries) {
      Query query = sessionFactory.getCurrentSession().createQuery
                      ("FROM Car WHERE model = :car AND series = :series", Car.class)
              .setParameter("car", carName)
              .setParameter("series", carSeries);
      Car car = (Car) query.getSingleResult();
      if (car != null){
         for (User user : listUsers()) {
            if (user.getCar().equals(car)) {
               return user;
            }
         }
      }
      return null;
   }
}
