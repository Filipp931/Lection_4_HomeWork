package Business.Server;

public class InvalidPinException extends Exception{
    public InvalidPinException() {
        super("Incorrect account PIN try again");
    }
}
