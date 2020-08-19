import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesManager {

    public static String getProperty(String key) {
        InputStream resourceAsStream = PropertiesManager.class.getClassLoader().getResourceAsStream("config.properties");

        Properties properties = new Properties();
        try {
            properties.load(resourceAsStream);
        } catch (IOException e) {
            System.out.println("Couldn't find property for given name: " + key);
        }

        return properties.getProperty(key);
    }
}
