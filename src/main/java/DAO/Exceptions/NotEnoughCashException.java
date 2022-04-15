package DAO.Exceptions;

public class NotEnoughCashException extends Exception{

    public NotEnoughCashException() {
        super("You don't have enough cash on balance");
    }
}
