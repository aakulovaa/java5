package org.reflection;

public class App {
    public static void main( String[] args )
    {

        SomeBean sb =(new Injector()).inject(new SomeBean());
        sb.foo();

    }
}
