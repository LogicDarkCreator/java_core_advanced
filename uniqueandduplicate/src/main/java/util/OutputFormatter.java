package util;

/**
 * Formats and displays analysis results to the console.
 */
public class OutputFormatter {
    private static final String UNIQUE_SUM_MESSAGE = "Sum of unique numbers: %d";
    private static final String DUPLICATES_MESSAGE = "Duplicate numbers: %s";

    /**
     * Displays the analysis results in the required format.
     *
     * @param result The analysis result to display
     */
    public void displayResults(AnalysisResult result) {
        System.out.println("\n" + "=".repeat(40));
        System.out.printf(UNIQUE_SUM_MESSAGE + "%n", result.getSumOfUniqueNumbers());
        System.out.printf(DUPLICATES_MESSAGE + "%n", result.getDuplicateNumbers());
        System.out.println("=".repeat(40));
    }
}