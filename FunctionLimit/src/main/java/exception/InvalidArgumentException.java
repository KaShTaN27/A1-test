package exception;

public class InvalidArgumentException extends RuntimeException{
    public InvalidArgumentException() {
        super("Argument must be grater than 1");
    }
}
