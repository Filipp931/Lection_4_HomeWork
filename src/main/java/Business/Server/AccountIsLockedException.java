package Business.Server;

public class AccountIsLockedException extends Throwable{
    private static final String message = "Account is locked! Remain seconds :";
    public AccountIsLockedException(int secondsRemain) {
        super(message+secondsRemain);
    }
}
