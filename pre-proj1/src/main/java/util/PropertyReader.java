package util;

import java.io.IOException;
import java.util.Properties;

public class PropertyReader {
    static public Properties read() throws IOException {
        Properties properties = new Properties();
        properties.load(PropertyReader.class.getClassLoader().getResourceAsStream("JDBC.properties"));
        return properties;
    }
}
