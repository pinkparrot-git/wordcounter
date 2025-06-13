import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Properties;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtilService {
    private final String stopWordsFilePath;

    public FileUtilService() throws IOException {
        Properties properties = new Properties();
        properties.load(getClass().getClassLoader().getResourceAsStream("application.properties"));
        this.stopWordsFilePath = properties.getProperty("stopwords.file.path");

    }
    public Set<String> convertFileToSet() throws IOException {
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(stopWordsFilePath).getFile());
        String data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        Set<String> stopWords = Stream.of(data.trim().split("\\s+"))
                .map(String::toLowerCase)
                .collect(Collectors.toSet());
        return stopWords;
    }
}
