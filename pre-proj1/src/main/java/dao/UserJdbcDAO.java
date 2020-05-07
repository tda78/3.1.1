package dao;


import model.User;
import util.DBHelper;
import util.PropertyReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserJdbcDAO implements UserDAO {

    private static Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public UserJdbcDAO() throws Exception {
        this.connection = getMysqlConnection();
    }

    public List<User> getAllUsers() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users");
        ResultSet result = statement.getResultSet();
        List<User> users = new ArrayList<>();
        while (!result.isLast()) {
            result.next();
            users.add(new User(
                    result.getLong(1),
                    result.getString(2),
                    result.getString(3),
                    result.getString(4))
            );
        }
        result.close();
        statement.close();
        return users;
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users where name='" + name + "'");
        ResultSet result = statement.getResultSet();
        result.next();
        User user = new User(
                result.getLong("id"),
                result.getString("name"),
                result.getString("password"),
                result.getString("role")
        );
        result.close();
        return user;
    }

    public User getUser(long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users where id='" + id + "'");
        ResultSet result = statement.getResultSet();
        result.next();
        User user = new User(
                result.getLong("id"),
                result.getString("name"),
                result.getString("password"),
                result.getString("role")
        );
        result.close();
        return user;
    }

    @Override
    public void addUser(User user) throws SQLException {
        execUpdate("insert into users (name, password, role) VALUES ('"
                + user.getName() + "','"
                + user.getPassword() + "','"
                + user.getRole() + "');");
    }

    @Override
    public void updateUser(User user) throws SQLException {
        execUpdate("update users set name='" + user.getName()
                + "',password='" + user.getPassword()
                + "',role='" + user.getRole()
                + "' where id = '" + user.getId() + "';");

    }



    public void updateUser(String id, String name, String password, String role) throws SQLException {
        execUpdate("update users set name='" + name
                + "',password='" + password
                + "',role='" + role
                + "' where id = '" + id + "';");
    }

    public void deleteUser(String id) throws SQLException {
        execUpdate("delete from users where id=" + id + ";");
    }

    private void execUpdate(String update) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute(update);
        statement.close();
    }

    private static Connection getMysqlConnection() throws Exception {
        return DBHelper.getConnection();
    }
}
