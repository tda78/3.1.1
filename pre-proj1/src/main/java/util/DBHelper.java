package util;

import model.User;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;


public class DBHelper {
    private DBHelper dbHelper;

    private DBHelper() {
    }

    public DBHelper getInstance() {
        if (dbHelper == null) dbHelper = new DBHelper();
        return dbHelper;
    }

    public static Connection getConnection() throws SQLException, IOException, ClassNotFoundException {

        Properties properties = PropertyReader.readConnectionProperties();
        final String uRL = properties.getProperty("db.Url");
        final String login = properties.getProperty("db.Login");
        final String password = properties.getProperty("db.Password");
        final String driver = properties.getProperty("db.Driver");
        Class.forName(driver);
        Connection connection = DriverManager.getConnection(uRL, login, password);
        //Connection connection = DriverManager.getConnection(url.toString());

        return connection;

    }

    public static Configuration configuration() {
        return null;
    }

    private static SessionFactory sessionFactory;

    public static SessionFactory getSessionFactory() throws IOException {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }


    @SuppressWarnings("UnusedDeclaration")
    private static Configuration getMySqlConfiguration() throws IOException {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        Properties properties = PropertyReader.readConfigurationProperties();

        for (String propertyName : properties.stringPropertyNames()) {
            configuration.setProperty(propertyName, properties.getProperty(propertyName));
        }

/*
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/task2?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "admin");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");*/
        return configuration;
    }

    private static SessionFactory createSessionFactory() throws IOException {
        Configuration configuration = getMySqlConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
