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
    private FileService fileService;
    private WordCounterService wordCounter;

    @BeforeEach
    void setup() {
        fileService = new FileServiceImpl();
        WordCounterConfiguration configuration = new WordCounterConfiguration(
                "[a-zA-Z]*", "stopwords.txt", false);

        wordCounter = new WordCounterServiceImpl(fileService, configuration);
        wordCounter.initialize();
    }

    @Test
    void shouldCountWordsCorrectlyIgnoringStopWords() {
        String content = fileService.loadTextFromFile("mytext.txt");
        assertEquals(content, "Mary had\n" +
                        "a little\n" +
                        "lamb",
                "Couldn't load text from the file properly");

        int count = wordCounter.countValidWords(content);
        assertEquals(4, count, "Should ignore stop words (case-insensitive)");
    }
}
