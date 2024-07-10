package ir.ac.kntu;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class TransactionDetailsActivity extends AppCompatActivity {
    static Transaction transaction;
    TextView details;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_transaction_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        details=findViewById(R.id.transactionDetals);
        details.setText(transaction.toStringComplete(DashboardActivity.getAccount()));

    }

//    public static Transaction getTransaction() {
//        return transaction;
//    }
//
//    public static void setTransaction(Transaction transaction) {
//        TransactionDetailsActivity.transaction = transaction;
//    }
}