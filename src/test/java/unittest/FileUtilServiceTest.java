package unittest;

import exception.FileLoadingException;
import org.junit.jupiter.api.Test;
import service.FileUtilService;

import static org.junit.jupiter.api.Assertions.*;

public class FileUtilServiceTest {
    private final FileUtilService fileUtilService = new FileUtilService();

    @Test
    void shouldLoadTextFromFileSuccessfully() {
        String fileContent = fileUtilService.loadTextFromFile("mytext.txt");
        assertEquals("Mary had\n" +
                "a little\n" +
                "lamb", fileContent.trim());
    }

    @Test
    void shouldThrowExceptionWhenFilePathIsNull() {
        FileLoadingException exception = assertThrows(
                FileLoadingException.class,
                () -> fileUtilService.loadTextFromFile(null)
        );
        assertTrue(exception.getMessage().contains("File path is empty"));
    }

    @Test
    void shouldThrowExceptionWhenFilePathIsBlank() {
        FileLoadingException exception = assertThrows(
                FileLoadingException.class,
                () -> fileUtilService.loadTextFromFile("   ")
        );
        assertTrue(exception.getMessage().contains("File path is empty"));
    }

    @Test
    void shouldThrowExceptionWhenFileNotFound() {
        FileLoadingException exception = assertThrows(
                FileLoadingException.class,
                () -> fileUtilService.loadTextFromFile("anothertext.txt")
        );
        assertTrue(exception.getMessage().contains("File not found"));
    }

}
