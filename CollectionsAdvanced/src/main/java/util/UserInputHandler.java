package util;

import java.util.Scanner;

public class UserInputHandler {
    private final Scanner scanner;

    public UserInputHandler() {
        this.scanner = new Scanner(System.in);
    }

    public String readCommand() {
        System.out.println("\nEnterCommand: ");
        return scanner.nextLine().trim().toLowerCase();
    }

    public String readPassportNumber() {
        System.out.println("Enter passport number: ");
        return scanner.nextLine().trim();
    }

    public String readName() {
        System.out.print("Enter name: ");
        return scanner.nextLine().trim();
    }

    public Integer readAge() {
        System.out.print("Enter age: ");
        try {
            int age = Integer.parseInt(scanner.nextLine().trim());
            if (age < 0) {
                System.out.println("Age must be non-negative");
                return null;
            }
            return age;
        } catch (NumberFormatException e) {
            System.out.println("Invalid age format");
            return null;
        }
    }

    public void close() {
        scanner.close();
    }
}
