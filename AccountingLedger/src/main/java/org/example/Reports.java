package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Reports {
    public static void displayReports(ArrayList<Transaction> transactions, Scanner scanner) {
        boolean isRunning = true;

        while (isRunning) {
            System.out.println("Which report would you like to view?");
            System.out.println("1) Month To Date Report");
            System.out.println("2) Previous Month Report");
            System.out.println("3) Year To Date Report");
            System.out.println("4) Previous Year Report");
            System.out.println("5) Search by Vendor");
            System.out.println("0) Back");
            System.out.print("Please enter 1, 2, 3, 4, 5, or 0: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    monthToDate(transactions);
                    break;
                case 2:
                    previousMonth(transactions);
                    break;
                case 3:
                    yearToDate(transactions);
                    break;
                case 4:
                    previousYear(transactions);
                    break;
                case 5:
                   searchByVendor(transactions, scanner);
                    break;
                case 0:
                    isRunning = false;
                    break;
                default:
                    System.out.println("Not a valid option, please try again.");
            }
        }
    }

    public static void monthToDate(ArrayList<Transaction> transactions) {
        int currentMonth = LocalDate.now().getMonthValue();
        int currentYear = LocalDate.now().getYear();

        double totalDeposit = 0;
        double totalPayment = 0;

        System.out.println("Month-to-Date Report:");

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate());
            int month = date.getMonthValue();
            int year = date.getYear();

            if (month == currentMonth && year == currentYear) {
                String category = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                String formattedTransaction = String.format("%s|%s|%s|%s|%s|%s",
                        transaction.getDate(), transaction.getTime(), category, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                System.out.println(formattedTransaction);

                if (transaction.getAmount() >= 0) {
                    totalDeposit += transaction.getAmount();
                } else {
                    totalPayment += transaction.getAmount();
                }
            }
        }
        System.out.println("Total deposits for the current month: $" + totalDeposit);
        System.out.println("Total payments for the current month: $" + Math.abs(totalPayment));
    }


    public static void previousMonth(ArrayList<Transaction> transactions) {
        LocalDate currentDate = LocalDate.now().minusMonths(1);
        int targetMonth = currentDate.getMonthValue();
        int targetYear = currentDate.getYear();

        System.out.println("Previous Month Report:");

        double totalDeposit = 0;
        double totalPayment = 0;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate());
            int month = date.getMonthValue();
            int year = date.getYear();

            if (month == targetMonth && year == targetYear) {
                String category = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                String formattedTransaction = String.format("%s|%s|%s|%s|%s|%s",
                        transaction.getDate(), transaction.getTime(), category, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                System.out.println(formattedTransaction);

                if (transaction.getAmount() >= 0) {
                    totalDeposit += transaction.getAmount();
                } else {
                    totalPayment += transaction.getAmount();
                }
            }
        }

        System.out.println("Total deposits for the previous month: $" + totalDeposit);
        System.out.println("Total payments for the previous month: $" + Math.abs(totalPayment));
    }
    public static void yearToDate(ArrayList<Transaction> transactions) {
        int currentYear = LocalDate.now().getYear();

        System.out.println("Year-to-Date Report:");

        double totalDeposit = 0;
        double totalPayment = 0;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate());
            int year = date.getYear();

            if (year == currentYear) {
                String category = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                String formattedTransaction = String.format("%s|%s|%s|%s|%s|%s",
                        transaction.getDate(), transaction.getTime(), category, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                System.out.println(formattedTransaction);

                if (transaction.getAmount() >= 0) {
                    totalDeposit += transaction.getAmount();
                } else {
                    totalPayment += transaction.getAmount();
                }
            }
        }

        System.out.println("Total deposits year-to-date: $" + totalDeposit);
        System.out.println("Total payments year-to-date: $" + Math.abs(totalPayment));
    }


    public static void previousYear(ArrayList<Transaction> transactions) {
        int currentYear = LocalDate.now().getYear() - 1;

        System.out.println("Previous Year Report:");

        double totalDeposit = 0;
        double totalPayment = 0;

        for (Transaction transaction : transactions) {
            LocalDate date = LocalDate.parse(transaction.getDate());
            int year = date.getYear();

            if (year == currentYear) {
                String category = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                String formattedTransaction = String.format("%s|%s|%s|%s|%s|%s",
                        transaction.getDate(), transaction.getTime(), category, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                System.out.println(formattedTransaction);

                if (transaction.getAmount() >= 0) {
                    totalDeposit += transaction.getAmount();
                } else {
                    totalPayment += transaction.getAmount();
                }
            }
        }

        System.out.println("Total deposits for the previous year: $" + totalDeposit);
        System.out.println("Total payments for the previous year: $" + Math.abs(totalPayment));
    }

    public static void searchByVendor(ArrayList<Transaction> transactions, Scanner scanner) {
        System.out.print("Enter vendor name: ");
        String vendorName = scanner.nextLine();

        System.out.println("Transactions for Vendor: " + vendorName);

        double totalDeposit = 0;
        double totalPayment = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getVendor().equalsIgnoreCase(vendorName)) {
                String category = transaction.getAmount() >= 0 ? "Deposit" : "Payment";
                String formattedTransaction = String.format("%s|%s|%s|%s|%s|%s",
                        transaction.getDate(), transaction.getTime(), category, transaction.getDescription(), transaction.getVendor(), transaction.getAmount());
                System.out.println(formattedTransaction);

                if (transaction.getAmount() >= 0) {
                    totalDeposit += transaction.getAmount();
                } else {
                    totalPayment += transaction.getAmount();
                }
            }
        }

        System.out.println("Total deposits for " + vendorName + ": $" + totalDeposit);
        System.out.println("Total payments for " + vendorName + ": $" + Math.abs(totalPayment));
    }
}