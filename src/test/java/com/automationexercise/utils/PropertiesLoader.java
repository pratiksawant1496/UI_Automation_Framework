package com.automationexercise.utils;

//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.util.Properties;
//
//public class PropertiesLoader {
//
//    public static String loadProperty(String propertyName) throws IOException {
//
//        InputStream inputStream = new FileInputStream("src/test/resources/config.properties");
//        Properties properties = new Properties();
//        properties.load(inputStream);
//        return properties.getProperty(propertyName);
//    }
//}


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesLoader {
    private static final String CONFIG_FILE = "src/test/resources/config.properties";
    private static Properties properties = new Properties();

    static {
        try (InputStream inputStream = new FileInputStream(CONFIG_FILE)) {
            properties.load(inputStream);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load config.properties", e);
        }
    }

    public static String loadProperty(String propertyName) {
        // Allow Jenkins/Maven -D overrides for browser only
        if ("browser.name".equals(propertyName)) {
            String systemValue = System.getProperty(propertyName);
            if (systemValue != null && !systemValue.isEmpty()) {
                return systemValue;
            }
        }
        // Always fallback to config.properties
        return properties.getProperty(propertyName);
    }
}
