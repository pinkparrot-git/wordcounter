import configuration.WordCounterConfiguration;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import service.IFileUtilService;
import service.WordCounterFactory;
import service.WordCounterService;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordCounterServiceTest {
    private static final IFileUtilService fileUtilService = mock(IFileUtilService.class);
    private static final Set<String> stopWords = new HashSet<>(Arrays.asList("A", "an", "or", "and", "AND"));

    private WordCounterService wordCounter;
    private WordCounterConfiguration configuration;

    @BeforeAll
    static void init() {
        when(fileUtilService.loadWordsFromFile(anyString())).thenReturn(stopWords);
    }

    void initializeWordCounter(boolean caseSensitive) {
        configuration = new WordCounterConfiguration("[a-zA-Z]*", "stopwords.txt", caseSensitive);
        wordCounter = WordCounterFactory.create(fileUtilService, configuration);
    }


    @Test
    void shouldCountValidWordsInSimpleSentence_caseSensitiveFalse() {
        initializeWordCounter(false);
        assertEquals(4, wordCounter.countValidWords("Mary had a little lamb"));
        assertEquals(4, wordCounter.countValidWords("Mary had A little lamb"));
        assertEquals(4, wordCounter.countValidWords("Mary had AN amazing lamb"));
    }

    @Test
    void shouldCountValidWordsInSimpleSentence_caseSensitiveTrue() {
        initializeWordCounter(true);
        assertEquals(5, wordCounter.countValidWords("Mary had a little lamb"));
        assertEquals(4, wordCounter.countValidWords("Mary had A little lamb"));
        assertEquals(5, wordCounter.countValidWords("Mary had AN amazing lamb"));
    }

    @Test
    void shouldIgnoreInvalidCharacters_caseSensitiveFalse() {
        initializeWordCounter(false);
        assertEquals(3, wordCounter.countValidWords("Ma*ry had a little lamb"));
        assertEquals(3, wordCounter.countValidWords("Mary had a little lamb."));
        assertEquals(3, wordCounter.countValidWords("Mary had A little lamb."));
        assertEquals(4, wordCounter.countValidWords("Mary\t  had 1 OR   one \n\t\tlittle _lamb"));
    }

    @Test
    void shouldIgnoreInvalidCharacters_caseSensitiveTrue() {
        initializeWordCounter(true);
        assertEquals(4, wordCounter.countValidWords("Ma*ry had a little lamb"));
        assertEquals(4, wordCounter.countValidWords("Mary had a little lamb."));
        assertEquals(3, wordCounter.countValidWords("Mary had A little lamb."));
        assertEquals(5, wordCounter.countValidWords("Mary\t  had 1 OR   one \n\t\tlittle _lamb"));
    }

    @Test
    void shouldReturnZeroForOnlySymbolsAndNumbers() {
        initializeWordCounter(false);
        assertEquals(0, wordCounter.countValidWords(" å  ø ö é ü ` ? / + = - % ^ & * 12 ' "));
    }

    @Test
    void shouldReturnZeroForNullOrEmptyInput() {
        initializeWordCounter(false);
        assertEquals(0, wordCounter.countValidWords(""));
        assertEquals(0, wordCounter.countValidWords(null));
        assertEquals(0, wordCounter.countValidWords("    "));
        assertEquals(0, wordCounter.countValidWords("  \t \n  "));
    }

    @Test
    void shouldCreateWordCounterService() {
        initializeWordCounter(false);
        WordCounterService service = WordCounterFactory.create(fileUtilService, configuration);
        assertNotNull(service);
    }
}
