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

public class RegisterActivity extends AppCompatActivity {
    EditText editFirstName,editLastName,editPhone,editId,editPassword;
    TextView firstNameErorr,lastNameErorr,phoneErorr,idErorr,passwordErorr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.listTransaction), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        defItems();
    }
    public void defItems(){
        editFirstName=findViewById(R.id.regFirstName);
        editLastName=findViewById(R.id.regLastName);
        editPhone=findViewById(R.id.regPhone);
        editId=findViewById(R.id.regId);
        editPassword=findViewById(R.id.regPassword);
        firstNameErorr=findViewById(R.id.regFirstNameErr);
        lastNameErorr=findViewById(R.id.regLastNameErr);
        phoneErorr=findViewById(R.id.regPhoneErr);
        idErorr=findViewById(R.id.regIdErr);
        passwordErorr=findViewById(R.id.regPassErr);
    }
    public void onRegister(View view){
        clearErorrs();
        boolean isOk=true;
        String firstName=editFirstName.getText().toString();
        if(!Helper.isLetter(firstName)){
            firstNameErorr.setText("invalid first name");
            isOk=false;
        }
        String lastName=editLastName.getText().toString();
        if(!Helper.isLetter(lastName)){
            lastNameErorr.setText("invalid last name");
            isOk=false;
        }

        String nationalId=editId.getText().toString();
        if(!Helper.isNumber(nationalId) || nationalId.length()!=10){
            idErorr.setText("invalid id");
            isOk=false;
        }
        long phone=0;
        try {
            phone = Helper.convertToPhone(editPhone.getText().toString());
        }catch (Exception e){
            phoneErorr.setText("invalid phone");
            isOk=false;
        }
        String password=editPassword.getText().toString();
        if(!Helper.isStrongPass(password)){
            passwordErorr.setText("weak password");
            isOk=false;
        }

        if(isOk){
            doRegister(new Account(firstName,lastName,phone,nationalId,password),phone);
        }
    }

    public void doRegister(Account newAccount,long phoneNumber){
        DataBase.addUser(newAccount);
//        newAccount.setVerifyReq(DataBase.addSuppVerify(phoneNumber));
//        newAccount.addSuppReq(newAccount.getVerifyReq());
        newAccount.verify();
        Toast.makeText(getApplicationContext(),"register was successful",Toast.LENGTH_SHORT).show();
        finish();
    }

    public void clearErorrs(){
        firstNameErorr.setText("");
        lastNameErorr.setText("");
        phoneErorr.setText("");
        idErorr.setText("");
        passwordErorr.setText("");
    }
}