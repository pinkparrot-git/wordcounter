import java.util.Scanner;

public class WordCounterApp {
    public static void main(String[] args) {
        System.out.println("Welcome to WordCounterApp! \n" +
                "Type a sentence and I'll count the *real* words for you.\n" +
                "Type 'exit' to make me stop counting.\n");
        Scanner scanner = new Scanner(System.in);
        WordCounterService wordCounter = new WordCounterService();
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
    }
}
