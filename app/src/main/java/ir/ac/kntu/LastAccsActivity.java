package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class LastAccsActivity extends AppCompatActivity {
    private ListView listView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_last_accs);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ArrayList <LastTransfer> lastAccs= (ArrayList<LastTransfer>) DashboardActivity.getAccount().getLastTransferAccs();
        listView=findViewById(R.id.lastAccsList);
        LastAccsAdaptor adaptor=new LastAccsAdaptor(LastAccsActivity.this,lastAccs);
        listView.setAdapter(adaptor);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                AmountTransferActivity.setToAccount(lastAccs.get(i).getAccountNum());
                startActivity(new Intent(LastAccsActivity.this,AmountTransferActivity.class));
                finish();
            }
        });
    }
}