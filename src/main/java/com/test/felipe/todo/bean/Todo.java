package com.test.felipe.todo.bean;

public class Todo {

    private String name;
    private int n1;
    private int n2;
    public Todo() {
    }
    public Todo(String name, int n1, int n2) {
        this.name = name;
        this.n1 = n1;
        this.n2 = n2;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getN1() {
        return n1;
    }

    public void setN1(int n1) {
        this.n1 = n1;
    }

    public int getN2() {
        return n2;
    }

    public void setN2(int n2) {
        this.n2 = n2;
    }
}
