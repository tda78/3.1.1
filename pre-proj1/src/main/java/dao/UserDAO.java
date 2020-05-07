package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers() throws SQLException;

    public User getUserByName(String name) throws SQLException;

    public User getUser(long id) throws SQLException;

    public void addUser(User user) throws SQLException;

    public void updateUser(User user) throws SQLException;

    public void deleteUser(String id) throws SQLException;

}
