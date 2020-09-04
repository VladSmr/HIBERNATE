package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {

    public UserDaoHibernateImpl() {
    }

    // создать таблицу
    @Override
    public void createUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("CREATE TABLE Users (id BIGINT PRIMARY KEY AUTO_INCREMENT, name VARCHAR(255), lastname VARCHAR(255), age INT)").executeUpdate();
            tx.commit();
            System.out.println("Table was created successfully");
        } catch (Exception e) {
            System.out.println("**** got some exception creating table");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // удалить всю таблицу
    @Override
    public void dropUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("DROP TABLE Users").executeUpdate();
            tx.commit();
            System.out.println("Table was dropped successfully");
        } catch (Exception e) {
            System.out.println("**** got some exception dropping table");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // сохранить юзера
    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.save(new User(name, lastName, age));
            tx.commit();
            System.out.println("User с именем – " + name + " добавлен в базу данных");
        } catch (Exception e) {
            System.out.println("**** got some exception saving user");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // удалить юзера по ид
    @Override
    public void removeUserById(long id) {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.delete(session.load(User.class, id));
            tx.commit();
            System.out.println("User was removed successfully");
        } catch (Exception e) {
            System.out.println("**** got some exception removing user");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }

    // получить всех юзеров
    @Override
    public List<User> getAllUsers() {
        Session session = null;
        Transaction tx = null;
        List<User> allUsers = null;
        try {
            session = Util.getSessionFactory().openSession();
            allUsers = session.createQuery("FROM User").list();
            System.out.println("All users were got successfully");
        } catch (Exception e) {
            System.out.println("**** got some exception getting all users");
            e.printStackTrace();
        } finally {
            session.close();
        }
        return allUsers;
    }

    // очистить таблицу
    @Override
    public void cleanUsersTable() {
        Session session = null;
        Transaction tx = null;
        try {
            session = Util.getSessionFactory().openSession();
            tx = session.beginTransaction();
            session.createSQLQuery("DELETE FROM Users").executeUpdate();
            tx.commit();
            System.out.println("Table was cleaned successfully");
        } catch (Exception e) {
            System.out.println("**** got some exception getting all users");
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}