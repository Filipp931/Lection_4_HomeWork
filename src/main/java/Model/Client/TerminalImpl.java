package Model.Client;

import DAO.Exceptions.AccountNotFoundException;
import DAO.Exceptions.NotEnoughCashException;
import Model.Server.Exceptions.AccountIsLockedException;
import Model.Server.Exceptions.CashMultipleException;
import Model.Server.Exceptions.InvalidPinException;
import Model.Server.TerminalServer;
import View.TerminalController;
import javafx.application.Platform;

public class TerminalImpl implements Terminal{
    private final TerminalServer terminalServer = TerminalServer.getTerminalServer();
    private final TerminalController controller;

    public TerminalImpl(TerminalController controller) {
        this.controller = controller;
    }

    /**
     * Проверка pin кода
     * @param pin
     * @return boolean - isValid
     */
    public boolean verifyPinCode(Integer pin)  {
        Boolean isValid = false;
        try {
            isValid = terminalServer.verifyPinCode(pin);
        } catch (AccountIsLockedException e) {
            controller.printMessage("Аккаунт заблокирован, подождите " + e.getSecondsRemain()+" секунд;");
            controller.resetPinFiled();
        } catch (InvalidPinException e) {
            controller.printMessage("Неверный Pin-код");
            controller.resetPinFiled();
        }
        if(isValid){
            controller.printMessage("Pin-код введен успешно");
            controller.resetPinFiled();
        }
        return isValid;
    }

    /**
     * Получение баланса текущего аккаунта
     * @return Integer - баланс текущего аккаунта
     */
    @Override
    public Integer checkAmount() {
        return terminalServer.getBalance();
    }

    public boolean checkAccount(Integer accountNumber) {
        try {
            boolean isFind = terminalServer.checkAccount(accountNumber);
            if(isFind) {
                controller.printMessage("Аккаунт найден, введите Pin");
            }
            return isFind;
        } catch (AccountNotFoundException e) {
            controller.printMessage(("Аккаунт с номером "+accountNumber+" не найден"));
            return false;
        }
    }

    /**
     * Внесение денег на счет текущего аккаунта
     * @param cash - сумма для внесения
     * @return Integer - баланс счета текущего аккаунта после поплнения
     */
    @Override
    public Integer deposit(Integer cash) {
        try {
            terminalServer.depositCash(cash);
            controller.printMessage("Сумма успешно внесена");
        } catch (CashMultipleException e) {
            controller.printMessage("Cумма должна быть кратна 100");
        }
        return terminalServer.getBalance();
    }

    /**
     * Снятие денег со счета текущего аккаунта
     * @param amount - сумма для снятия
     * @return Integer - баланс счета текущего аккаунта после снятия
     */
    @Override
    public Integer withdraw(Integer amount) {
        Integer current = terminalServer.getBalance();
        try {
            terminalServer.cashWithdrawal(amount);
        } catch (CashMultipleException e) {
            controller.printMessage("Cумма должна быть кратна 100");
        } catch (NotEnoughCashException e) {
            controller.printMessage("Недостаточно денег на счете \n сумма не должна превышать "+terminalServer.getBalance());
        }
        if(current != terminalServer.getBalance()) {
            controller.printMessage("Деньги успешно сняты");
        }
        return terminalServer.getBalance();
    }
}
