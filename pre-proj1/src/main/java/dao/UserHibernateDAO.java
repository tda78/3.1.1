package dao;

import model.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;

import java.util.List;

public class UserHibernateDAO implements UserDAO {

    private static SessionFactory sessionFactory;

    public UserHibernateDAO(){
        sessionFactory = getSessionFactory();
    }
    @Override
    public List<User> getAllUsers() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.close();
        return users;
    }

    @Override
    public User getUser(long id) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = (User) session.get(User.class, id);
        transaction.commit();
        session.close();
        return user;
    }

    @Override
    public void addUser(String name, String password) {
        User user = new User(name, password);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(user);
        transaction.commit();
        session.close();
    }

    @Override
    public void updateUser(String id, String name, String password) {
        User user = new User(Long.parseLong(id), name, password);
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.update(user);

        transaction.commit();
        session.close();

    }

    @Override
    public void deleteUser(String id) {
        User user = getUser(Long.parseLong(id));
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(user);
        transaction.commit();
        session.close();
    }

    private  SessionFactory getSessionFactory (){
        if(sessionFactory == null){
            sessionFactory = DBHelper.getSessionFactory();
        }
        return sessionFactory;
    }

}
