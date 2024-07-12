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
        }
    }

    public static void setLive(boolean live) {
        AutoManager.live = live;
    }
}
