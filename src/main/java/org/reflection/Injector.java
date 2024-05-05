package org.reflection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Properties;

public class Injector {
    public <T> T inject(T obj) {

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

        Class<?> injectClass = obj.getClass();
        Field[] fields = injectClass.getDeclaredFields();
        for(Field field: fields) {
            if(field.isAnnotationPresent(AutoInjectable.class)) {
                Class<?> fieldType = field.getType();
                try {
                    Object impl = Class.forName(properties.getProperty(fieldType.getName())).newInstance();
                    field.setAccessible(true);
                    field.set(obj,impl);
                } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
                    throw new RuntimeException(e);
                }
            }
        }
        return obj;
    }
}
