package ir.ac.kntu;

import android.app.Activity;
import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.TextView;

public class MyService extends Service implements Runnable {
    private  static TextView balanceView;
    private Handler handler;
    public MyService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        handler=new Handler();
        handler.post(this);

    }

    @Override
    public void run() {
        balanceView.animate().alpha(0).setDuration(1000);
        //balanceView.animate().alpha(1).setDuration(1000).setStartDelay(1000);
        for (int i = 0; i < 1000; i++) {
            balanceView.setText(i+"");
        }
    }
}