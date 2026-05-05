package util;

import java.util.List;

/**
 * Immutable data class representing the result of number analysis.
 */
public class AnalysisResult {
    private final long sumOfUniqueNumbers;
    private final List<Integer> duplicateNumbers;

    public AnalysisResult(long sumOfUniqueNumbers, List<Integer> duplicateNumbers) {
        this.sumOfUniqueNumbers = sumOfUniqueNumbers;
        this.duplicateNumbers = List.copyOf(duplicateNumbers); // Create immutable copy
    }

    public long getSumOfUniqueNumbers() {
        return sumOfUniqueNumbers;
    }

    public List<Integer> getDuplicateNumbers() {
        return duplicateNumbers;
    }
}
