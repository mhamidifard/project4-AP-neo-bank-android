package ir.ac.kntu;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddLoanActivity extends AppCompatActivity {

    private EditText amountEdit, monthesEdit;
    private TextView score, erorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_loan);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        amountEdit = findViewById(R.id.addLoanAmount);
        monthesEdit = findViewById(R.id.addLoanMonthes);
        score = findViewById(R.id.addLoanScore);
        score.setText("score: " + DashboardActivity.getAccount().getScore());
        erorr = findViewById(R.id.addLoanErorr);

    }

    public void onSendRequest(View view) {
        long amount;
        try {
            amount = Long.parseLong(amountEdit.getText().toString());
        } catch (Exception e) {
            return;
        }
        int monthes;
        try {
            monthes = Integer.parseInt(monthesEdit.getText().toString());
        } catch (Exception e) {
            return;
        }
        if (monthes > 24) {
            erorr.setText("maximum monthes is 24");
            return;
        }

        DataBase.addLoan(monthes, amount, DashboardActivity.getAccount().getAccountNumber());
        finish();
    }
}