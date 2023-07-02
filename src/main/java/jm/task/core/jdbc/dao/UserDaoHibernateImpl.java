package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getFactory();

    public UserDaoHibernateImpl() {

    }

    @Override
    public void createUsersTable() {

        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE IF NOT EXISTS mydbtest.users5" +
                    "(ID INT8 NOT NULL AUTO_INCREMENT PRIMARY KEY," +
                    "NAME VARCHAR(45) NOT NULL," +
                    "LASTNAME VARCHAR(45) NOT NULL," +
                    "AGE TINYINT(255) NOT NULL)").executeUpdate();

            transaction.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();

            session.createSQLQuery("DROP TABLE IF EXISTS mydbtest.users5").executeUpdate();

            transaction.commit();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            System.out.println("User с именем – " + name + " добавлен в базу данных ");

            transaction.commit();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Wrong with SQL query");
            }
            System.err.println("Wrong with saveUser: transaction == null");
        }
    }

    @Override
    public void removeUserById(long id) {
        Transaction transaction = null;
        try(Session session = sessionFactory.openSession()) {
            transaction  = session.beginTransaction();
            try {
                session.delete(session.load(User.class, id));
            } catch (EntityNotFoundException e) {
                System.err.println("Transaction status: " + transaction.getStatus() + " Пользователь не существует");
                return;
            }
            transaction.commit();
        }catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                e.printStackTrace();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        List<User>users = new ArrayList<>();
        try (Session session = sessionFactory.openSession()){
            users = session.createQuery("FROM user_entity_name", User.class).getResultList();
        }
        return users;
    }

    @Override
    public void cleanUsersTable() {
        Transaction transaction = null;
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();

            session.createQuery("DELETE FROM user_entity_name").executeUpdate();

            transaction.commit();
        } catch (HibernateException e) {
            if (transaction != null) {
                transaction.rollback();
                System.err.println("Wrong with SQL query");
                e.printStackTrace();
            }
            System.err.println("Wrong with cleanUserTable: transaction == null");
        }
    }
}
