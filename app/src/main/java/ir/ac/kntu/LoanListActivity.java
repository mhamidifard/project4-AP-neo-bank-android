package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class LoanListActivity extends AppCompatActivity {
    private Button addLoanButton;
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_loan_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.loanRequestListView);
        addLoanButton=findViewById(R.id.addLoanButton);
        if(getIntent().hasExtra("acceptedLoan")){
            listView.setClickable(true);
            addLoanButton.setClickable(false);
            addLoanButton.setAlpha(0);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Loan> loanRequests;
        if(getIntent().hasExtra("acceptedLoan")){
            loanRequests=(ArrayList<Loan>) DashboardActivity.getAccount().getAcceptedLoans();
        }else {
            loanRequests = (ArrayList<Loan>) DashboardActivity.getAccount().getLoanRequests();
        }
        LoanAdaptor adaptor=new LoanAdaptor(LoanListActivity.this,loanRequests);
        listView.setAdapter(adaptor);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(getIntent().hasExtra("acceptedLoan")) {
                    LoanDetailesActivity.setLoan(loanRequests.get(i));
                    startActivity(new Intent(LoanListActivity.this, LoanDetailesActivity.class));
                }
            }
        });

    }

    public void onAddLoan(View view){
        startActivity(new Intent(LoanListActivity.this,AddLoanActivity.class));
    }
}