package util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Handles user input from the console.
 */
public class InputHandler {
    private static final String EMPTY_INPUT_PROMPT = "Enter integers (empty line to finish):";
    private static final String INVALID_INPUT_MESSAGE = "Invalid input. Please enter an integer or empty line to finish.";

    /**
     * Reads integers from console until an empty line is entered.
     *
     * @return List of integers entered by the user
     */
    public List<Integer> readNumbersFromConsole() {
        List<Integer> numbers = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println(EMPTY_INPUT_PROMPT);

        while (true) {
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) {
                break;
            }

            try {
                int number = Integer.parseInt(input);
                numbers.add(number);
            } catch (NumberFormatException e) {
                System.out.println(INVALID_INPUT_MESSAGE);
            }
        }

        return numbers;
    }
}
