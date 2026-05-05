package com.finance.app.ui;

import com.finance.app.models.Transaction;
import com.finance.app.services.FinancialAccountingService;
import com.finance.app.services.ReportService;
import java.util.Scanner;

/**
 * Console-based user interface for the financial accounting application
 */
public class ConsoleMenu {
    private final Scanner scanner;
    private final  FinancialAccountingService accountingService;

    /**
     * Creates a new console menu with a financial service
     */
    public ConsoleMenu() {
        this.scanner = new Scanner(System.in);
        this.accountingService = new FinancialAccountingService(5); // Keep last 5 transactions
    }

    /**
     * Starts the application main loop
     */
    public void start() {
        printWelcomeMessage();

        boolean running = true;
        while (running) {
            printMenu();
            System.out.print("\nEnter your choice: ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                continue;
            }

            String command = input.toUpperCase();

            switch (command) {
                case "1":
                case "ADD":
                    addTransaction();
                    break;

                case "2":
                case "REPORT":
                    ReportService.generateReport(accountingService);
                    break;

                case "3":
                case "HELP":
                    showHelp();
                    break;

                case "4":
                case "CLEAR":
                    clearTransactions();
                    break;

                case "5":
                case "EXIT":
                    System.out.println("\nGenerating final report...");
                    ReportService.generateReport(accountingService);
                    System.out.println("Thank you for using Financial Accounting!");
                    running = false;
                    break;

                default:
                    System.out.println("Invalid command. Type HELP for available commands.");
                    break;
            }
        }

        scanner.close();
    }

    /**
     * Handles adding a new transaction
     */
    private void addTransaction() {
        System.out.println("\n" + "-".repeat(70));
        System.out.println("ADD NEW TRANSACTION");
        System.out.println("-".repeat(70));
        System.out.println("Format: Description; Amount; Type(INCOME/EXPENSE); Date(dd.MM.yyyy)");
        System.out.println("Example: Mars Ticket; 2499.99; EXPENSE; 24.03.2036");
        System.out.println("-".repeat(70));

        System.out.print("Enter transaction: ");
        String input = scanner.nextLine().trim();

        if (input.isEmpty()) {
            System.out.println("Transaction not added.");
            return;
        }

        try {
            Transaction transaction = Transaction.parse(input);
            accountingService.addTransaction(transaction);
            System.out.println("✓ Transaction added successfully!");
        } catch (IllegalArgumentException e) {
            System.out.println("✗ Error: " + e.getMessage());
            System.out.println("Please check the format and try again.");
        } catch (Exception e) {
            System.out.println("✗ Unexpected error: " + e.getMessage());
        }
    }

    /**
     * Clears all transactions
     */
    private void clearTransactions() {
        System.out.print("\nAre you sure you want to clear all transactions? (yes/no): ");
        String confirmation = scanner.nextLine().trim().toLowerCase();

        if (confirmation.equals("yes") || confirmation.equals("y")) {
            accountingService.clearAll();
            System.out.println("✓ All transactions have been cleared.");
        } else {
            System.out.println("Operation cancelled.");
        }
    }

    /**
     * Prints welcome message
     */
    private void printWelcomeMessage() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("WELCOME TO FINANCIAL ACCOUNTING APPLICATION");
        System.out.println("=".repeat(70));
    }

    /**
     * Prints main menu
     */
    private void printMenu() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("MAIN MENU");
        System.out.println("=".repeat(70));
        System.out.println("1. ADD    - Add new transaction");
        System.out.println("2. REPORT - Generate financial report");
        System.out.println("3. HELP   - Show help and examples");
        System.out.println("4. CLEAR  - Clear all transactions");
        System.out.println("5. EXIT   - Exit the application");
        System.out.println("=".repeat(70));
    }

    /**
     * Shows help information
     */
    private void showHelp() {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("HELP & EXAMPLES");
        System.out.println("=".repeat(70));
        System.out.println("\nCOMMANDS:");
        System.out.println("  ADD    - Add new transaction");
        System.out.println("  REPORT - Generate financial report");
        System.out.println("  HELP   - Show this help");
        System.out.println("  CLEAR  - Clear all transactions");
        System.out.println("  EXIT   - Exit the application");

        System.out.println("\nTRANSACTION FORMAT:");
        System.out.println("  Description; Amount; Type; Date");
        System.out.println("  Type must be INCOME or EXPENSE");
        System.out.println("  Date format: dd.MM.yyyy (e.g., 25.03.2036)");

        System.out.println("\nEXAMPLES:");
        System.out.println("  Salary; 50000.00; INCOME; 25.03.2036");
        System.out.println("  Mars Ticket; 2499.99; EXPENSE; 24.03.2036");
        System.out.println("  Grocery Shopping; 150.75; EXPENSE; 26.03.2036");

        System.out.println("\nSAMPLE DATA:");
        System.out.println("  You can use these for testing:");
        System.out.println("  1. Teleport Payment; 400.00; EXPENSE; 24.03.2036");
        System.out.println("  2. Neuromodule Creation; 2500; INCOME; 25.03.2036");
        System.out.println("  3. Mars Ticket; 2599.99; EXPENSE; 25.03.2036");
        System.out.println("  4. Bonus; 10000; INCOME; 25.03.2036");
        System.out.println("  5. Digital Avatar Skin; 3900; EXPENSE; 26.03.2036");
        System.out.println("  6. Nanofood Delivery; 745.89; EXPENSE; 27.03.2036");
        System.out.println("=".repeat(70));
    }
}