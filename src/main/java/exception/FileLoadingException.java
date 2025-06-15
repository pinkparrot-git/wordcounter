package exception;

public class FileLoadingException extends RuntimeException {
    public FileLoadingException(String message, Throwable cause) {
        super(message, cause);
    }

    public FileLoadingException(String message) {
        super(message);
    }
}
