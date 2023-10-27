package org.example;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CustomSearch {
    public static void customSearch(Transaction[] transactions) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("Enter Start Date (YYYY-MM-DD) or leave blank: ");
                String startDateInput = scanner.nextLine().trim();
                LocalDate startDate = null;
                if (!startDateInput.isEmpty()) {
                    startDate = LocalDate.parse(startDateInput);
                }

                System.out.print("Enter End Date (YYYY-MM-DD) or leave blank: ");
                String endDateInput = scanner.nextLine().trim();
                LocalDate endDate = null;
                if (!endDateInput.isEmpty()) {
                    endDate = LocalDate.parse(endDateInput);
                }

                System.out.print("Enter Description or leave blank: ");
                String description = scanner.nextLine().trim();

                System.out.print("Enter Vendor or leave blank: ");
                String vendor = scanner.nextLine().trim();

                System.out.print("Enter the amount or leave blank: ");
                String amountInput = scanner.nextLine().trim();
                double userAmountInput = 0.0;
                if (!amountInput.isEmpty()) {
                    userAmountInput = Double.parseDouble(amountInput);
                }

                for (Transaction transaction : transactions) {
                    LocalDate transactionDate = LocalDate.parse(transaction.getDate());
                    boolean isDateInRange = false;
                    if (startDate == null && endDate == null) {
                        isDateInRange = true;
                    } else if (startDate != null && endDate != null) {
                        isDateInRange = (transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate)) &&
                                (transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate));
                    } else if (startDate != null) {
                        isDateInRange = transactionDate.isEqual(startDate) || transactionDate.isAfter(startDate);
                    } else if (endDate != null) {
                        isDateInRange = transactionDate.isEqual(endDate) || transactionDate.isBefore(endDate);
                    }

                    if (isDateInRange &&
                            (description.isEmpty() || transaction.getDescription().contains(description)) &&
                            (vendor.isEmpty() || transaction.getVendor().contains(vendor)) &&
                            (userAmountInput == 0.0 || userAmountInput == transaction.getAmount())) {
                        System.out.println(transaction);
                    }
                }
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter dates in the format YYYY-MM-DD.");
            } catch (NumberFormatException e) {
                System.out.println("Invalid input for amount. Please enter a valid number.");
            }
        }
    }
}

