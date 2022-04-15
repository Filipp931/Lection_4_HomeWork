package Business.Client;

public interface Terminal {
    public String checkAmount();
    public Integer deposit(Integer amount);
    public Integer withdraw(Integer amount);
}
