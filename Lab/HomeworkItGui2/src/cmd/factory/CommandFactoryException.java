package cmd.factory;

public class CommandFactoryException extends RuntimeException{
    public CommandFactoryException(Throwable cause) {
        super(cause);
    }

    public CommandFactoryException(String message) {
        super(message);
    }
}
