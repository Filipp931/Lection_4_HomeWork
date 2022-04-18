package Model.Server;

import Model.DAO.Exceptions.AccountNotFoundException;
import Model.DAO.Exceptions.NotEnoughCashException;
import Model.Server.Exceptions.AccountIsLockedException;
import Model.Server.Exceptions.CashMultipleException;
import Model.Server.Exceptions.InvalidPinException;

public interface Server {
    boolean verifyPinCode(Integer pin) throws AccountIsLockedException, InvalidPinException;
    boolean checkAccount(Integer accountNumber) throws AccountNotFoundException;
    Integer getBalance();
    Integer depositCash(Integer cash) throws CashMultipleException;
    Integer cashWithdrawal(Integer cash) throws CashMultipleException, NotEnoughCashException;
}
