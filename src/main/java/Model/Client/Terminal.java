package Model.Client;

import Model.DAO.Exceptions.NotEnoughCashException;
import Model.Server.Exceptions.CashMultipleException;

public interface Terminal {
    public Integer checkAmount();
    public Integer deposit(Integer amount) throws CashMultipleException;
    public Integer withdraw(Integer amount) throws CashMultipleException, NotEnoughCashException;
}
