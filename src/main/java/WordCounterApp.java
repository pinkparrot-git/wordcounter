import java.io.IOException;
import java.util.Scanner;

public class WordCounterApp {
    public static void main(String[] args) {
        try {
            FileUtilService fileUtilService = new FileUtilService();
            WordCounterService wordCounter = new WordCounterService(fileUtilService);

            System.out.println("Welcome to WordCounterApp! \n" +
                    "Type a sentence and I'll count the *real* words for you.\n" +
                    "Type 'exit' to make me stop counting.\n");
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter text: ");
                String input = scanner.nextLine();
                if ("exit".equalsIgnoreCase(input.trim())) {
                    System.out.println("See you soon!");
                    break;
                }
                int wordCount = wordCounter.countValidWords(input);
                System.out.println("Number of words: " + wordCount);
            }
        } catch (IOException e) {
            System.err.println("Failed to initialize WordCounterApp: " + e.getMessage());
        }
    }
}
