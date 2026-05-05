package com.finance.app.services;

import com.finance.app.models.Transaction;
import java.util.List;

/**
 * Service for generating financial reports
 */
public class ReportService {

    /**
     * Generates and prints a financial report
     *
     * @param service FinancialAccountingService containing transaction data
     */
    public static void generateReport(FinancialAccountingService service) {
        System.out.println("\n" + "=".repeat(70));
        System.out.println("FINANCIAL REPORT");
        System.out.println("=".repeat(70));

        // Financial summary
        double totalIncome = service.getTotalIncome();
        double totalExpense = service.getTotalExpense();
        double balance = service.getBalance();

        System.out.printf("Total Income:    $%12.2f%n", totalIncome);
        System.out.printf("Total Expense:   $%12.2f%n", totalExpense);
        System.out.printf("Balance:         $%12.2f%n", balance);
        System.out.println("-".repeat(70));

        // Recent transactions
        System.out.println("RECENT TRANSACTIONS (last 5):");
        System.out.println("-".repeat(70));
        System.out.printf("%-12s | %12s | %-7s | %s%n",
                "Date", "Amount", "Type", "Description");
        System.out.println("-".repeat(70));

        List<Transaction> recentTransactions = service.getRecentTransactions(5);
        if (recentTransactions.isEmpty()) {
            System.out.println("No transactions recorded.");
        } else {
            for (Transaction transaction : recentTransactions) {
                System.out.println(transaction);
            }
        }
        System.out.println("=".repeat(70) + "\n");
    }
}
