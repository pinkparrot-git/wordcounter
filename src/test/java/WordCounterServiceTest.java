import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WordCounterServiceTest {
    private final WordCounterService wordCounter = new WordCounterService();

    @Test
    void testValidSentence() {
        assertEquals(5, wordCounter.countValidWords("Mary had a little lamb"));
    }

    @Test
    void testInvalidCharactersInWord() {
        assertEquals(4, wordCounter.countValidWords("Ma*ry had a little lamb"));
        assertEquals(4, wordCounter.countValidWords("Mary had a little lamb."));
        assertEquals(3, wordCounter.countValidWords("Look! THERE iS Mary's lAmb"));
        assertEquals(1, wordCounter.countValidWords("   @  \n     L!TTLE  \t  lAmb  "));
        assertEquals(5, wordCounter.countValidWords("Mary\t  had 1 or   one \n\t\tlittle _lamb"));

    }

    @Test
    void testNonAlphabeticCharacters() {
        assertEquals(0, wordCounter.countValidWords(" å  ø ö é ü ` ? / + = - % ^ & * 12 ' "));
    }

    @Test
    void testNullAndEmptyString() {
        assertEquals(0, wordCounter.countValidWords(""));
        assertEquals(0, wordCounter.countValidWords(null));
        assertEquals(0, wordCounter.countValidWords("    "));
        assertEquals(0, wordCounter.countValidWords("  \t \n  "));
    }
}
