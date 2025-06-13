import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilServiceTest {

    private final FileUtilService fileUtilService = new FileUtilService();

    public FileUtilServiceTest() throws IOException {
    }


    @Test
    void testConvertFileToSetReturnsCorrectStopWords() throws IOException {
        Set<String> stopWords = fileUtilService.convertFileToSet();

        assertNotNull(stopWords, "stopWords should not be null");
        assertFalse(stopWords.isEmpty(), "stopWords should not be empty");

        assertTrue(stopWords.contains("the"));
        assertTrue(stopWords.contains("and"));
        assertTrue(stopWords.contains("or"));

        assertEquals(6, stopWords.size());
    }

    @Test
    void testConvertFileToSetReturnsStopWordsInLowerCase() throws IOException {
        Set<String> stopWords = fileUtilService.convertFileToSet();

        assertTrue(stopWords.contains("the"));
        assertFalse(stopWords.contains("The"));
    }

    @Test
    void testStopWordsFilePathIsLoadedFromProperties() throws IOException, NoSuchFieldException, IllegalAccessException {
        FileUtilService fileUtilService = new FileUtilService();

        Field pathField = FileUtilService.class.getDeclaredField("stopWordsFilePath");
        pathField.setAccessible(true);
        String actualPath = (String) pathField.get(fileUtilService);

        assertEquals("stopwords.txt", actualPath);
    }

}