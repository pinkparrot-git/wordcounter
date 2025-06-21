package service.integration;

import configuration.WordCounterConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FileServiceImpl;
import service.FileService;
import service.WordCounterService;
import service.WordCounterServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterIntegrationTest {
    private FileService fileUtilService;
    private WordCounterService wordCounter;

    @BeforeEach
    void setup() {
        fileUtilService = new FileServiceImpl();
        WordCounterConfiguration configuration = new WordCounterConfiguration(
                "[a-zA-Z]*", "stopwords.txt", false);

        wordCounter = new WordCounterServiceImpl(fileUtilService, configuration);
        wordCounter.initialize();
    }

    @Test
    void shouldCountWordsCorrectlyIgnoringStopWords() {
        String content = fileUtilService.loadTextFromFile("mytext.txt");
        assertEquals(content, "Mary had\n" +
                        "a little\n" +
                        "lamb",
                "Couldn't load text from the file properly");

        int count = wordCounter.countValidWords(content);
        assertEquals(4, count, "Should ignore stop words (case-insensitive)");
    }
}
