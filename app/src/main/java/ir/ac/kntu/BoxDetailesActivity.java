package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class BoxDetailesActivity extends AppCompatActivity {
    private static Box box;
    private TextView detailes, balance, erorr;
    private EditText amount, nameEdit;
    private ImageView withdraw, deposit;
    private Button delete;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_box_detailes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        detailes = findViewById(R.id.boxDetailesView);
        balance = findViewById(R.id.boxBalanceView);
        erorr = findViewById(R.id.boxDetailesErorr);
        amount = findViewById(R.id.boxEditAmount);
        nameEdit = findViewById(R.id.boxEditName);
        withdraw = findViewById(R.id.boxWithdraw);
        deposit = findViewById(R.id.boxDeposit);
        delete = findViewById(R.id.boxDeleteButton);
        listView = findViewById(R.id.boxTransactionListView);
    }

    @Override
    protected void onResume() {
        super.onResume();
        erorr.setText("");
        detailes.setText(box.toString());
        balance.setText(box.getBalance().toString());
        nameEdit.setText(box.getName());
        if (box.getType() != TypeBox.SIMPLE) {
            deposit.setClickable(false);
            deposit.setAlpha(0f);
        }
        if (box.getType() == TypeBox.REWARD) {
            withdraw.setClickable(false);
            withdraw.setAlpha(0f);
            amount.setEnabled(false);
            amount.setAlpha(0);
        }
        if (box.getBalance() != 0) {
            delete.setClickable(false);
            delete.setAlpha(0);
        } else if (delete.isClickable() == false) {
            delete.setClickable(true);
            delete.setAlpha(1);
        }

        TransactionAdaptor adaptor = new TransactionAdaptor(BoxDetailesActivity.this, (ArrayList<Long>) box.getTransactionList());
        listView.setAdapter(adaptor);
        listView.setClickable(true);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                TransactionDetailsActivity.setTransaction(DataBase.findTransaction(box.getTransactionList().get(i)));
                startActivity(new Intent(BoxDetailesActivity.this, TransactionDetailsActivity.class));
            }
        });

    }

    public void onEdit(View view) {
        erorr.setText("");
        String name = nameEdit.getText().toString();
        if (name.length() == 0) {
            erorr.setText("invalid name");
            return;
        }
        box.setName(name);
    }

    public void onDeposit(View view) {
        erorr.setText("");
        long value = 0;
        try {
            value = Long.parseLong(amount.getText().toString());
        } catch (Exception exception) {
            erorr.setText("invalid amount");
            return;
        }
        if (value > DashboardActivity.getAccount().getBalance()) {
            erorr.setText("not enough balance");
            return;
        }
        if (value <= 0) {
            erorr.setText("invalid amount");
            return;
        }
        DashboardActivity.getAccount().deposit(box, value);
        onResume();
    }

    public void onWithdraw(View view) {
        erorr.setText("");
        long value = 0;
        try {
            value = Long.parseLong(amount.getText().toString());
        } catch (Exception exception) {
            erorr.setText("invalid amount");
            return;
        }
        if (value > box.getBalance()) {
            erorr.setText("not enough balance");
            return;
        }
        if (value <= 0) {
            erorr.setText("invalid amount");
            return;
        }
        DashboardActivity.getAccount().withdraw(box, value);
        onResume();
    }

    public void onDelete(View view) {
        DashboardActivity.getAccount().removeBox(box);
        finish();
    }


    public static Box getBox() {
        return box;
    }

    public static void setBox(Box box) {
        BoxDetailesActivity.box = box;
    }
}