package Model.DAO.Exceptions;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(Integer accountNumber) {
        super("Account with number " + accountNumber + " not found.");
    }
}
