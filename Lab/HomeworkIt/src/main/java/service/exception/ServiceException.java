package service.exception;

/**
 * Exception class for Service
 */
public class ServiceException extends RuntimeException {
    /**
     * Constructor
     * @param message error that caused the exception to be thrown
     */
    public ServiceException(String message) {
        super(message);
    }
}
