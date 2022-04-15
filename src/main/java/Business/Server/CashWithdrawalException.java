package Business.Server;

public class CashWithdrawalException extends Exception{
    public CashWithdrawalException() {
        super("Incorrect amount entered, enter the amount as a multiple of 100");
    }
}
