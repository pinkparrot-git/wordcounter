package configuration;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class WordCounterConfigurationTest {

    @Test
    void shouldLoadConfigurationFromPropertiesFile() {
        WordCounterConfiguration configuration = WordCounterConfiguration.loadConfiguration();

        assertEquals("stopwords.txt", configuration.getStopWordsFilePath());
        assertEquals("[a-zA-Z]*", configuration.getWordPattern());
        assertFalse(configuration.isCaseSensitive());
    }
}
