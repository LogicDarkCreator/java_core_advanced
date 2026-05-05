package com.finance.app;

import com.finance.app.models.Transaction;
import com.finance.app.services.FinancialAccountingService;
import com.finance.app.services.ReportService;
import com.finance.app.ui.ConsoleMenu;

/**
 * Main application entry point
 */
public class Main {

    /**
     * Main method - application entry point
     *
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Check if should load sample data
        if (args.length > 0 && args[0].equalsIgnoreCase("--sample")) {
            loadSampleTransactions();
        }

        // Start the console interface
        ConsoleMenu menu = new ConsoleMenu();
        menu.start();
    }

    /**
     * Loads sample transactions for demonstration
     */
    private static void loadSampleTransactions() {
        try {
            FinancialAccountingService accounting = new FinancialAccountingService(5);

            // Load test transactions from the assignment
            String[] sampleTransactions = {
                    "Teleport Payment; 400.00; EXPENSE; 24.03.2036",
                    "Neuromodule Creation; 2500; INCOME; 25.03.2036",
                    "Mars Ticket; 2599.99; EXPENSE; 25.03.2036",
                    "Bonus; 10000; INCOME; 25.03.2036",
                    "Digital Avatar Skin; 3900; EXPENSE; 26.03.2036",
                    "Nanofood Delivery; 745.89; EXPENSE; 27.03.2036"
            };

            System.out.println("\n" + "=".repeat(70));
            System.out.println("LOADING SAMPLE TRANSACTIONS");
            System.out.println("=".repeat(70));

            for (String transactionStr : sampleTransactions) {
                Transaction transaction = Transaction.parse(transactionStr);
                accounting.addTransaction(transaction);
                System.out.println("✓ Loaded: " + transaction.description());
            }

            System.out.println("\nSample data loaded successfully!");
            System.out.println("Expected results:");
            System.out.println("- Total Income:     $12,500.00");
            System.out.println("- Total Expenses:   $ 7,645.88");
            System.out.println("- Balance:          $ 4,854.12");
            System.out.println("=".repeat(70) + "\n");

            // Show report with sample data
            ReportService.generateReport(accounting);

        } catch (Exception e) {
            System.out.println("Note: Could not load sample transactions: " + e.getMessage());
        }
    }
}