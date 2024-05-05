package org.reflection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class App {
    public static void main( String[] args )
    {
        //Properties properties = new Properties();
//        properties.setProperty("org.reflection.interfaces.SomeInterface","org.reflection.classes.SomeImpl");
//        properties.setProperty("org.reflection.interfaces.SomeOtherInterface","org.reflection.classes.SODoer");
//
//        FileOutputStream fos;
//        try{
//            fos = new FileOutputStream("./src/main/java/org/reflection/properties");
//            properties.store(fos," ");
//
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
        //SomeBean sb =(new Injector()).inject(new SomeBean());
        //sb.foo();

        Injector injector = new Injector();
        injector.inject();
    }
}
