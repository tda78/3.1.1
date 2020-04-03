package service;
import dao.UserDAO;
import dao.UserHibernateDAO;
import dao.UserJdbcDAO;
import exception.DBException;
import model.User;

import java.sql.SQLException;
import java.util.List;


public class UserService {

    private static UserDAO dao;

    static {
        try {
            dao = new UserHibernateDAO();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static UserService userService;

    private UserService() throws Exception {
    }

    public static UserService getInstance() throws Exception {
        if(userService==null) userService = new UserService();
        return userService;
    }

    public List<User> getAllUsers() throws SQLException {
        return dao.getAllUsers();
    }

    public User getUser(long id) throws SQLException {
        return dao.getUser(id);
    }

    public void deleteUser(String id) throws DBException, SQLException {

        dao.deleteUser(id);

    }

    public void addUser(String name, String password) throws SQLException {
        dao.addUser(name, password);
    }

    public void updateUser(String id, String name, String password) throws SQLException {
        dao.updateUser(id, name, password);
    }

      public static void main(String[] args) throws Exception {
         UserService service = new UserService();
    //       service.addUser("qwe","jkjhhj");
    //       service.addUser("q76867we","jkjh9789hj");
    // service.deleteUser("3");
    //  service.updateUser("6", "Vasya", "Pupkin");
        List<User> users = service.getAllUsers();
        for (User user:users
             ) {
            System.out.println(user.getId() +" "+user.getName() + " "+user.getPassword());
        }

    }


}

