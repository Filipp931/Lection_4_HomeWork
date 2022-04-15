package Business.Server.Exceptions;

public class InvalidPinException extends Exception{
    public InvalidPinException() {
        super("Incorrect account PIN try again");
    }
}
