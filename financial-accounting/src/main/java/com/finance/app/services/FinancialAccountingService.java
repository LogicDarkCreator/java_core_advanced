package com.finance.app.services;

import com.finance.app.models.Transaction;
import com.finance.app.enums.TransactionType;
import java.util.ArrayList;
import java.util.List;

/**
 * Service for managing financial transactions using a circular buffer
 */
public class FinancialAccountingService {
    private final Transaction[] transactions;
    private int lastElementIndex = -1;
    private int transactionCount = 0;
    private final int maxSize;

    /**
     * Creates a new financial accounting service with specified buffer size
     *
     * @param maxSize Maximum number of transactions to keep in history
     */
    public FinancialAccountingService(int maxSize) {
        this.maxSize = maxSize;
        this.transactions = new Transaction[maxSize];
    }

    /**
     * Adds a new transaction to the history
     * Uses circular buffer: when buffer is full, oldest transaction is replaced
     *
     * @param transaction The transaction to add
     */
    public void addTransaction(Transaction transaction) {
        lastElementIndex = (lastElementIndex + 1) % maxSize;
        transactions[lastElementIndex] = transaction;

        if (transactionCount < maxSize) {
            transactionCount++;
        }
    }

    /**
     * Gets the most recent transactions
     *
     * @param count Maximum number of transactions to return
     * @param List of recent transactions in chronological order (newest first)
     */
    public List<Transaction> getRecentTransactions(int count) {
        count = Math.min(count, transactionCount);
        List<Transaction> result = new ArrayList<>();

        for (int i = 0; i < count; i++) {
            int index = (lastElementIndex - i + maxSize) % maxSize;
            Transaction transaction = transactions[index];
            if (transaction != null) {
                result.add(transaction);
            }
        }
        return result;
    }

    /**
     * Calculates total income from all stored transactions
     *
     * @return Sum of all INCOME transactions
     */
    public double getTotalIncome() {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) {
            int index = (lastElementIndex - i + maxSize) % maxSize;
            Transaction transaction = transactions[index];
            if (transaction != null && transaction.type() == TransactionType.INCOME) {
                total += transaction.amount();
            }
        }
        return total;
    }

    /**
     * Calculates total expenses from all stored transactions
     *
     * @return Sum of all EXPENSE transactions
     */
    public double getTotalExpense() {
        double total = 0;
        for (int i = 0; i < transactionCount; i++) {
            int index = (lastElementIndex - i + maxSize) % maxSize;
            Transaction transaction = transactions[index];
            if (transaction != null && transaction.type() == TransactionType.EXPENSE) {
                total += transaction.amount();
            }
        }
        return total;
    }

    /**
     * Calculates current balance
     *
     * @return Balance (income - expenses)
     */
    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }

    /**
     * Gets the current number fo stored transactions
     *
     * @return Transaction count
     */
    public int getTransactionCount() {
        return transactionCount;
    }

    /**
     * Clears all transactions
     */
    public void clearAll() {
        for (int i = 0; i < maxSize; i++) {
            transactions[i] = null;
        }
        lastElementIndex = -1;
        transactionCount = 0;
    }
}