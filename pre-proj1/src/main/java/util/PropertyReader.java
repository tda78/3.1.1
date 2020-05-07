package util;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    static public Properties readConnectionProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyReader.class.getClassLoader().getResourceAsStream("JDBC.properties"));
        return properties;
    }
    static public Properties readConfigurationProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyReader.class.getClassLoader().getResourceAsStream("hibernate.properties"));
        return properties;
    }
    static public Properties readDaoTypeProperties() throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyReader.class.getClassLoader().getResourceAsStream(".properties"));
        return properties;
    }
}
