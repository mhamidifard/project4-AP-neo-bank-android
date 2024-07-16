package ir.ac.kntu;

import androidx.annotation.NonNull;

import java.time.Instant;
import java.util.ArrayList;

enum LoanStatus {
    CREATED, ACCEPTED, FINISHED, REJECTED;
}

public class Loan {
    private boolean accepted = false;
    private boolean finished = false;
    private boolean rejected = false;
    private LoanStatus status = LoanStatus.CREATED;
    private int monthes;
    private long amount;
    private long accountNumber;
    private int currentInstallment = 0;
    private ArrayList<Installment> installments;
    private int payedInstallment;


    public Loan(int monthes, long amount, long accountNumber) {
        this.monthes = monthes;
        this.amount = amount;
        this.accountNumber = accountNumber;
    }

    public void accept() {
        Instant date = Calendar.now();
        installments = new ArrayList<>(monthes);
        long everyInstall = amount / monthes;
        date = date.plusSeconds(30 * 24 * 60 * 60);
        installments.add(new Installment(amount - everyInstall * (monthes - 1), date));
        for (int i = 0; i < monthes - 1; i++) {
            date = date.plusSeconds(30 * 24 * 60 * 60);
            installments.add(new Installment(everyInstall, date));
        }
        doLoan();
    }

    public void doLoan() {
        DataBase.findByAccNum(accountNumber).payLoan(this);
        accepted = true;
        status = LoanStatus.ACCEPTED;
    }

    public long payInstallment(int position) {
        long navId;
        long cost = installments.get(position).getAmount();
        Account account = DataBase.findByAccNum(accountNumber);
        if (cost > account.getBalance()) {
            throw new RuntimeException("Not enough balance");
        }
        navId = account.payInstallment(installments.get(position).getAmount());
        installments.get(position).setPeyed(true);
        installments.get(position).setTransaction(navId);
        payedInstallment++;

        Instant now=Calendar.now();
        if(installments.get(position).getDate().isAfter(now)){
            account.setScore(account.getScore()+installments.get(position).getAmount()*2);
        }

        if (payedInstallment >= monthes) {
            finished = true;
            status = LoanStatus.FINISHED;
            DataBase.finishLoan(this);
        }
        return navId;
    }

    public boolean isFinished() {
        return finished;
    }

    public void setFinished(boolean finished) {
        this.finished = finished;
    }

    public int getMonthes() {
        return monthes;
    }

    public void setMonthes(int monthes) {
        this.monthes = monthes;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(long accountNumber) {
        accountNumber = accountNumber;
    }

    public int getCurrentInstallment() {
        return currentInstallment;
    }

    public Installment getCurrentInstallmentObj() {
        return installments.get(currentInstallment);
    }

    public void setCurrentInstallment(int currentInstallment) {
        this.currentInstallment = currentInstallment;
    }

    public ArrayList<Installment> getInstallments() {
        return installments;
    }

    public void setInstallments(ArrayList<Installment> installments) {
        this.installments = installments;
    }

    public boolean isAccepted() {
        return accepted;
    }

    public void setAccepted(boolean accepted) {
        this.accepted = accepted;
    }

    public boolean isRejected() {
        return rejected;
    }

    public void setRejected(boolean rejected) {
        this.rejected = rejected;
    }

    public LoanStatus getStatus() {
        return status;
    }

    public void setStatus(LoanStatus status) {
        this.status = status;
    }

    @NonNull
    @Override
    public String toString() {
        return "amount : " + amount +
                "\n\nmonthes : " + monthes;
    }

    public int getPayedInstallment() {
        return payedInstallment;
    }

    public void setPayedInstallment(int payedInstallment) {
        this.payedInstallment = payedInstallment;
    }

    public boolean isDelay() {
        if (finished) {
            return false;
        }
        for (Installment installment : installments) {
            Instant now = Calendar.now();
            if (!installment.isPeyed() && installment.getDate().isBefore(now)) {
                return true;
            }
        }
        return false;
    }
}
