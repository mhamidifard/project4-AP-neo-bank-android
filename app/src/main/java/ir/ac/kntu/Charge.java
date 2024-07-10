package ir.ac.kntu;

public class Charge extends Transaction {
    private long account;

    public Charge(long value, long account) {
        super(value, TraType.CHARGE);
        setToAccount(account);
    }

    public long getAccount() {
        return account;
    }

    public void setToAccount(long account) {
        this.account = account;
    }
    
    @Override
    public String toStringComplete(Account account) {
        return "charge\n" +
                "\namount: " + getValue() +
                "\n\nnav id: " + getNavId() +
                "\n\n"+Helper.DateToString(getDate());
    }
}