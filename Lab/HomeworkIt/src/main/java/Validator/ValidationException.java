package Validator;

public class ValidationException extends Exception{
    private String message;

    ValidationException(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return message;
    }
}
