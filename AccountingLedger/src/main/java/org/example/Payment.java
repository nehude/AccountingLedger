package org.example;

import java.util.Scanner;

public class Payment {
    private String date;
    private String time;
    private String description;
    private String vendor;
    private double amount;

    public Payment(String date, String time, String description, String vendor, double amount) {
        this.date = date;
        this.time = time;
        this.description = description;
        this.vendor = vendor;
        this.amount = amount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public static double getCorrectAmount(Scanner scanner) {
        while (true) {
            System.out.print("Enter amount: ");
            String amountInput = scanner.nextLine();
            try {
                double amount = Double.parseDouble(amountInput);
                if (amount < 0) {
                    System.out.println("Sorry please enter a positive number");
                } else {
                    return amount;
                }
            } catch (NumberFormatException e) {
                System.out.println("Sorry please enter a number.");
            }
        }
    }
    public static String getCorrectDate(Scanner scanner) {
        while (true) {
            System.out.print("Enter date (YYYY-MM-DD): ");
            String dateInput = scanner.nextLine();

            if (dateInput.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return dateInput;
            } else {
                System.out.println("Sorry please enter date in YYYY-MM-DD format.");
            }
        }
    }

    public static String getCorrectTime(Scanner scanner) {
        while (true) {
            System.out.print("Enter time (HH:MM:SS): ");
            String timeInput = scanner.nextLine();

            if (timeInput.matches("\\d{2}:\\d{2}:\\d{2}")) {
                return timeInput;
            } else {
                System.out.println("Sorry please enter time in HH:MM:SS format.");
            }
        }
    }
}