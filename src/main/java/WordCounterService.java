import java.io.IOException;

import java.util.Set;
import java.util.regex.Pattern;

public class WordCounterService {
    private static final String regex = "[a-zA-Z]*";
    private Set<String> stopWords;


    public WordCounterService(FileUtilService fileUtilService) throws IOException {
        this.stopWords = fileUtilService.convertFileToSet();
    }

    public int countValidWords(String input) throws IOException {
        if (input == null || input.isBlank()) {
            return 0;
        }
        int count = 0;
        String[] words = input.trim().split("\\s+");
        for (String word : words) {
            if (isRealWord(word) && !isStopWord(word, stopWords)) {
                count++;
            }
        }
        return count;
    }

    public boolean isRealWord(String word) {
        return Pattern.matches(regex, word);
    }

    public boolean isStopWord(String word, Set<String> stopWords) {
        return stopWords.contains(word.toLowerCase());
    }
}
