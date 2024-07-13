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

public class SupportListActivity extends AppCompatActivity {
    private ListView listview;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_support_list);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listview=findViewById(R.id.supportListView);

    }

    @Override
    protected void onResume() {
        super.onResume();
        ArrayList<Long> arrayList=(ArrayList<Long>) DashboardActivity.getAccount().getSupportRequests();
        SupportAdaptor adaptor=new SupportAdaptor(SupportListActivity.this,arrayList);
        listview.setAdapter(adaptor);
        listview.setClickable(true);
        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                SupportDetailesActivity.setRequest(DataBase.findSuppReq(arrayList.get(i)));
                startActivity(new Intent(SupportListActivity.this,SupportDetailesActivity.class));
            }
        });
    }

    public void onAddRequest(View view){
        startActivity(new Intent(SupportListActivity.this,AddSupportActivity.class));
    }
}