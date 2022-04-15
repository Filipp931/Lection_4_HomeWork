package Business.Client;

import Business.Server.PinValidator;
import Business.Server.TerminalServer;

public class TerminalImpl implements Terminal{
    private final TerminalServer server = new TerminalServer();


    @Override
    public String checkAmount() {
        return null;
    }

    @Override
    public Integer deposit(Integer amount) {
        return null;
    }

    @Override
    public Integer withdraw(Integer amount) {
        return null;
    }
}
