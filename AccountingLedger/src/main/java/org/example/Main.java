package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean isRunning = true;

        while (isRunning) {
            displayMenu();
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "D":
                    addDeposit(scanner);
                    break;
                case "P":
                    //makePayment(scanner);
                    break;
                case "L":
                    //displayLedger(scanner);
                    break;
                case "X":
                    System.out.println("Thank you. Goodbye :)");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Not a valid input. Please try again.");
            }
        }
    }

    public static void displayMenu() {
        System.out.println("Welcome to the Accounting Home Screen");
        System.out.println("What would you like to do today?");
        System.out.println("D) Add Deposit");
        System.out.println("P) Make Payment (Debit)");
        System.out.println("L) Ledger");
        System.out.println("X) Exit");
        System.out.println("Please enter D, P, L, or X: ");
    }

    public static void addDeposit(Scanner scanner) {
        boolean deposit = true;

        while (deposit) {
            String date = Deposit.getCorrectDate(scanner);
            String time = Deposit.getCorrectTime(scanner);

            System.out.print("Enter description: ");
            String description = scanner.nextLine();
            System.out.print("Enter vendor: ");
            String vendor = scanner.nextLine();

            double amount = Deposit.getCorrectAmount(scanner);

            Deposit newDeposit = new Deposit(date, time, description, vendor, amount);

            try (FileWriter fileWriter = new FileWriter("src/main/resources/transactions.csv", true)) {
                fileWriter.write(String.format("%s|%s|%s|%s|%.2f\n",
                        newDeposit.getDate(), newDeposit.getTime(), newDeposit.getDescription(),
                        newDeposit.getVendor(), newDeposit.getAmount()));
                System.out.println("Deposit added successfully.");
            } catch (IOException ex) {
                System.out.println("Could not deposit.");
                ex.printStackTrace();
            }

            System.out.print("Do you want to make another deposit? Enter Y or N: ");
            String response = scanner.nextLine().toUpperCase();
            if (!response.equals("Y")) {
                deposit = false;
            }
        }
    }
}