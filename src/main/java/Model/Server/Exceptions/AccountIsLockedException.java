package Model.Server.Exceptions;

public class AccountIsLockedException extends Throwable{
    private int secondsRemain;
    private static final String message = "Account is locked! Remain seconds :";
    public AccountIsLockedException(int secondsRemain) {
        super(message+secondsRemain);
        this.secondsRemain = secondsRemain;
    }

    public int getSecondsRemain() {
        return secondsRemain;
    }
}
