package exception;

public class IpFormatException extends RuntimeException{
    public IpFormatException() {
        super("Invalid format. IPv4 must contain 4 octets");
    }
}
