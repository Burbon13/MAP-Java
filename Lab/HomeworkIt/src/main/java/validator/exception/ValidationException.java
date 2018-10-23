package validator.exception;


/**
 * Exception class for Validator interface
 */
public class ValidationException extends RuntimeException{
    /**
     * Constructor
     * @param message the error that caused the exception to be thrown
     */
    public ValidationException(String message) {
        super(message);
    }
}
