package dao;


import model.BankClient;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BankClientDAO {

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public BankClientDAO(Connection connection) throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        this.connection = connection;
    }

    public List<BankClient> getAllBankClient() throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from bank_client");
        ResultSet result = statement.getResultSet();
        List<BankClient> clients = new ArrayList<>();
        while (!result.isLast()) {
            result.next();
            clients.add(new BankClient(
                    result.getLong("id"),
                    result.getString("name"),
                    result.getString("password"),
                    result.getLong("money")
            ));
        }
        result.close();
        statement.close();
        return clients;
    }

    public boolean validateClient(String name, String password) throws SQLException {
        return getClientByName(name).getPassword().equals(password);
    }

    public void updateClientsMoney(String name, String password, Long transactValue) throws SQLException {

        if (!validateClient(name, password)) {
            throw new SQLException();
        }

        BankClient client = getClientByName(name);
        if (!isClientHasSum(name, -transactValue)) {
            throw new SQLException();
        }
        PreparedStatement statement = connection.prepareStatement(
                "update bank_client set money=? where name=?");
        statement.setLong(1, client.getMoney() + transactValue);
        statement.setString(2, name);
        statement.executeUpdate();
        statement.close();


    }


    public BankClient getClientById(long id) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from bank_client where id='" + id + "'");
        ResultSet result = statement.getResultSet();
        result.next();
        BankClient client = new BankClient(
                result.getLong("id"),
                result.getString("name"),
                result.getString("password"),
                result.getLong("money")
        );
        result.close();
        return client;

    }

    public boolean isClientHasSum(String name, Long expectedSum) throws SQLException {
        return getClientByName(name).getMoney() >= expectedSum;
    }

    public long getClientIdByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from bank_client where name='" + name + "'");
        ResultSet result = statement.getResultSet();
        result.next();
        Long id = result.getLong("id");
        result.close();
        return id;
    }

    public BankClient getClientByName(String name) throws SQLException {
        Statement statement = connection.createStatement();
        statement.execute("select * from bank_client where name='" + name + "'");
        ResultSet result = statement.getResultSet();
        result.next();
        BankClient client = new BankClient(
                result.getLong("id"),
                result.getString("name"),
                result.getString("password"),
                result.getLong("money"));
        return client;
    }

    public void addClient(BankClient client) throws SQLException {
        execUpdate("insert into bank_client (name, password, money) VALUES ('"
                + client.getName() + "','"
                + client.getPassword() + "',"
                + client.getMoney() + ")");
    }

    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("create table if not exists bank_client (id bigint auto_increment, name varchar(256), password varchar(256), money bigint, primary key (id))");
        stmt.close();
    }

    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS bank_client");
        stmt.close();
    }

    public void deleteClientByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("delete from bank_client where name='" + name + "'");
        stmt.close();
    }

    private int execUpdate(String update) throws SQLException {
        Statement statement = this.connection.createStatement();
        statement.execute(update);
        int updated = statement.getUpdateCount();
        statement.close();
        return updated;
    }
}
