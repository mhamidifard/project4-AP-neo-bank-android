package ir.ac.kntu;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.HashMap;
import java.util.Map;

public class AddSupportActivity extends AppCompatActivity {
    private RadioGroup radioGroup;
    private RadioButton report;
    //int ReportId,contactId,transferId,settingId,boxId;
    private Map<Integer,Subject> idMap=new HashMap<>();
    private EditText messageEdit;
    private TextView erorr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_add_support);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        radioGroup=findViewById(R.id.radioSubject);
        messageEdit=findViewById(R.id.addSupportMessage);
        erorr=findViewById(R.id.addSupportErorr);
//        ReportId=findViewById(R.id.radioReport).getId();
//        contactId=findViewById(R.id.radioTransfer).getId();
//        transferId=findViewById(R.id.radioTransfer).getId();
//        boxId=findViewById(R.id.radioBox).getId();
        idMap.put(findViewById(R.id.radioReport).getId(),Subject.REPORT);
        idMap.put(findViewById(R.id.radioContact).getId(),Subject.CONTACTS);
        idMap.put(findViewById(R.id.radioBox).getId(),Subject.BOX);
        idMap.put(findViewById(R.id.radioTransfer).getId(),Subject.TRANSFER);
    }

    public void onSend(View view){
        Subject subject=idMap.get(radioGroup.getCheckedRadioButtonId());
        if(subject==null){
            erorr.setText("invalid subject");
            return;
        }
        String message=messageEdit.getText().toString();
        if(message.length()==0){
            erorr.setText("invalid message");
            return;
        }
        SupportRequest request=DataBase.addSupportReq("",DashboardActivity.getAccount().getPhoneNumber(),subject);
        DashboardActivity.getAccount().addSuppReq(request.getNavId());
        request.addMessage(message,Sender.USER);
        //Toast.makeText(getApplicationContext(),subject.toString(),Toast.LENGTH_SHORT).show();
        finish();

    }


}