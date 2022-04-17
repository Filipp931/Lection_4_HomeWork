package Model.Server.Exceptions;

public class CashMultipleException extends Exception{
    public CashMultipleException() {
        super("Incorrect amount entered, enter the amount as a multiple of 100");
    }
}
