import configuration.WordCounterConfiguration;
import service.FileServiceImpl;
import service.FileService;
import service.WordCounterService;
import service.WordCounterServiceImpl;
import ui.ConsoleWordCounterUIImpl;
import ui.ConsoleWordCounterUI;

public class WordCounterApp {
    private final ConsoleWordCounterUI counterUI;

    private WordCounterApp(ConsoleWordCounterUI counterUI) {
        this.counterUI = counterUI;
    }

    public static void main(String[] args) {
        WordCounterApp app = configureApp();
        app.startConsoleApp(args);
    }

    private static WordCounterApp configureApp() {
        FileService fileUtilService = new FileServiceImpl();
        WordCounterConfiguration configuration = WordCounterConfiguration.loadConfiguration();
        WordCounterService wordCounter = new WordCounterServiceImpl(fileUtilService, configuration);
        wordCounter.initialize();
        ConsoleWordCounterUI counterUI = new ConsoleWordCounterUIImpl(fileUtilService, wordCounter);
        return new WordCounterApp(counterUI);
    }

    private void startConsoleApp(String[] args) {
        if (args.length > 0) {
            counterUI.processFileInput(args[0]);
        } else {
            counterUI.consoleInputBanner();
            counterUI.processConsoleInput();
        }
    }
}
