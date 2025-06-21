package service;

import exception.FileLoadingException;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;


public class FileUtilService implements IFileUtilService {

    public String loadTextFromFile(String filePath) {
        if(filePath == null || filePath.isBlank()){
            throw new FileLoadingException("File path is empty: " + filePath);
        }
        String data = "";
        try {
            ClassLoader classLoader = getClass().getClassLoader();
            URL resource = classLoader.getResource(filePath);
            if (resource == null) {
                throw new FileLoadingException("File not found in classpath: " + filePath);
            }
            File file = new File(resource.getFile());
            data = FileUtils.readFileToString(file, StandardCharsets.UTF_8);
        } catch (IOException e) {
            throw new FileLoadingException("Failed to load the file: " + filePath, e);
        }
        return data;
    }
}
