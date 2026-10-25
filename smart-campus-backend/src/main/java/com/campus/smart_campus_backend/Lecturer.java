package com.campus.smart_campus_backend;

public class Lecturer {
    private String name;
    private String cabinNumber;
    private String hallName;
    private boolean isInCabin;

    public Lecturer(String name, String cabinNumber, String hallName) {
        this.name = name;
        this.cabinNumber = cabinNumber;
        this.hallName = hallName;
        this.isInCabin = false;
    }

    public String getName() {
        return name;
    }

    public void setCabinStatus(boolean status) {
        this.isInCabin = status;
    }

    public void displayLecturerInfo() {
        System.out.println("Lecturer: " + name + " | " + cabinNumber + " (" + hallName + ") | Status: " + (isInCabin ? "🟢 In Cabin" : "🔴 Away"));
    }
}