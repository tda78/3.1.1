package Service;

import dao.UserDAO;
import model.User;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;


public class UserService {
    private UserDAO dao = new UserDAO(getMysqlConnection());
    public void addClient(String name, String password) throws SQLException {
        dao.addUser(name, password);
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

    private static UserDAO getBankClientDAO() throws ClassNotFoundException, SQLException, InstantiationException, IllegalAccessException {
        return new UserDAO(getMysqlConnection());
    }

    public static void main(String[] args) throws SQLException {
        UserService service = new UserService();
        service.addClient("123","456");
        service.addClient("12bnmbm3","456jhhgj");

    }

}
