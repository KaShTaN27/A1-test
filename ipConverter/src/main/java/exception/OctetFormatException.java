package exception;

public class OctetFormatException extends RuntimeException{
    public OctetFormatException(String value) {
        super(String.format("\"%s\" is invalid octet value. Correct value is in range [0, 255]", value));
    }
}
