package com.example.phonecallapp;

public class Person {

    private String name;
    private String number;
    private String gender;

    public Person(String name, String number, String gender) {
        this.name = name;
        this.number = number;
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
