package util;

import java.util.*;

/**
 * Analyzes numbers to find unique values and duplicates.
 */
public class NumberAnalyzer {

    /**
     * Analyzes the list of numbers to find unique numbers and duplicates.
     *
     * @param numbers List of integers to analyze
     * @return AnalysisResult containing unique numbers sum and duplicate list
     */
    public AnalysisResult analyzeNumbers(List<Integer> numbers) {
        Map<Integer, Integer> frequencyMap = buildFrequencyMap(numbers);

        long sumOfUniqueNumbers = calculateSumOfUniqueNumbers(frequencyMap);
        List<Integer> duplicateNumbers = findDuplicateNumbers(frequencyMap);

        return new AnalysisResult(sumOfUniqueNumbers, duplicateNumbers);
    }

    /**
     * Builds a frequency map of numbers.
     *
     * @param numbers List of integers
     * @return Map with number as key and frequency as value
     */
    private Map<Integer, Integer> buildFrequencyMap(List<Integer> numbers) {
        Map<Integer, Integer> frequencyMap = new HashMap<>();

        for (int number : numbers) {
            frequencyMap.put(number, frequencyMap.getOrDefault(number, 0) + 1);
        }

        return frequencyMap;
    }

    /**
     * Calculates the sum of numbers that appear exactly once.
     *
     * @param frequencyMap Map with number frequencies
     * @return Sum of unique numbers
     */
    private long calculateSumOfUniqueNumbers(Map<Integer, Integer> frequencyMap) {
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() == 1)
                .mapToLong(Map.Entry::getKey)
                .sum();
    }

    /**
     * Finds numbers that appear more than once.
     *
     * @param frequencyMap Map with number frequencies
     * @return List of duplicate numbers (without repetitions)
     */
    private List<Integer> findDuplicateNumbers(Map<Integer, Integer> frequencyMap) {
        return frequencyMap.entrySet().stream()
                .filter(entry -> entry.getValue() > 1)
                .map(Map.Entry::getKey)
                .sorted()
                .toList();
    }
}
