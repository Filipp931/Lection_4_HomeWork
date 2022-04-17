package Model.Server;

import Model.Server.Exceptions.AccountIsLockedException;
import Model.Server.Exceptions.CashMultipleException;
import Model.Server.Exceptions.InvalidPinException;
import DAO.Account;
import DAO.Exceptions.AccountNotFoundException;
import DAO.Data;
import DAO.Exceptions.NotEnoughCashException;

public class TerminalServer {
    private static TerminalServer terminalServer = new TerminalServer();
    private Account account;
    private PinValidator pinValidator = null;
    private static Data data = Data.getData();
    private Integer accountNumber;

    public static TerminalServer getTerminalServer() {
        return terminalServer;
    }

    /**
     * Поиск аккаунта по уникальному номеру и верификация
     *
     * @param pin - пин код для проверки
     * @return boolean - прошла ли верификация
     * @throws AccountIsLockedException, InvalidPinException
     */
    public boolean verifyPinCode(Integer pin) throws AccountIsLockedException, InvalidPinException {
        Boolean valid = false;
        int[] pinCode = Integer.toString(pin).chars().map(c -> c - '0').toArray();
        if (pinValidator == null) {
            pinValidator = new PinValidator(account);
        }
        valid = pinValidator.validate(pinCode);
        if (valid) {
            pinValidator = null;
        }
        return valid;
    }

    public boolean checkAccount(Integer accountNumber) throws AccountNotFoundException {
        Account temp = data.getAccountByNumber(accountNumber);
        if (temp == null) {
            return false;
        } else {
            this.accountNumber = accountNumber;
            this.account = temp;
            return true;
        }
    }

    /**
     * Проверка баланса текущего аккаунта
     *
     * @return Integer баланс
     */
    public Integer getBalance() {
        if (account != null) {
            return account.getBalance();
        } else return null;
    }

    /**
     * пополнение баланса текущего аккаунта
     *
     * @return Integer баланс после операции
     */
    public Integer depositCash(Integer cash) throws CashMultipleException {
        checkCash(cash);
        return account.depositCash(cash);
    }

    /**
     * Снятие денег с баланса текущего аккаунта
     *
     * @return Integer баланс
     */
    public Integer cashWithdrawal(Integer cash) throws CashMultipleException, NotEnoughCashException {
        checkCash(cash);
        return account.cashWithdrawal(cash);
}

    private void checkCash(Integer cash) throws CashMultipleException {
        if ((cash % 100) != 0) {
            throw new CashMultipleException();
        }
    }
}

