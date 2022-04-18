package Model.DAO;

import Model.DAO.Exceptions.NotEnoughCashException;

/**
 *
 */
public class Account {
    private final int[] pin; //пин-код
    private Integer balance; //деньги
    private final String owner; //ФИО владельца
    private final Integer accountNumber; //уникальный номер аккаунта
    private boolean isBlocked;
    private long blockTime;

    public Account(int[] pin, String owner, Integer accountNumber, Integer balance) {
        this.pin = pin;
        this.owner = owner;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public Integer getBalance() {
        return balance;
    }

    public Integer depositCash(Integer cash) {
        balance += cash;
        return balance;
    }

    public void setBlocked() {
        isBlocked = true;
        blockTime = System.currentTimeMillis();
    }

    public Integer cashWithdrawal(int cash) throws NotEnoughCashException {
        if (balance >= cash) {
            balance -= cash;
            return balance;
        } else {
            throw new NotEnoughCashException(balance);
        }
    }

    public void unblock(){
        isBlocked = false;
    }

    public long getBlockTime() {
        return blockTime;
    }

    public boolean isBlocked(){
        return isBlocked;
    }

    public boolean checkPin(int[] pin) {
        if((pin == null) || (pin.length != this.pin.length)) return false;
        for(int i=0; i < pin.length; i++) {
            if(this.pin[i] != pin[i]) return false;
        }
        return true;
    }

}
