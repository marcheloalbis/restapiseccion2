package util;

import java.io.*;
import java.util.Properties;

public class SetProperties {

    public void guardarPropiedades(String user,String password) throws IOException {
        Properties properties = new Properties();

        // In the name of userCreated.properties, in the
        // current directory location, the file is created

        FileOutputStream fileOutputStream
                = new FileOutputStream(
                "qa.properties");

        // As an example, given steps how
        // to keep username and password
        properties.setProperty("host", "http://todo.ly");
        properties.setProperty("user", user);
        properties.setProperty("password", password);

        // writing properites into properties file
        // from Java As we are writing text format,
        // store() method is used
        properties.store(
                fileOutputStream,
                "Sample way of creating Properties file from Java program");

        fileOutputStream.close();
    }
}
