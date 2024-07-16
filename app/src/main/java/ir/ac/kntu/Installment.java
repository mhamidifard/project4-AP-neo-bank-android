package ir.ac.kntu;

import java.time.Instant;

enum InstallmentStatus{
    PAYED,WAITING,DELAYED;
}

public class Installment {
    private boolean peyed=false;
    private long amount;
    private Instant date;
    private long transaction;


    public Installment(long amount, Instant date) {
        this.amount = amount;
        this.date = date;
    }

    public boolean isPeyed() {
        return peyed;
    }

    public void setPeyed(boolean peyed) {
        this.peyed = peyed;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public long getTransaction() {
        return transaction;
    }

    public void setTransaction(long transaction) {
        this.transaction = transaction;
    }

    public InstallmentStatus getStatus(){
        if(isPeyed()){
            return InstallmentStatus.PAYED;
        } else if (date.isAfter(Calendar.now())) {
            return InstallmentStatus.WAITING;
        }else {
            return InstallmentStatus.DELAYED;
        }
    }
}
