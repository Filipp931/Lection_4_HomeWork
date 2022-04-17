package DAO;

import DAO.Exceptions.AccountNotFoundException;

import java.util.ArrayList;


public class Data {
    private static Data data = new Data();

    private ArrayList<Account> accountList = new ArrayList<Account>() {
    };

    public Data() {
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Широков Ф.В.", 449220, 400766));
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Иванова А.С.", 459211, 800));
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Левченко С.В.", 448221, 192000));
        accountList.add(new Account(new int[]{1, 2, 3, 4}, "Петров Д.С.", 142239, 198123));
    }

    public Account getAccountByNumber(Integer accountNumber) throws AccountNotFoundException {
        Account acc = null;
        for (Account account : accountList) {
            if (account.getAccountNumber().equals( accountNumber)) {
                acc = account;
            }
        }
        if (acc == null) {
            throw new AccountNotFoundException(accountNumber);
        }
        return acc;
    }

    public static Data getData() {
        return data;
    }
}

