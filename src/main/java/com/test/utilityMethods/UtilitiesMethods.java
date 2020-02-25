package com.test.utilityMethods;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
/*
 * This class, method can be called to read the *.properties file from any method.
 */
public class UtilitiesMethods {

    public static Properties getPropertiesFile(String propertiesFileName) {
        InputStream inputStream = null;
        Properties properties = null;
        try {
            properties = new Properties();
            inputStream = UtilitiesMethods.class.getClassLoader().getResourceAsStream(propertiesFileName);
            if (inputStream != null) {
                properties.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propertiesFileName + "' not found in the classpath");
            }
        } catch (Exception e) {
            System.out.println("Exception: " + e);
        } finally {
            try {
                if (!(inputStream == null)) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return properties;
    }

}
