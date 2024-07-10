package ir.ac.kntu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;

public class MainActivity extends AppCompatActivity {

    private EditText textPhone;
    private EditText textPassword;
    private TextView textErorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.chargeErorr), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        DataBase.firstWorks();
        textPhone = findViewById(R.id.userPhone);
        textPassword = findViewById(R.id.userPassword);
        textErorr = findViewById(R.id.loginErorr);

    }

    public void checkLogin(View view) {
        Long phone = Long.parseLong(textPhone.getText().toString());
        Account account = DataBase.findByPhone(phone);
        if (account == null) {
            textErorr.setText("invalid phone");
            return;
        }
        String password = textPassword.getText().toString();
        if (!account.passwordEqual(password)) {
            textErorr.setText("invalid password");
        } else if (!account.isVerifyStatus()) {
            textErorr.setText("the account is not verified");
        } else {
            Intent intent=new Intent(this,DashboardActivity.class);
            //intent.putExtra("account",account);
            DashboardActivity.setAccount(account);
            startActivity(intent);
        }
    }

    public void onRegister(View view){
        startActivity(new Intent(this,RegisterActivity.class));
    }
}