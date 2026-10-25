package com.campus.smart_campus_backend;

import java.util.ArrayList;

public class Building {
    private String buildingName;
    private String buildingCode;
    private int totalFloors;
    private double latitude;
    private double longitude;
    private ArrayList<Hall> halls; // Changed from String to Hall

    public Building(String buildingName, String buildingCode, int totalFloors, double latitude, double longitude) {
        this.buildingName = buildingName;
        this.buildingCode = buildingCode;
        this.totalFloors = totalFloors;
        this.latitude = latitude;
        this.longitude = longitude;
        this.halls = new ArrayList<>();
    }

    public void addHall(Hall hall) {
        halls.add(hall);
    }

    public void displayBuildingInfo() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Building : " + buildingName + " [" + buildingCode + "]");
        System.out.println("Location : Lat " + latitude + ", Lng " + longitude);
        System.out.println("Floors   : " + totalFloors);
        System.out.println("Halls    :");
        for (Hall hall : halls) {
            hall.displayHallInfo();
        }
        System.out.println("--------------------------------------------------");
    }
}