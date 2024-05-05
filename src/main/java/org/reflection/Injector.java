package org.reflection;

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Objects;
import java.util.Properties;

/**
 * Класс, осуществляющий внедрение зависимостей
 * в любой объект, помеченный аннотацией
 */
public class Injector {
    /**
     * Метод внедрения зависимостей
     * @param obj - объект, в который происходит внедрение зависимотей
     * @return вернет объект с внедренными зависимостями
     * @param <T> - тип внедряемого объекта
     */
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
