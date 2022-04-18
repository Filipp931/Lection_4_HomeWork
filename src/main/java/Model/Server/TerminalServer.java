package Model.Server;

import Model.Server.Exceptions.AccountIsLockedException;
import Model.Server.Exceptions.CashMultipleException;
import Model.Server.Exceptions.InvalidPinException;
import DAO.Account;
import DAO.Exceptions.AccountNotFoundException;
import DAO.Data;
import DAO.Exceptions.NotEnoughCashException;

public class TerminalServer implements Server {
    private static TerminalServer terminalServer = new TerminalServer();
    private Account account;
    private PinValidator pinValidator = null;
    private static Data data = Data.getData();
    private Integer accountNumber;

    public static TerminalServer getTerminalServer() {
        return terminalServer;
    }

    /**
     * Проверка Pin кода
     * @param pin
     * @return true если pin соответствует
     * @throws AccountIsLockedException
     * @throws InvalidPinException
     */
    @Override
    public boolean verifyPinCode(Integer pin) throws AccountIsLockedException, InvalidPinException {
        Boolean valid = false;
        int[] pinCode = Integer.toString(pin).chars().map(c -> c - '0').toArray();
        if(pinCode.length < 4) {
            return false;
        }
        if (pinValidator == null) {
            pinValidator = new PinValidator(account);
        }
        valid = pinValidator.validate(pinCode);
        if (valid) {
            pinValidator = null;
        }
        return valid;
    }

    /**
     * Проверка аккаунта по номеру
     * @param accountNumber
     * @return true если аккаунт найден
     * @throws AccountNotFoundException
     */
    @Override
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
    @Override
    public Integer getBalance() {
        if (account != null) {
            return account.getBalance();
        } else return null;
    }

    /**
     * пополнение баланса текущего аккаунта
     *
     * @return Integer баланс после операции
     * @throws CashMultipleException
     */
    @Override
    public Integer depositCash(Integer cash) throws CashMultipleException {
        checkCash(cash);
        return account.depositCash(cash);
    }

    /**
     * Снятие денег с баланса текущего аккаунта
     *
     * @return Integer баланс
     * @throws CashMultipleException
     * @throws NotEnoughCashException
     */
    @Override
    public Integer cashWithdrawal(Integer cash) throws CashMultipleException, NotEnoughCashException {
        checkCash(cash);
        return account.cashWithdrawal(cash);
}

    /**
     * Проверка кратности суммы
     * @param cash
     * @throws CashMultipleException
     */
    private void checkCash(Integer cash) throws CashMultipleException {
        if ((cash % 100) != 0) {
            throw new CashMultipleException();
        }
    }
}

