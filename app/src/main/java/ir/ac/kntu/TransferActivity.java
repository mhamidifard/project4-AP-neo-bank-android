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

public class TransferActivity extends AppCompatActivity {
    private EditText accNumEdit;
    private TextView erorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transfer);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        accNumEdit = findViewById(R.id.transferAccNum);
        erorr = findViewById(R.id.transferAccNumErr);
    }

    public void onNext(View view) {
        long accNum;
        try {
            accNum = Long.parseLong(accNumEdit.getText().toString());
        } catch (Exception e) {
            erorr.setText("invalid account number");
            return;
        }

        if (DataBase.findByAccNum(accNum) == null) {
            erorr.setText("there is not this account number");
            return;
        }

        if (DashboardActivity.getAccount().getAccountNumber() == accNum) {
            erorr.setText("this is your account");
            return;
        }
        AmountTransferActivity.setToAccount(accNum);
        startActivity(new Intent(TransferActivity.this, AmountTransferActivity.class));
        finish();
    }
}