import org.junit.jupiter.api.Test;
import service.FileUtilService;
import service.IFileUtilService;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilServiceTest {
    private final IFileUtilService fileUtilService = new FileUtilService();
    private final String stopWordsFilePath = "stopwords.txt";


    @Test
    void shouldLoadStopWords() {
        Set<String> stopWords = fileUtilService.loadWordsFromFile(stopWordsFilePath);

        assertNotNull(stopWords, "stopWords should not be null");
        assertFalse(stopWords.isEmpty(), "stopWords should not be empty");

        assertEquals(4, stopWords.size());
    }

    @Test
    void shouldDistinguishWordsBasedOnCase() {
        Set<String> stopWords = fileUtilService.loadWordsFromFile(stopWordsFilePath);

        assertTrue(stopWords.contains("and"));
        assertTrue(stopWords.contains("AND"));

        assertFalse(stopWords.contains("a"));
        assertTrue(stopWords.contains("A"));
    }

}