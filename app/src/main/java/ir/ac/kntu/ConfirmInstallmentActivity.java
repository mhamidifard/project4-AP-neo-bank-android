package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConfirmInstallmentActivity extends AppCompatActivity {
    private static int position;
    private TextView text;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_confirm_installment);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        Installment installment=LoanDetailesActivity.getLoan().getInstallments().get(position);
        text=findViewById(R.id.confirmInstallmentText);
        confirmButton=findViewById(R.id.confirmInstallmentButton);
        if(installment.getAmount()>DashboardActivity.getAccount().getBalance()){
            text.setText("not enough balance");
            confirmButton.setClickable(false);
            confirmButton.setAlpha(0);
        }else {
            text.setText("number : "+(position+1)+"\n\namount : "+installment.getAmount());
        }
    }

    public void onConfirm(View view){
        long navId=LoanDetailesActivity.getLoan().payInstallment(position);
        TransactionDetailsActivity.setTransaction(DataBase.findTransaction(navId));
        startActivity(new Intent(ConfirmInstallmentActivity.this,TransactionDetailsActivity.class));
        finish();
    }

    public static int getPosition() {
        return position;
    }

    public static void setPosition(int position) {
        ConfirmInstallmentActivity.position = position;
    }
}