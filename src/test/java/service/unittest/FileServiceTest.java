package service.unittest;

import exception.FileLoadingException;
import org.junit.jupiter.api.Test;
import service.FileServiceImpl;

import static org.junit.jupiter.api.Assertions.*;

public class FileServiceTest {
    private final FileServiceImpl fileService = new FileServiceImpl();

    @Test
    void shouldLoadTextFromFileSuccessfully() {
        String fileContent = fileService.loadTextFromFile("mytext.txt");
        assertEquals("Mary had\n" +
                "a little\n" +
                "lamb", fileContent.trim());
    }

    @Test
    void shouldThrowExceptionWhenFilePathIsNull() {
        FileLoadingException exception = assertThrows(
                FileLoadingException.class,
                () -> fileService.loadTextFromFile(null)
        );
        assertTrue(exception.getMessage().contains("File path is empty"));
    }

    @Test
    void shouldThrowExceptionWhenFilePathIsBlank() {
        FileLoadingException exception = assertThrows(
                FileLoadingException.class,
                () -> fileService.loadTextFromFile("   ")
        );
        assertTrue(exception.getMessage().contains("File path is empty"));
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        FileLoadingException exception = assertThrows(
                FileLoadingException.class,
                () -> fileService.loadTextFromFile("anothertext.txt")
        );
        assertTrue(exception.getMessage().contains("File not found"));
    }

}
