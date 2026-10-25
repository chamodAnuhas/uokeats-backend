package com.campus.smart_campus_backend;

// Base Class (Encapsulation Concept)
public class User {
    private String name;
    private String userRole;

    // Constructor
    public User(String name, String userRole) {
        this.name = name;
        this.userRole = userRole;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public String getUserRole() {
        return userRole;
    }

    // Method to display user info
    public void displayProfile() {
        System.out.println("User Name : " + name);
        System.out.println("User Role : " + userRole);
    }
}

