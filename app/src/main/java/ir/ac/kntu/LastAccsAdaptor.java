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

public class LastAccsAdaptor extends ArrayAdapter<LastTransfer> {

    public LastAccsAdaptor(@NonNull Context context, ArrayList<LastTransfer> arrayList) {
        super(context, R.layout.last_accs_item, arrayList);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Long toAccount=getItem(position).getAccountNum();
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.last_accs_item,parent,false);
        }
        TextView accNumber=convertView.findViewById(R.id.lastAccNum);
        TextView name=convertView.findViewById(R.id.lastAccName);
        accNumber.setText(Long.toString(toAccount));
        name.setText(DataBase.findByAccNum(toAccount).getFullName());

        return convertView;
    }
}
