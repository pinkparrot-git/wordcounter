import configuration.WordCounterConfiguration;
import service.FileUtilService;
import service.IFileUtilService;
import service.IWordCounterService;
import service.WordCounterFactory;
import ui.ConsoleWordCounterUI;
import ui.IConsoleWordCounterUI;

public class WordCounterApp {
    private final IConsoleWordCounterUI counterUI;

    private WordCounterApp(IConsoleWordCounterUI counterUI) {
        this.counterUI = counterUI;
    }

    public static void main(String[] args) {
        WordCounterApp app = configureApp();
        app.startConsoleApp(args);
    }

    private static WordCounterApp configureApp() {
        IFileUtilService fileUtilService = new FileUtilService();
        WordCounterConfiguration configuration = WordCounterConfiguration.loadConfiguration();
        IWordCounterService wordCounter = WordCounterFactory.create(fileUtilService, configuration);
        IConsoleWordCounterUI counterUI = new ConsoleWordCounterUI(fileUtilService, wordCounter);
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
