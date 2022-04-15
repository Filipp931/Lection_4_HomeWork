package Business.Server;

import Business.Server.Exceptions.AccountIsLockedException;
import Business.Server.Exceptions.CashWithdrawalException;
import Business.Server.Exceptions.InvalidPinException;
import DAO.Account;
import DAO.Exceptions.AccountNotFoundException;
import DAO.Data;
import DAO.Exceptions.NotEnoughCashException;

public class TerminalServer{
    private static TerminalServer terminalServer = new TerminalServer();
    private Account account;
    private PinValidator pinValidator = null ;
    /**
     * Поиск аккаунта по уникальному номеру и верификация
     * @param pin - пин код для проверки
     * @return boolean - прошла ли верификация
     */
    public boolean verifyAndGetAccount(int[] pin, Long accountNumber){
        Data data = new Data();
        Account temp = null;
        Boolean valid = false;
        try {
            temp = data.getAccountByNumber(accountNumber);
        } catch (AccountNotFoundException e) {
            System.err.println(e.getMessage());
        }
        if( pinValidator == null) {
            pinValidator = new PinValidator(temp);
        }
        try {
            valid = pinValidator.validate(pin);
        } catch (AccountIsLockedException e) {
            System.err.println(e.getMessage());
        } catch (InvalidPinException e) {
            System.err.println(e.getMessage());
        }
        //если верификация пройдена, то работаем с аккаунтом
        if(valid) {
            account = temp;
            pinValidator = null;
        }
        return valid;
    }

    /**
     * Проверка баланса текущего аккаунта
     * @return Integer баланс
     */
    public Integer getBalance(){
        if(account != null){
            return account.getBalance();
        }
        else return null;
    }
    /**
     * пополнение баланса текущего аккаунта
     * @return Integer баланс после операции
     */
    public Integer depositCash(Integer cash){
        if(account != null){
            return account.depositCash(cash);
        }
        else return null;
    }
    /**
     * Снятие денег с баланса текущего аккаунта
     * @return Integer баланс
     */
    public Integer cashWithdrawal(Integer cash){
        if((cash % 100) != 0 ) {
            try {
                throw new CashWithdrawalException();
            } catch (CashWithdrawalException e) {
                System.err.println(e.getMessage());
            }
        } else {
            try {
                return account.cashWithdrawal(cash);
            } catch (NotEnoughCashException e) {
                System.err.println(e.getMessage());
            }
        }
        return null;
    }


}

