package org.reflection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;

public class Injector {
    public void inject() {

        Properties properties = new Properties();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream("./src/main/java/org/reflection/properties");
            properties.load(fis);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            try {
                Objects.requireNonNull(fis).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        try {
            System.out.println(Class.forName(properties.getProperty("org.reflection.interfaces.SomeInterface")).newInstance());
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
