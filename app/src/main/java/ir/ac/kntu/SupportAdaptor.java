package ir.ac.kntu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class SupportAdaptor extends ArrayAdapter<Long> {

    public SupportAdaptor(@NonNull Context context, ArrayList<Long> arrayList) {
        super(context, R.layout.support_item, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.support_item,parent,false);
        }

        SupportRequest supportRequest=DataBase.findSuppReq(getItem(position));
        TextView subject=convertView.findViewById(R.id.supportItemSubject);
        TextView navId=convertView.findViewById(R.id.supportItemNavId);
        subject.setText(supportRequest.getSubject().toString());
        navId.setText("id : "+supportRequest.getNavId());
        return convertView;
    }
}
