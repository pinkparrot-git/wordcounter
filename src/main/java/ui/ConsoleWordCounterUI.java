package ui;

import exception.FileLoadingException;
import service.IFileUtilService;
import service.IWordCounterService;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleWordCounterUI implements IConsoleWordCounterUI {
    private final IWordCounterService wordCounter;
    private final IFileUtilService fileUtilService;


    public ConsoleWordCounterUI(IFileUtilService fileUtilService, IWordCounterService wordCounter) {
        this.fileUtilService = fileUtilService;
        this.wordCounter = wordCounter;
    }

    public void processFileInput(String fileName) {
        try {
            if (!fileName.contains(".txt")) {
                throw new FileLoadingException("File extension is not valid.");
            }
            String fileContent = fileUtilService.loadTextFromFile(fileName);
            int wordCount = wordCounter.countValidWords(fileContent);

            printBanner(fileName, "File: ", "Number of words: " + wordCount);

        } catch (FileLoadingException e) {
            printBanner(fileName, "Error reading file: ",
                    e.getMessage(),
                    "Switching to console input mode.",
                    "Type a sentence to count its words, or type 'exit' to stop.");
            processConsoleInput();
        }
    }

    public void processConsoleInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter text: ");
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("exit")) {
                System.out.println("See you soon!");
                break;
            }
            int wordCount = wordCounter.countValidWords(input);
            System.out.println("Number of words: " + wordCount);
            System.out.println("------------------- ");
        }
    }


    private void printBanner(String fileName, String... lines) {
        if (fileName != null) {
            lines[0] = lines[0] + " " + fileName;
        }

        int maxLength = Arrays.stream(lines)
                .mapToInt(String::length)
                .max()
                .orElse(0);
        String border = "*".repeat(maxLength + 4); // Padding for aesthetics

        System.out.println("\n" + border);
        for (String line : lines) {
            System.out.println("* " + line + " ".repeat(maxLength - line.length()) + " *");
        }
        System.out.println(border + "\n");
    }

    public void consoleInputBanner() {
        String[] bannerLines = {
                "Welcome to WordCounterApp!",
                "Type a sentence and I'll count the *real* words for you.",
                "Type 'exit' to stop."
        };
        printBanner(null, bannerLines);
    }
}
