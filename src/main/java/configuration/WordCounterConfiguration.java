package configuration;

import exception.ConfigurationLoadException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class WordCounterConfiguration {
    private final String wordPattern;
    private final String stopWordsFilePath;
    private final boolean caseSensitive;

    public WordCounterConfiguration(String wordPattern, String stopWordsFilePath, boolean caseSensitive) {
        this.wordPattern = validateNotNull(wordPattern, "Word pattern property should not be null");
        this.stopWordsFilePath = validateNotNull(stopWordsFilePath, "Stop words file path property should not be null");
        this.caseSensitive = caseSensitive;
    }
    public static WordCounterConfiguration loadConfiguration() {
        try {
            Properties properties = new Properties();
            InputStream inputStream = WordCounterConfiguration.class.getClassLoader().getResourceAsStream("application.properties");
            if (inputStream == null) {
                throw new ConfigurationLoadException("The application.properties file not found in classpath");
            }
            properties.load(inputStream);

            String pattern = properties.getProperty("word.pattern");
            String filePath = properties.getProperty("stopwords.file.path");
            boolean caseSensitive = Boolean.parseBoolean(properties.getProperty("case.sensitive"));

            return new WordCounterConfiguration(pattern, filePath, caseSensitive);
        } catch (IOException e) {
            throw new ConfigurationLoadException("Failed to load application.properties", e);
        }
    }

    public String getWordPattern() {
        return wordPattern;
    }

    public String getStopWordsFilePath() {
        return stopWordsFilePath;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }


    private String validateNotNull(String value, String message) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(message);
        }
        return value.trim();
    }
}
