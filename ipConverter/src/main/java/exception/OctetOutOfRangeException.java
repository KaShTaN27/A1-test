package exception;

public class OctetOutOfRangeException extends RuntimeException{
    public OctetOutOfRangeException(int value) {
        super(String.format("Octet value %d out of range [0, 255]", value));
    }
}
