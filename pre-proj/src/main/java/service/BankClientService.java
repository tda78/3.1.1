package service;

import dao.BankClientDAO;
import exception.DBException;
import model.BankClient;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class BankClientService {
    BankClientDAO dao = getBankClientDAO();

    public BankClientService() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
    }

    public BankClient getClientById(long id) throws DBException {
        try {
            return dao.getClientById(id);
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public BankClient getClientByName(String name) {
        try {
            return dao.getClientByName(name);
        } catch (SQLException e) {
            return null;
        }
    }

    public List<BankClient> getAllClient() {
        try {
            return dao.getAllBankClient();
        } catch (SQLException e) {
            return null;
        }
    }

    public boolean deleteClient(String name) throws DBException, SQLException {

        dao.deleteClientByName(name);
        return true;
    }

    public boolean addClient(BankClient client) throws DBException, SQLException {
        try {
            dao.getClientByName(client.getName());
            return false;
        } catch (Exception e) {
            try {
                dao.addClient(client);
                return true;
            } catch (Exception ex) {
                return false;
            }
        }
    }

    public boolean sendMoneyToClient(BankClient sender, String name, Long value) throws SQLException {
        try {
            dao.getConnection().setAutoCommit(false);
            BankClient getter = dao.getClientByName(name);
            dao.updateClientsMoney(
                    sender.getName(),
                    sender.getPassword(),
                    -value);
            dao.updateClientsMoney(
                    getter.getName(),
                    getter.getPassword(),
                    value);
            dao.getConnection().commit();
        } catch (Exception e) {
            dao.getConnection().rollback();
            return false;
        } finally {
            dao.getConnection().setAutoCommit(true);
        }
        return true;
    }

    public void cleanUp() throws DBException {

        try {
            dao.dropTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    public void createTable() throws DBException {

        try {
            dao.createTable();
        } catch (SQLException e) {
            throw new DBException(e);
        }
    }

    private static Connection getMysqlConnection() {
        try {
            DriverManager.registerDriver((Driver) Class.forName("com.mysql.jdbc.Driver").newInstance());

            StringBuilder url = new StringBuilder();

            url.
                    append("jdbc:mysql://").        //db type
                    append("localhost:").           //host name
                    append("3306/").                //port
                    append("tsk-2").           //db name
                    append("?user=root").           //login
                    append("&password=admin").      //password
                    append("&serverTimezone=UTC");  //setup server time

            System.out.println("URL: " + url + "\n");

            Connection connection = DriverManager.getConnection(url.toString());
            return connection;
        } catch (SQLException | InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            e.printStackTrace();
            throw new IllegalStateException();
        }
    }

    private static BankClientDAO getBankClientDAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new BankClientDAO(getMysqlConnection());
    }
}
