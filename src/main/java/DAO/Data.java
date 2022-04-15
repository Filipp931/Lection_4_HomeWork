package DAO;

import java.util.ArrayList;


public class Data {
    private ArrayList<Account> accountList = new ArrayList<Account>() {
    };

    public Data() {
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Широков Ф.В.", 449220l));
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Иванова А.С.", 459211l));
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Левченко С.В.", 448221l));
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Петров Д.С.", 142239l));
    }

    public Account getAccountByNumber(long accountNumber) throws AccountNotFoundException {
        Account acc = null;
        for (Account account : accountList) {
            if (account.getAccountNumber() == accountNumber) {
                acc = account;
            }
            if (acc == null) {
                throw new AccountNotFoundException("Account with number " + accountNumber + " not found.");
            }
        }
        return acc;
    }
}

