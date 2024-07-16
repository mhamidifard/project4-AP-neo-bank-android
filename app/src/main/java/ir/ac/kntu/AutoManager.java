package ir.ac.kntu;

import java.time.Instant;

public class AutoManager implements Runnable{
    private static boolean live=true;
    @Override
    public void run() {
        while (live){
            Instant nowTime= Calendar.now();
            for (int i = 0; i <DataBase.getRewardBoxes().size(); i++) {
                if(nowTime.isAfter(DataBase.getRewardBoxes().get(i).getDate())){
                    RewardBox box=DataBase.getRewardBoxes().get(i);
                    box.payProfit();
                    box.getAccount().withdraw(box,box.getBalance());
                    DataBase.getRewardBoxes().remove(i);
                    i--;
                }
            }

            for (int i = 0; i < DataBase.getPendingSuppReq().size(); i++) {
                sendAutoMessage(DataBase.getPendingSuppReq().get(i));
                DataBase.getPendingSuppReq().remove(i);
                i--;
            }

            for (int i = 0; i < DataBase.getPendingLoan().size(); i++) {
                checkLoan(DataBase.getPendingLoan().get(i));
                DataBase.getPendingLoan().remove(i);
                i--;
            }
        }
    }

    public static void sendAutoMessage(SupportRequest request){
        request.addMessage("Our colleagues will contact you soon",Sender.SUPPORT);
    }

    public static void checkLoan(Loan loan){
        Account account=DataBase.findByAccNum(loan.getAccountNumber());
        for(Loan otherLoan: account.getAcceptedLoans()){
            if(otherLoan.isDelay()){
                DataBase.rejectLoan(loan);
                return;
            }
        }

        if(account.getScore()<loan.getAmount()){
            DataBase.rejectLoan(loan);
            return;
        }
        DataBase.acceptLoan(loan);
        account.setScore(account.getScore()- loan.getAmount());

    }

    public static void setLive(boolean live) {
        AutoManager.live = live;
    }
}
