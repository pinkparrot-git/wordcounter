import configuration.WordCounterConfiguration;
import service.*;

import java.util.Scanner;

public class WordCounterApp {
    private final IWordCounterService wordCounter;

    private WordCounterApp(IWordCounterService wordCounter) {
        this.wordCounter = wordCounter;
    }
    public static void main(String[] args) {
        IFileUtilService fileUtilService = new FileUtilService();
        WordCounterConfiguration configuration = WordCounterConfiguration.loadConfiguration();
        WordCounterService wordCounter = WordCounterFactory.create(fileUtilService, configuration);
        new WordCounterApp(wordCounter).startConsoleApp();
    }

    private void startConsoleApp(){
        System.out.println( "\n" +
            "************************************************************\n" +
            "* Welcome to WordCounterApp!                               *\n" +
            "* Type a sentence and I'll count the *real* words for you. *\n" +
            "* Type 'exit' to make me stop counting.                    *\n" +
            "************************************************************\n");
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
            System.out.println("------------------- ");
        }
    }
}
