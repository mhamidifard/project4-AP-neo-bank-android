package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class DashboardActivity extends AppCompatActivity {
    private static Account account;
    private TextView balanceView;
    private Handler handler=new Handler();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dashboard);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chargeErorr), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        balanceView=findViewById(R.id.balance);
//        Account account=(Account)getIntent().getSerializableExtra("account");
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        //balanceView.setText(account.getBalance()+"");
    }

    public void onBalance(View view) throws InterruptedException {
        balanceView.setText(account.getBalance()+"");
    }

    public void onCharge(View view){
        startActivity(new Intent(this,ChargeActivity.class));
    }

    public void onTransaction(View view){
        startActivity(new Intent(this,ListTransactionActivity.class));
    }

    public void onContact(View view){
        startActivity(new Intent(DashboardActivity.this, ContactListActivity.class));
    }

    public void onTransfer(View view){
        startActivity(new Intent(DashboardActivity.this,TransferActivity.class));
    }

    public static void setAccount(Account account) {
        DashboardActivity.account = account;
    }

    public static Account getAccount() {
        return account;
    }
}