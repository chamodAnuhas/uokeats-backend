package com.campus.smart_campus_backend;

// Subclass inheriting from User (Inheritance Concept)
public class Student extends User {
    private String studentId;
    private String faculty;

    // Constructor
    public Student(String name, String studentId, String faculty) {
        super(name, "Student"); // Calling parent class constructor
        this.studentId = studentId;
        this.faculty = faculty;
    }

    public String getStudentId() {
        return studentId;
    }

    // Method Overriding (Polymorphism Concept)
    @Override
    public void displayProfile() {
        super.displayProfile();
        System.out.println("Student ID: " + studentId);
        System.out.println("Faculty   : " + faculty);
    }
}