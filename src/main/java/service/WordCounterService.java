package service;

import configuration.WordCounterConfiguration;

import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class WordCounterService implements IWordCounterService {
    private final IFileUtilService fileUtilService;
    private final String regex;
    private final boolean caseSensitive;
    private final String stopWordsFilePath;
    private Set<String> stopWords;

    public WordCounterService(IFileUtilService fileUtilService, WordCounterConfiguration configuration) {
        this.fileUtilService = fileUtilService;
        this.regex = configuration.getWordPattern();
        this.caseSensitive = configuration.isCaseSensitive();
        this.stopWordsFilePath = configuration.getStopWordsFilePath();
    }

    public int countValidWords(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }
        int count = 0;
        String[] words = input.trim().split("\\s+");
        for (String word : words) {
            if (isRealWord(word) && !isStopWord(word)) {
                count++;
            }
        }
        return count;
    }

    private boolean isRealWord(String word) {
        return Pattern.matches(regex, word);
    }

    private boolean isStopWord(String word) {
        return stopWords.contains(caseSensitive ? word : word.toLowerCase());
    }

    private Set<String> normalizeStopWords() {
        Set<String> stopWords = fileUtilService.loadWordsFromFile(stopWordsFilePath);
        return caseSensitive ? stopWords : stopWords.stream().map(String::toLowerCase).collect(Collectors.toSet());
    }

    public void initialize() {
        this.stopWords = normalizeStopWords();
    }
}
