package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ListTransactionActivity extends AppCompatActivity {
    //RecyclerView listView;
    ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_transaction);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listTransaction), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.listview);
        TransactionAdaptor listAdapter=new TransactionAdaptor(ListTransactionActivity.this,(ArrayList<Long>) DashboardActivity.getAccount().getTransactions());
        listView.setAdapter(listAdapter);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Transaction transaction =DataBase.findTransaction(DashboardActivity.getAccount().getTransactions().get(i));
                Toast.makeText(getApplicationContext(),transaction.getNavId()+"",Toast.LENGTH_SHORT).show();
                TransactionDetailsActivity.transaction=transaction;
                startActivity(new Intent(ListTransactionActivity.this,TransactionDetailsActivity.class));
            }
        });

    }
}