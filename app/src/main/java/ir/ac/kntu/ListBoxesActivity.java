package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class ListBoxesActivity extends AppCompatActivity {
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_boxes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        listView=findViewById(R.id.boxListView);
    }

    @Override
    protected void onResume() {
        super.onResume();

        BoxAdaptor adaptor=new BoxAdaptor(ListBoxesActivity.this,(ArrayList<Box>) DashboardActivity.getAccount().getBoxes());
        listView.setAdapter(adaptor);
        listView.setClickable(true);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                BoxDetailesActivity.setBox(DashboardActivity.getAccount().getBoxes().get(i));
                startActivity(new Intent(ListBoxesActivity.this,BoxDetailesActivity.class));
            }
        });


    }

    public void onSimpleBox(View view){
        AddBoxActivity.setType(TypeBox.SIMPLE);
        startActivity(new Intent(ListBoxesActivity.this,AddBoxActivity.class));
    }

    public void onRewardBox(View view){
        AddBoxActivity.setType(TypeBox.REWARD);
        startActivity(new Intent(ListBoxesActivity.this,AddBoxActivity.class));
    }

    public void onSmallMoney(View view){
        AddBoxActivity.setType(TypeBox.SMALLMONEY);
        startActivity(new Intent(ListBoxesActivity.this,AddBoxActivity.class));
    }
}