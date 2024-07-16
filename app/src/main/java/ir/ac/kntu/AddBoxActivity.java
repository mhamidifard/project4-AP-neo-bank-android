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

public class AddBoxActivity extends AppCompatActivity {
    private static TypeBox type;
    private EditText nameEdit, amountEdit, daysEdit;
    private TextView erorr;
    private String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_box);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        nameEdit = findViewById(R.id.boxNameEdit);
        amountEdit = findViewById(R.id.boxAmountEdit);
        daysEdit = findViewById(R.id.boxDaysEdit);
        erorr = findViewById(R.id.addBoxErorr);

        if (type != TypeBox.REWARD) {
            amountEdit.setEnabled(false);
            amountEdit.setAlpha(0);
            daysEdit.setEnabled(false);
            daysEdit.setAlpha(0);
        }


    }

    public void switchAddBox(View view) {
        name = nameEdit.getText().toString();
        if (name.length() == 0) {
            erorr.setText("invalid name");
            return;
        }
        if (type == TypeBox.REWARD) {
            addRewardBox();
        } else {
            DashboardActivity.getAccount().addBox(new Box(0L, type, name));
            finish();
        }
    }

    public void addRewardBox() {
        long amount;
        long days;
        try {
            amount = Long.parseLong(amountEdit.getText().toString());
        } catch (Exception e) {
            erorr.setText("invalid amount");
            return;
        }
        if (amount > DashboardActivity.getAccount().getBalance()) {
            erorr.setText("not enoghf balance");
            return;
        }
        if (amount < 100L) {
            erorr.setText("amount is too small");
            return;
        }

        try {
            days = Long.parseLong(daysEdit.getText().toString());
        } catch (Exception e) {
            erorr.setText("invalid days");
            return;
        }
        if (days < 30 || days > 365) {
            erorr.setText("invalid days");
            return;
        }
        RewardBox box = new RewardBox(0L, days, name);
        DashboardActivity.getAccount().addBox(box);
        DashboardActivity.getAccount().deposit(box, amount);
        finish();

    }

    public static TypeBox getType() {
        return type;
    }

    public static void setType(TypeBox type) {
        AddBoxActivity.type = type;
    }
}