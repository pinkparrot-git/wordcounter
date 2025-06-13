import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterServiceTest {
    private final FileUtilService fileUtilService = new FileUtilService();
    private final WordCounterService wordCounter = new WordCounterService(fileUtilService);

    public WordCounterServiceTest() throws IOException {
    }


    @Test
    void testValidSentence() throws IOException {
        assertEquals(4, wordCounter.countValidWords("Mary had a little lamb"));
    }

    @Test
    void testInvalidCharactersInWord() throws IOException {
        assertEquals(3, wordCounter.countValidWords("Ma*ry had a little lamb"));
        assertEquals(3, wordCounter.countValidWords("Mary had a little lamb."));
        assertEquals(4, wordCounter.countValidWords("Mary had a little lamb"));
        assertEquals(3, wordCounter.countValidWords("Look! THERE iS Mary's lAmb"));
        assertEquals(1, wordCounter.countValidWords("   @  \n     L!TTLE  \t  lAmb  "));
        assertEquals(4, wordCounter.countValidWords("Mary\t  had 1 or   one \n\t\tlittle _lamb"));
        assertEquals(3, wordCounter.countValidWords("Mary\t had a TEST lamb"));
        assertEquals(4, wordCounter.countValidWords("Mary\t had an amazing lamb"));
        assertEquals(3, wordCounter.countValidWords("The lamb is online"));
    }

    @Test
    void testNonAlphabeticCharacters() throws IOException {
        assertEquals(0, wordCounter.countValidWords(" å  ø ö é ü ` ? / + = - % ^ & * 12 ' "));
    }

    @Test
    void testNullAndEmptyString() throws IOException {
        assertEquals(0, wordCounter.countValidWords(""));
        assertEquals(0, wordCounter.countValidWords(null));
        assertEquals(0, wordCounter.countValidWords("    "));
        assertEquals(0, wordCounter.countValidWords("  \t \n  "));
    }
}
