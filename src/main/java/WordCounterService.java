import java.util.regex.Pattern;

public class WordCounterService {
    private static final String regex = "[a-zA-Z]*";

    public int countValidWords(String input) {
        if (input == null || input.isBlank()) {
            return 0;
        }
        int count = 0;
        String[] words = input.trim().split("\\s+");
        for (String word : words) {
            if (Pattern.matches(regex, word)) {
                count++;
            }
        }
        return count;
    }
}
