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
                    Deposit.addDeposit(scanner);
                    break;
                case "P":
                    Payment.makePayment(scanner);
                    break;
                case "L":
                    Ledger.displayLedger(scanner);
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
        System.out.println("L) View Ledger");
        System.out.println("X) Exit");
        System.out.println("Please enter D, P, L, or X: ");
    }
}


