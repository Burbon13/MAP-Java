package repository.exception;

public class FileRepositoryException extends RuntimeException {
    public FileRepositoryException(Throwable cause) {
        super(cause);
    }

    public FileRepositoryException(String message) {
        super(message);
    }
}
