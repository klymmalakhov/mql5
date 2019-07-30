package properties;

import java.io.IOException;
import java.io.InputStream;


public class PropertyHolder {

    public static String getPropValue(String propertyName) {
        String namePropertyFile = "mql5.properties";
        InputStream inputStream = PropertyHolder.class.getClassLoader().getResourceAsStream(namePropertyFile);
        java.util.Properties prop = new java.util.Properties();
        String name = propertyName.replaceAll(" ", "_");
        try {
            prop.load(inputStream);
            return prop.getProperty(name);
        } catch (IOException e) {
            String errorMessage = "Can not find property name '" + propertyName + "'. Check it please.\n";
            throw new RuntimeException(errorMessage + e);
        }
    }
}
