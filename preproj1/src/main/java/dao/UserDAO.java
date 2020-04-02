package dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDAO {

    private Connection connection;
    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    public void addUser(String name,String password) throws SQLException {
        execUpdate("insert into bank_client (name, password, money) VALUES ('"
                + name + "','"
                + password + "')");
    }
    private void execUpdate(String update) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute(update);
        statement.close();
    }

}
