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

public class ChargeActivity extends AppCompatActivity {
    private EditText amountEdit;
    private TextView erorrView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_charge);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chargeErorr), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        amountEdit=findViewById(R.id.editNumber);
        erorrView=findViewById(R.id.chargeErorr);

    }

    public void onDoCharge(View view){
        String temp=amountEdit.getText().toString();
        if(!Helper.isNumber(temp)){
            erorrView.setText("invalid number");
            return;
        }
        DashboardActivity.getAccount().charge(Long.parseLong(temp));
        finish();

    }
}