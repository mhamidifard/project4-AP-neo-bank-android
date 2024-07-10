package ir.ac.kntu;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class AddContactActivity extends AppCompatActivity {
    private EditText fistnameEdit, lastnameEdit, phoneEdit;
    private TextView erorrView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        fistnameEdit = findViewById(R.id.addContactFirstname);
        lastnameEdit = findViewById(R.id.addContactLastName);
        phoneEdit = findViewById(R.id.addContactPhone);
        erorrView = findViewById(R.id.addContactErr);
    }

    public void onAddContact(View view) {
        String firstname = fistnameEdit.getText().toString();
        if (firstname.length() == 0) {
            erorrView.setText("invalid first name");
            return;
        }

        String lastname = lastnameEdit.getText().toString();
        if (lastname.length() == 0) {
            erorrView.setText("invalid last name");
            return;
        }
        long phone = 0;
        try {
            phone = Long.parseLong(phoneEdit.getText().toString());
        } catch (Exception e) {
            erorrView.setText("invalid phone");
            return;
        }

        if (DataBase.findByPhone(phone) == null) {
            erorrView.setText("there is not this phone");
            return;
        }

        if (DashboardActivity.getAccount().getPhoneNumber() == phone) {
            erorrView.setText("this is your phone");
            return;
        }

        if(DashboardActivity.getAccount().getContactMap().containsKey(phone)){
            erorrView.setText("duplicate phone number");
            return;
        }
        DashboardActivity.getAccount().addContact(firstname, lastname, phone);
        Toast.makeText(getApplicationContext(), "add contact successfully", Toast.LENGTH_SHORT).show();
        finish();
    }
}