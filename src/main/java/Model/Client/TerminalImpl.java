package Model.Client;

import DAO.Exceptions.AccountNotFoundException;
import DAO.Exceptions.NotEnoughCashException;
import Model.Server.Exceptions.AccountIsLockedException;
import Model.Server.Exceptions.CashMultipleException;
import Model.Server.Exceptions.InvalidPinException;
import Model.Server.TerminalServer;
import View.TerminalController;

public class TerminalImpl implements Terminal{
    private TerminalServer terminalServer = TerminalServer.getTerminalServer();
    private TerminalController controller;

    public TerminalImpl(TerminalController controller) {
        this.controller = controller;
    }

    public boolean verifyPinCode(Integer pin)  {
        Boolean isVerified = false;
        try {
            isVerified = terminalServer.verifyPinCode(pin);
        } catch (AccountIsLockedException e) {
            controller.printMessage("Аккаунт заблокирован, подождите " + e.getSecondsRemain()+" секунд;");
        } catch (InvalidPinException e) {
            controller.printMessage("Неверный Pin-код");
        }
        return isVerified;
    }

    @Override
    public Integer checkAmount() {
        return terminalServer.getBalance();
    }

    public boolean checkAccount(Integer accountNumber) {
        try {
            return terminalServer.checkAccount(accountNumber);
        } catch (AccountNotFoundException e) {
            controller.printMessage(("Аккаунт с номером "+accountNumber+" не найден"));
            return false;
        }
    }
    @Override
    public Integer deposit(Integer cash) {
        try {
            return terminalServer.depositCash(cash);
        } catch (CashMultipleException e) {
            controller.printMessage("Cумма должна быть кратна 100");
        } return terminalServer.getBalance();
    }

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
