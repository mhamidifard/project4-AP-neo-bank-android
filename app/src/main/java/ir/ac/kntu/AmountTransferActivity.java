package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AmountTransferActivity extends AppCompatActivity {
    private static long toAccount;
    private EditText amountEdit;
    private TextView erorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_amount_transfer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        amountEdit=findViewById(R.id.transferAmount);
        erorr=findViewById(R.id.transferAmountErorr);
    }

    public void onNext(View view){
        long amount;
        try{
            amount=Long.parseLong(amountEdit.getText().toString());
        }catch (Exception e){
            erorr.setText("invalid amount");
            return;
        }

        if(DashboardActivity.getAccount().getBalance()<amount+Parametr.getFariToFee()){
            erorr.setText("no enough balance");
            return;
        }
        ConfirmTransferActivity.setToAccount(toAccount);
        ConfirmTransferActivity.setAmount(amount);
        startActivity(new Intent(AmountTransferActivity.this,ConfirmTransferActivity.class));
        finish();
    }

    public static long getToAccount() {
        return toAccount;
    }

    public static void setToAccount(long toAccount) {
        AmountTransferActivity.toAccount = toAccount;
    }
}