package org.reflection;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class App {
    public static void main( String[] args )
    {

        SomeBean sb =(new Injector()).inject(new SomeBean());
        sb.foo();

    }
}
