package org.example;

public class Foo {
    private int x;
    private String name;

    public Foo(int x, String name) {
        this.x = x;
        this.name = name;
    }
    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
