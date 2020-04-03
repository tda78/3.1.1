package dao;

import model.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    public List<User> getAllUsers() throws SQLException;

    public User getUser(long id) throws SQLException;

    public void addUser(String name, String password) throws SQLException;

    public void updateUser(String id, String name, String password) throws SQLException;

    public void deleteUser(String id) throws SQLException;

}
