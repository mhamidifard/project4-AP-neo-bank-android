package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmTransferActivity extends AppCompatActivity {
    private static long toAccount;
    private static long amount;
    private TextView detailes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_transfer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        detailes=findViewById(R.id.confirmDetailes);
        Account toAccountObj=DataBase.findByAccNum(toAccount);
        detailes.setText("to: " + toAccount + "\n\nname: " +toAccountObj.getFirstName()+" "+toAccountObj.getLastName()  + "\n\namount: " + amount);
    }

    public void onConfirm(View view){
        long navId=DashboardActivity.getAccount().doFariTo(toAccount,amount);
        TransactionDetailsActivity.setTransaction(DataBase.findTransaction(navId));
        startActivity(new Intent(ConfirmTransferActivity.this,TransactionDetailsActivity.class));
        finish();
    }

    public static long getToAccount() {
        return toAccount;
    }

    public static void setToAccount(long toAccount) {
        ConfirmTransferActivity.toAccount = toAccount;
    }

    public static long getAmount() {
        return amount;
    }

    public static void setAmount(long amount) {
        ConfirmTransferActivity.amount = amount;
    }
}