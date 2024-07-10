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

public class EditContactActivity extends AppCompatActivity {
    private static Contact contact;
    private EditText firstnameEdit,lastnameEdit,phoneEdit;
    private TextView erorrView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_edit_contact);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        firstnameEdit=findViewById(R.id.editContactFirstname);
        lastnameEdit=findViewById(R.id.editContactLastname);
        phoneEdit=findViewById(R.id.editContactPhone);
        erorrView=findViewById(R.id.editContactErorr);
        firstnameEdit.setText(contact.getFirstName());
        lastnameEdit.setText(contact.getLastName());
        phoneEdit.setText(contact.getPhoneNumber()+"");
    }

    public void onSave(View view){
        String firstname=firstnameEdit.getText().toString();
        if(firstname.length()==0){
            erorrView.setText("invalid first name");
            return;
        }

        String lastname=lastnameEdit.getText().toString();
        if(lastname.length()==0){
            erorrView.setText("invalid last name");
            return;
        }

        long phone=0;
        try {
            phone=Long.parseLong(phoneEdit.getText().toString());
        }catch (Exception e){
            erorrView.setText("invalid phone");
            return;
        }

        if(DataBase.findByPhone(phone)==null){
            erorrView.setText("there is not this phone");
            return;
        }

        if(DashboardActivity.getAccount().getPhoneNumber()==phone){
            erorrView.setText("this is your phone");
            return;
        }
        contact.editContact(firstname,lastname,phone);
        finish();

    }

    public void onDelete(View view){
        DashboardActivity.getAccount().getContactMap().remove(contact.getPhoneNumber());
        finish();
    }

    public static Contact getContact() {
        return contact;
    }

    public static void setContact(Contact contact) {
        EditContactActivity.contact = contact;
    }
}