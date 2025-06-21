package service.unittest;

import configuration.WordCounterConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import service.FileService;
import service.WordCounterServiceImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WordCounterServiceTest {
    private static final FileService fileService = mock(FileService.class);
    private static final String stopWords = "A an or and AND";

    private WordCounterServiceImpl wordCounter;
    private WordCounterConfiguration configuration;

    @BeforeEach
    void setup() {
        when(fileService.loadTextFromFile(anyString())).thenReturn(stopWords);
    }

    void initializeWordCounter(boolean caseSensitive) {
        configuration = new WordCounterConfiguration("[a-zA-Z]*", "stopwords.txt", caseSensitive);
        wordCounter = new WordCounterServiceImpl(fileService, configuration);
        wordCounter.initialize();
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
        WordCounterServiceImpl service = new WordCounterServiceImpl(fileService, configuration);
        wordCounter.initialize();
        assertNotNull(service);
    }
}
