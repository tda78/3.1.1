package dao;


import model.User;
import util.PropertyReader;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class UserJdbcDAO implements UserDAO{

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
                    result.getString(3)));
        }
        result.close();
        statement.close();
        return users;
    }

  /*  public boolean validateClient(String name, String password) throws SQLException {
        return getClientByName(name).getPassword().equals(password);
    }*/

    public User getUser(long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from users where id='" + id + "'");
        ResultSet result = statement.getResultSet();
        result.next();
        User user = new User(
                result.getLong("id"),
                result.getString("name"),
                result.getString("password")
        );
        result.close();
        return user;
    }

    public void addUser(String name, String password) throws SQLException {
        execUpdate("insert into users (name, password) VALUES ('"
                + name + "','"
                + password + "');");
    }

    public void updateUser(String id, String name, String password) throws SQLException {
        execUpdate("update users set name='" + name
                +"',password='" + password+
                "' where id = '" + id + "';");
    }

    public void deleteUser(String id) throws SQLException {
        execUpdate("delete from users where id=" + id +";");
    }

    private void execUpdate(String update) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute(update);
        statement.close();
    }
    private static Connection getMysqlConnection() throws Exception {
        try {
            Properties properties = PropertyReader.read();
            final String uRL = properties.getProperty("db.Url");
            final String login = properties.getProperty("db.Login");
            final String password = properties.getProperty("db.Password");
            final String driver = properties.getProperty("db.Driver");
            Class.forName(driver);
            connection = DriverManager.getConnection(uRL, login, password);
            //Connection connection = DriverManager.getConnection(url.toString());

            return connection;
        } catch (SQLException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw e;
        }
    }
}
