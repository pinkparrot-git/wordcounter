package integration;

import configuration.WordCounterConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FileUtilService;
import service.IFileUtilService;
import service.IWordCounterService;
import service.WordCounterFactory;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterIntegrationTest {
    private IFileUtilService fileUtilService;
    private IWordCounterService wordCounter;

    @BeforeEach
    void setup() {
        fileUtilService = new FileUtilService();
        WordCounterConfiguration configuration = new WordCounterConfiguration(
                "[a-zA-Z]*", "stopwords.txt", false);

        wordCounter = WordCounterFactory.create(fileUtilService, configuration);
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
