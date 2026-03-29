package com.amit;

public class User {
    private String name;
    private String company;
    private User user;

    public User(String name, String company, User user) {
        this.name = name;
        this.company = company;
        this.user = user;
    }

    public String getName() {
        return name;
    }

    public String getCompany() {
        return company;
    }

    public User getUser() {
        return user;
    }
}