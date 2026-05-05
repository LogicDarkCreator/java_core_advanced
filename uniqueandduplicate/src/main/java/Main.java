import java.util.*;

import util.*;

/**
 * Main application class that orchestrates the number processing workflow
 */
public class Main {
    private final InputHandler inputHandler;
    private final NumberAnalyzer numberAnalyzer;
    private final OutputFormatter outputFormatter;

    public Main() {
        this.inputHandler = new InputHandler();
        this.numberAnalyzer = new NumberAnalyzer();
        this.outputFormatter = new OutputFormatter();
    }

    /**
     * Runs the application: handles input, processes numbers, and displays results.
     */
    public void run() {
        try {
            List<Integer> numbers = inputHandler.readNumbersFromConsole();
            AnalysisResult result = numberAnalyzer.analyzeNumbers(numbers);
            outputFormatter.displayResults(result);
        } catch (Exception e) {
            System.err.println("An unexpected error occurred: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        new Main().run();
    }

}
