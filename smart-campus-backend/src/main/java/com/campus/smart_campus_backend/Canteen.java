package com.campus.smart_campus_backend;

public class Canteen {
    private String canteenName;
    private String crowdStatus; // Low, Medium, High
    private boolean isFoodAvailable;
    private boolean isOpen;
    private int occupancyCount; // For ESP32 sensor data
    private double latitude;
    private double longitude;

    public Canteen(String canteenName, String crowdStatus, boolean isFoodAvailable, double latitude, double longitude) {
        this.canteenName = canteenName;
        this.crowdStatus = crowdStatus;
        this.isFoodAvailable = isFoodAvailable;
        this.isOpen = true;
        this.occupancyCount = 0;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public void updateCrowdStatus(String newStatus, boolean foodAvailable) {
        this.crowdStatus = newStatus;
        this.isFoodAvailable = foodAvailable;
    }

    // This method will be called by the MQTT Subscriber later
    public void updateSensorCount(int count) {
        this.occupancyCount = count;
        // Auto-classify based on thresholds
        if (count < 20) this.crowdStatus = "Low";
        else if (count <= 50) this.crowdStatus = "Medium";
        else this.crowdStatus = "High";
    }

    public void displayCanteenInfo() {
        System.out.println("\n--------------------------------------------------");
        System.out.println("Canteen Name  : " + canteenName);
        System.out.println("Location      : Lat " + latitude + ", Lng " + longitude);
        System.out.println("Status        : " + (isOpen ? "OPEN" : "CLOSED"));
        System.out.println("Crowd Level   : " + crowdStatus + " (" + occupancyCount + " people)");
        System.out.println("Food Status   : " + (isFoodAvailable ? "Available" : "Not Available"));
        System.out.println("--------------------------------------------------");
    }

    public String getCanteenName() {
        return canteenName;
    }

    public String getCrowdStatus() {
        return crowdStatus;
    }

    public boolean isFoodAvailable() {
        return isFoodAvailable;
    }

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }
}