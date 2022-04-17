package Model.Client;

import DAO.Exceptions.AccountNotFoundException;
import DAO.Exceptions.NotEnoughCashException;
import Model.Server.Exceptions.AccountIsLockedException;
import Model.Server.Exceptions.CashMultipleException;
import Model.Server.Exceptions.InvalidPinException;
import Model.Server.TerminalServer;

public class TerminalImpl implements Terminal{
    private TerminalServer terminalServer = TerminalServer.getTerminalServer();

    public boolean verifyPinCode(Integer pin) throws AccountIsLockedException, InvalidPinException {
        return terminalServer.verifyPinCode(pin);
    }

    @Override
    public Integer checkAmount() {
        return terminalServer.getBalance();
    }

    public boolean checkAccount(Integer accountNumber) throws AccountNotFoundException {
        return terminalServer.checkAccount(accountNumber);
    }
    @Override
    public Integer deposit(Integer cash) throws CashMultipleException {
        return terminalServer.depositCash(cash);
    }

    @Override
    public Integer withdraw(Integer amount) throws CashMultipleException, NotEnoughCashException {
        terminalServer.cashWithdrawal(amount);
        return terminalServer.getBalance();
    }
}
