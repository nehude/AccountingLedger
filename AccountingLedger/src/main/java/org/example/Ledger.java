package org.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    private static ArrayList<Transaction> transactions = new ArrayList<>();
    private static ArrayList<Deposit> deposits = new ArrayList<>();
    private static ArrayList<Payment> payments = new ArrayList<>();


    public static void displayLedger(Scanner scanner) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Ledger Options:");
            System.out.println("A) All Transactions");
            System.out.println("D) Display Deposits");
            System.out.println("P) Display Payments");
            System.out.println("R) View Reports");
            System.out.println("H) Return to Home Screen");
            System.out.println("Please enter A, D, P, R, or H: ");
            String choice = scanner.nextLine().toUpperCase();

            switch (choice) {
                case "A":
                    loadTransactions();
                    displayTransactions();
                    break;
                case "D":
                    loadTransactions();
                    displayDeposits();
                    break;
                case "P":
                    loadTransactions();
                    displayPayments();
                    break;
                case "R":
                    Reports.displayReports(transactions, scanner);
                    break;
                case "H":
                    isRunning = false;
                    break;
                default:
                    System.out.println("Not a valid choice, try again.");
            }
        }
    }

    private static void loadTransactions() {
        transactions.clear();
        deposits.clear();
        payments.clear();

        try (FileInputStream fis = new FileInputStream("src/main/resources/transactions.csv");
             Scanner scanner = new Scanner(fis)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split("\\|");

                if (parts.length == 5) {
                    String date = parts[0];
                    String time = parts[1];
                    String description = parts[2];
                    String vendor = parts[3];
                    double amount = Double.parseDouble(parts[4]);

                    Transaction transaction = new Transaction(date, time, description, vendor, amount);

                    if (amount >= 0) {
                        deposits.add(new Deposit(date, time, description, vendor, amount));
                    } else {
                        payments.add(new Payment(date, time, description, vendor, amount));
                    }

                    transactions.add(transaction);
                } else {
                    System.out.println("Error in line: " + line);
                }
            }
        } catch (FileNotFoundException ex) {
            System.out.println("File not found.");
        } catch (IOException ex) {
            System.out.println("Error reading file.");
        }
    }


    public static void displayTransactions() {
        for (Transaction transaction : transactions) {
            System.out.printf("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f\n",
                    transaction.getDate(), transaction.getTime(), transaction.getDescription(),
                    transaction.getVendor(), transaction.getAmount());
        }
    }

    public static void displayDeposits() {
        for (Deposit deposit : deposits) {
            System.out.printf("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f\n",
                    deposit.getDate(), deposit.getTime(), deposit.getDescription(),
                    deposit.getVendor(), deposit.getAmount());
        }
    }
    public static void displayPayments() {
        for (Payment payment : payments) {
            System.out.printf("Date: %s, Time: %s, Description: %s, Vendor: %s, Amount: %.2f\n",
                    payment.getDate(), payment.getTime(), payment.getDescription(),
                    payment.getVendor(), payment.getAmount());
        }
    }
}
