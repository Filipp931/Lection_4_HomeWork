package Business.Server;
import Business.Server.Exceptions.AccountIsLockedException;
import Business.Server.Exceptions.InvalidPinException;
import DAO.Account;

public class PinValidator {
    private Account account;
    private boolean pinIsCorrect;
    private static Integer numberOfIncorrectAttempts=0;
    public PinValidator(Account account) {
        this.account = account;
    }
    public boolean validate(int[] pin) throws AccountIsLockedException, InvalidPinException {
        if(checkAccountIsBlocked(account)) {
            throw new AccountIsLockedException(secondsRemain(account));
        }
        pinIsCorrect = account.checkPin(pin);
        if(!pinIsCorrect) {
            numberOfIncorrectAttempts ++;
            if(numberOfIncorrectAttempts >= 3) {
                account.setBlocked();
                throw new AccountIsLockedException(secondsRemain(account));
            }
            throw new InvalidPinException();
        }
        numberOfIncorrectAttempts = 0;
        return pinIsCorrect;
    }
    private boolean checkAccountIsBlocked(Account account) {
        if (!account.isBlocked()) {
            return false;
        } else {
            int seconds = secondsRemain(account);
            if (seconds < 0) {
                account.unblock();
                numberOfIncorrectAttempts = 0;
                return false;
            }
            return true;
        }
    }
    private int secondsRemain(Account account){
        return (int) ( 10 - ((System.currentTimeMillis() - account.getBlockTime()) / 1000));
    }
}
