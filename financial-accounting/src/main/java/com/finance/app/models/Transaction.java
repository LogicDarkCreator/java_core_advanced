package com.finance.app.models;

import com.finance.app.enums.TransactionType;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Record representing a financial transaction
 * Immutable data class for transaction information
 */

public record Transaction(
        String description,
        double amount,
        TransactionType type,
        LocalDate date
) {
    private static final DateTimeFormatter DATE_FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy");

    /**
     * Parses a transaction from a string format
     * Format: Description; Amount; Type; Date(dd.MM.yyyy)
     *
     * @param line The string to parse
     * @return Parsed Transaction object
     * @throws IllegalArgumentException if format is invalid
     */
    public static Transaction parse(String line) {
        String[] parts = line.split(";");

        if (parts.length != 4) {
            throw new IllegalArgumentException("Invalid format. Expected: Description; Amount; Type; Date");
        }

        String description = parts[0].trim();
        double amount = parseAmount(parts[1].trim());
        TransactionType type = parseType(parts[2].trim());
        LocalDate date = parseDate(parts[3].trim());

        return new Transaction(description, amount, type, date);
    }

    private static double parseAmount(String amountStr) {
        try {
            return Double.parseDouble(amountStr);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Invalid amount format: " + amountStr);
        }
    }

    private static TransactionType parseType(String typeStr) {
        try {
            return TransactionType.valueOf(typeStr.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid transaction type. Use INCOME or EXPENSE");
        }
    }

    private static LocalDate parseDate(String dateStr) {
        try {
            return LocalDate.parse(dateStr, DATE_FORMATTER);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Invalid date format. Use dd.MM.yyyy (e.g., 25.03.2036)");
        }
    }

    @Override
    public String toString() {
        return String.format("%-10s | %10.2f | %-7s | %s",
                date.format(DATE_FORMATTER),
                amount,
                type,
                description
        );
    }
}