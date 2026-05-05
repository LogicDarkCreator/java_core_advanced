package util;

public class CommandHandler {
    private final UserManager userManager;
    private final UserInputHandler inputHandler;
    private boolean isRunning;

    public CommandHandler(UserManager userManager, UserInputHandler inputHandler) {
        this.userManager = userManager;
        this.inputHandler = inputHandler;
        this.isRunning = true;
    }

    public boolean isRunning() {
        return isRunning;
    }

    public void handleCommand(String command) {
        switch (command) {
            case "in":
                handleAddUser();
                break;
            case "del":
                handleDeleteUser();
                break;
            case "count":
                handleCountUsers();
                break;
            case "avg":
                handleAverageAge();
                break;
            case "median":
                handleMedianAge();
                break;
            case "young":
                handleYoungest();
                break;
            case "old":
                handleOldest();
                break;
            case "print":
                handlePrintAll();
                break;
            case "help":
                handleHelp();
                break;
            case "exit":
                handleExit();
                break;
            default:
                System.out.println("Unknown command. Please try again");
        }
    }

    private void handleAddUser() {
        String passportNumber = inputHandler.readPassportNumber();

        if (userManager.hasUser(passportNumber)) {
            System.out.println("User with this passport number already exists");
            return;
        }

        String name = inputHandler.readName();
        Integer age = inputHandler.readAge();

        if (age == null) {
            return;
        }

        if (userManager.addUser(passportNumber, name, age)) {
            System.out.println("User added successfully");
        } else {
            System.out.println("Failed to add user");
        }
    }

    private void handleDeleteUser() {
        String passportNumber = inputHandler.readPassportNumber();

        if (userManager.removeUser(passportNumber)) {
            System.out.println("User deleted successfully");
        } else {
            System.out.println("User with this passport number not found");
        }
    }

    private void handleCountUsers() {
        System.out.println("Number of users: " + userManager.getCount());
    }

    private void handleAverageAge() {
        if (userManager.isEmpty()) {
            System.out.println("No users in the system");
            return;
        }
        System.out.println("Average age: " + userManager.getAverageAge());
    }

    private void handleMedianAge() {
        if (userManager.isEmpty()) {
            System.out.println("No users in the system");
            return;
        }
        System.out.println("Median age: " + userManager.getMedianAge());
    }

    private void handleYoungest() {
        if (userManager.isEmpty()) {
            System.out.println("No users in the system");
            return;
        }
        System.out.println("Youngest user: " + userManager.getYoungest());
    }

    private void handleOldest() {
        if (userManager.isEmpty()) {
            System.out.println("No users in the system");
            return;
        }
        System.out.println("Oldest user: " + userManager.getOldest());
    }

    private void handlePrintAll() {
        if (userManager.isEmpty()) {
            System.out.println("No users in the system");
            return;
        }

        System.out.println("All users (sorted by age):");
        for (User user : userManager.getAllUsersSortedByAge()) {
            System.out.println(user);
        }
    }

    public void handleHelp() {
        System.out.println("Available commands:");
        System.out.println("  in     - Add a new user");
        System.out.println("  del    - Delete a user");
        System.out.println("  count  - Show number of users");
        System.out.println("  avg    - Calculate average age");
        System.out.println("  median - Calculate median age");
        System.out.println("  young  - Show youngest user");
        System.out.println("  old    - Show oldest user");
        System.out.println("  print  - Print all users sorted by age");
        System.out.println("  help   - Show this help");
        System.out.println("  exit   - Exit the program");
    }

    private void handleExit() {
        System.out.println("Program terminated");
        isRunning = false;
    }
}