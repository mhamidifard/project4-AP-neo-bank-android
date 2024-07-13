package ir.ac.kntu;

import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SupportDetailesActivity extends AppCompatActivity {
    private static SupportRequest request;
    private TextView subject,navId,message0,message1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_support_detailes);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        subject=findViewById(R.id.supportDetailesSubject);
        navId=findViewById(R.id.supportDetailesId);
        message0=findViewById(R.id.supportMessage0);
        message1=findViewById(R.id.supportMessage1);
        subject.setText(request.getSubject().toString());
        navId.setText("id : "+request.getNavId());
        message0.setText(request.getMessages().get(0).toString());
        if(request.getMessages().size()>1){
            message1.setAlpha(1);
            message1.setText(request.getLastMessage().toString());
        }
    }

    public static SupportRequest getRequest() {
        return request;
    }

    public static void setRequest(SupportRequest request) {
        SupportDetailesActivity.request = request;
    }
}