package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.time.Instant;

public class LoanDetailesActivity extends AppCompatActivity {
    private static Loan loan;
    private TextView detailes;
    private ListView listView;
    private static Instant nowDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan_detailes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        detailes=findViewById(R.id.loanDetailesText);
        listView=findViewById(R.id.installmentListView);


    }

    @Override
    protected void onResume() {
        super.onResume();
        detailes.setText(loan.toString());
        nowDate=Calendar.now();
        InstallmentAdaptor adaptor=new InstallmentAdaptor(LoanDetailesActivity.this,loan.getInstallments());
        listView.setAdapter(adaptor);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(loan.getInstallments().get(i).isPeyed()){
                    TransactionDetailsActivity.setTransaction(DataBase.findTransaction(loan.getInstallments().get(i).getTransaction()));
                    startActivity(new Intent(LoanDetailesActivity.this,TransactionDetailsActivity.class));
                }else {
                    ConfirmInstallmentActivity.setPosition(i);
                    startActivity(new Intent(LoanDetailesActivity.this,ConfirmInstallmentActivity.class));
                }
            }
        });
    }

    public static Loan getLoan() {
        return loan;
    }

    public static void setLoan(Loan loan) {
        LoanDetailesActivity.loan = loan;
    }

    public static Instant getNowDate() {
        return nowDate;
    }

    public static void setNowDate(Instant nowDate) {
        LoanDetailesActivity.nowDate = nowDate;
    }
}