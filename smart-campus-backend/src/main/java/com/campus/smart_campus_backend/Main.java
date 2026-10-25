package com.campus.smart_campus_backend;

import java.util.Scanner;
import java.util.ArrayList;

public class Main {

    // --- CUSTOM EXCEPTION FOR SENTINEL COMMANDS ---
    static class CommandException extends RuntimeException {
        String command;
        public CommandException(String command) {
            this.command = command;
        }
    }

    // --- CUSTOM INPUT HANDLER ---
    static class InputHandler {
        private Scanner scanner;

        public InputHandler(Scanner scanner) {
            this.scanner = scanner;
        }

        // Check if the input is a sentinel command
        private void checkSentinel(String input) {
            if (input == null) return;
            String trimmed = input.trim();
            if (trimmed.equalsIgnoreCase("exit")) throw new CommandException("EXIT");
            if (trimmed.equalsIgnoreCase("logout")) throw new CommandException("LOGOUT");
        }

        // Read a line of text
        public String nextLine() {
            String input = scanner.nextLine();
            checkSentinel(input);
            return input.trim();
        }

        // Read an integer safely
        public int nextInt() {
            String input = nextLine();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                return -999; // Indicates invalid number
            }
        }
    }

    // --- MAIN SYSTEM ---
    public static void main(String[] args) {
        InputHandler input = new InputHandler(new Scanner(System.in));

        System.out.println("==========================================================");
        System.out.println("   UNIVERSITY OF KELANIYA - SMART CAMPUS SYSTEM (BACKEND) ");
        System.out.println("==========================================================");

        // --- 1. INITIALIZE DATA ---
        Building f4 = new Building("F4 Building", "F4", 6, 6.9758250, 79.9146031);
        f4.addHall(new Hall("F4-101", "Lecture Hall", "-", "Enter main Ground entrance, head straight past lobby."));

        Building g2 = new Building("G2 Admin Wing", "G2", 3, 6.9758869, 79.9149385);
        g2.addHall(new Hall("G2-003", "Lecture's Offices", "6 Cabins", "End of the hallway on the left."));

        Canteen hilton = new Canteen("Hilton Canteen", "Medium", true, 6.9760629, 79.9156555);
        Canteen gym = new Canteen("Gym Canteen", "Low", true, 6.9742245, 79.9166772);
        Canteen staffCanteen = new Canteen("Staff Canteen", "Low", true, 6.9754761, 79.9155129);

        ArrayList<Lecturer> lecturers = new ArrayList<>();
        lecturers.add(new Lecturer("Mr. Malinda Herath", "Cabin No.4", "G2-003"));
        lecturers.add(new Lecturer("Mr. Naveen Nirupama", "Cabin No.1", "G2-003"));
        lecturers.add(new Lecturer("Ms. Thiruni Jayalath", "Cabin No.2", "G2-003"));
        lecturers.add(new Lecturer("Mr. Viraj Weerawickrama", "Cabin No.5", "G2-004"));

        String adminUser = "admin";
        String adminPass = "admin123";
        boolean isAdminAuthenticated = false;

        // --- 2. MENU LOOP ---
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("\n=================== MAIN NAVIGATION MENU ===================");
            System.out.println("1. View Campus Buildings & Halls");
            System.out.println("2. View Live Canteen Status (All 3)");
            System.out.println("3. View Lecturer Cabin Availability");
            System.out.println("4. Admin Login");
            System.out.println("5. [ADMIN] Update Canteen Crowd Status");
            System.out.println("6. [ADMIN] Simulate ESP32 Sensor Data (IoT)");
            System.out.println("7. [ADMIN] Update Lecturer Cabin Status");
            System.out.println("8. Exit System");
            System.out.println("----------------------------------------------------------");
            System.out.println("💡 Tip: Type \"exit\" to cancel actions, or \"logout\" to end admin session.");
            System.out.print("Please enter your selection (1-8): ");

            try {
                int choice = input.nextInt();

                if (choice == -999) {
                    System.out.println("\n[ERROR] Invalid input! Please enter a number.");
                    continue;
                }

                switch (choice) {
                    case 1:
                        f4.displayBuildingInfo();
                        g2.displayBuildingInfo();
                        break;

                    case 2:
                        hilton.displayCanteenInfo();
                        gym.displayCanteenInfo();
                        staffCanteen.displayCanteenInfo();
                        break;

                    case 3:
                        System.out.println("\n>>> PUBLIC LECTURER CABIN STATUS <<<");
                        for (Lecturer lec : lecturers) {
                            lec.displayLecturerInfo();
                        }
                        break;

                    case 4:
                        System.out.print("Enter Admin Username: ");
                        String u = input.nextLine();
                        System.out.print("Enter Admin Password: ");
                        String p = input.nextLine();
                        if (u.equals(adminUser) && p.equals(adminPass)) {
                            isAdminAuthenticated = true;
                            System.out.println("[SUCCESS] Logged in as Administrator.");
                        } else {
                            System.out.println("[ERROR] Incorrect username or password.");
                        }
                        break;

                    case 5:
                        if (!isAdminAuthenticated) { System.out.println("[DENIED] Please login as Admin first (Option 4)."); break; }
                        System.out.print("Select Canteen (1: Hilton, 2: Gym, 3: Staff): ");
                        int cChoice = input.nextInt();
                        if (cChoice < 1 || cChoice > 3) { System.out.println("[ERROR] Invalid canteen."); break; }

                        System.out.print("Enter New Crowd Level (Low / Medium / High): ");
                        String status = input.nextLine();

                        if (cChoice == 1) hilton.updateCrowdStatus(status, true);
                        else if (cChoice == 2) gym.updateCrowdStatus(status, true);
                        else if (cChoice == 3) staffCanteen.updateCrowdStatus(status, true);

                        System.out.println("[SUCCESS] Admin manually updated crowd status.");
                        break;

                    case 6:
                        if (!isAdminAuthenticated) { System.out.println("[DENIED] Please login as Admin first (Option 4)."); break; }
                        System.out.print("Simulate ESP32 - Enter people count for Gym Canteen: ");
                        int count = input.nextInt();
                        if (count == -999) { System.out.println("[ERROR] Please enter a valid number."); break; }
                        gym.updateSensorCount(count);
                        System.out.println("[SUCCESS] Sensor data ingested. Auto-classified crowd level.");
                        break;

                    case 7:
                        if (!isAdminAuthenticated) { System.out.println("[DENIED] Please login as Admin first (Option 4)."); break; }
                        System.out.print("Enter Lecturer Name (e.g., Mr. Malinda Herath): ");
                        String lecName = input.nextLine();

                        boolean found = false;
                        for (Lecturer lec : lecturers) {
                            if (lec.getName().equalsIgnoreCase(lecName)) {
                                System.out.print("Set Status (1: In Cabin, 2: Away): ");
                                int sChoice = input.nextInt();
                                if (sChoice == 1 || sChoice == 2) {
                                    lec.setCabinStatus(sChoice == 1);
                                    System.out.println("[SUCCESS] Lecturer status updated.");
                                } else {
                                    System.out.println("[ERROR] Invalid choice.");
                                }
                                found = true;
                                break;
                            }
                        }
                        if (!found) System.out.println("[ERROR] Lecturer not found.");
                        break;

                    case 8:
                        System.out.println("\nThank you for using Campus Navigator System. Exiting...");
                        isRunning = false;
                        break;

                    default:
                        System.out.println("\n[ERROR] Invalid choice! Please select between 1 and 8.");
                }
            } catch (CommandException e) {
                // THIS IS WHERE THE SENTINEL MAGIC HAPPENS
                if (e.command.equals("EXIT")) {
                    System.out.println("\n🛑 [COMMAND RECOGNIZED] Action cancelled. Returning to Main Menu...");
                } else if (e.command.equals("LOGOUT")) {
                    if (isAdminAuthenticated) {
                        isAdminAuthenticated = false;
                        System.out.println("\n🔒 [COMMAND RECOGNIZED] Admin session ended. Returning to Main Menu...");
                    } else {
                        System.out.println("\n⚠️ [COMMAND RECOGNIZED] You are not logged in. Returning to Main Menu...");
                    }
                }
                // The `continue` inside the catch block automatically loops back to the top of the while loop
            }
        }
        // (Scanner is closed automatically by the system, not needed with InputHandler)
    }
}