package com.campus.smart_campus_backend;

public class Hall {
    private String name;
    private String type;
    private String capacity;
    private String directions;

    public Hall(String name, String type, String capacity, String directions) {
        this.name = name;
        this.type = type;
        this.capacity = capacity;
        this.directions = directions;
    }

    public void displayHallInfo() {
        System.out.println("      -> Hall: " + name + " [" + type + "]");
        System.out.println("         Capacity: " + capacity);
        System.out.println("         Directions: " + directions);
    }
}
