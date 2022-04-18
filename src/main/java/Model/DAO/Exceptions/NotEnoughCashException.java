package Model.DAO.Exceptions;

public class NotEnoughCashException extends Exception{

    public NotEnoughCashException(Integer balance) {
        super("You don't have enough cash on balance, amount must be less than " + balance);
    }
}
