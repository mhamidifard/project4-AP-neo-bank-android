package ir.ac.kntu;


import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;

enum TypeBox {
    SIMPLE, SMALLMONEY, REWARD;
}

public class Box {
    private String name;
    private Long balance;
    private TypeBox type;
    private List<Long> transactionList = new ArrayList<>();


    public Box(Long balance, TypeBox type, String name) {
        this.balance = balance;
        this.type = type;
        this.name = name;
    }

    public void addTransaction(long navId) {
        transactionList.add(0, navId);
    }

    public void withdraw(long amount) {
        balance -= amount;
    }

    public void deposit(long amount) {
        balance += amount;
    }

    public Long getBalance() {
        return balance;
    }

    public void setBalance(Long balance) {
        this.balance = balance;
    }

    public TypeBox getType() {
        return type;
    }

    public void setType(TypeBox type) {
        this.type = type;
    }

    public String summery() {
        return type + "  " + balance;
    }

    @Override
    public String toString() {
//        return "balance=" + balance+
//                "\n\nname : "+getName()+
        return "type : " + getType();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Long> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Long> transactionList) {
        this.transactionList = transactionList;
    }
}
