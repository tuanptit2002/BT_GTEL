package org.example;

public class User {
    private String id;
    private String ma;
    private String fullName;
    private int age;
    private String number;

    public User(String id, String ma, String fullName, int age, String number) {
        this.id = id;
        this.ma = ma;
        this.fullName = fullName;
        this.age = age;
        this.number = number;
    }

    @Override
    public String toString() {
        return "- " + id  +","+ ma +","+fullName + "," + age +","+number ;
    }
}
