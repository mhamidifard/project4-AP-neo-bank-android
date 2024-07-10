package ir.ac.kntu;

import java.time.Instant;

enum TraType {
    TRANSFER, CHARGE,BOXTRANSACTION,SIMCHARGE;
}

public abstract class Transaction {
    private long value;
    private Instant date;
    private TraType type;
    private long navId;

    public Transaction(long value, TraType type) {
        setDate(Calendar.now());
        setType(type);
        setValue(value);
        setNavId(createId());
    }

    private long createId() {
        return 12300000 + DataBase.getTransactions().size();
    }

//    @Override
//    public abstract String toString();

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public TraType getType() {
        return type;
    }

    public void setType(TraType type) {
        this.type = type;
    }

    public long getNavId() {
        return navId;
    }

    public void setNavId(long navId) {
        this.navId = navId;
    }

    public long getValue() {
        return value;
    }

    public void setValue(long value) {
        this.value = value;
    }

    public  String summery(){
        return type+"   value: "+getValue()+"  nav id: "+getNavId()+" date: "+getDate();
    }

    public abstract String toStringComplete(Account account);
}
