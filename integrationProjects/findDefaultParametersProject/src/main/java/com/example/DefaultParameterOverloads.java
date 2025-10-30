package com.example;

public class DefaultParameterOverloads {

    public void greet(String name, int times, boolean excited) {
        System.out.println(name + times + excited);
    }

    public void greet(String name, int times) {
        greet(name, times, true);
    }

    public void greet(String name) {
        greet(name, 1, false);
    }

    public void greet() {
        greet("world", 2, true);
    }
}
