package service;

import exception.FileLoadingException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileUtilService implements IFileUtilService {

    public Set<String> loadWordsFromFile(String filePath) {
        String data = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(filePath);
            if (resource == null) {
                throw new FileLoadingException("file not found in classpath: " + filePath);
            }
            File file = new File(resource.getFile());
            data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileLoadingException("failed to load the file: " + filePath, e);
        }
        return Stream.of(data.trim().split("\\s+")).collect(Collectors.toSet());
    }
}
