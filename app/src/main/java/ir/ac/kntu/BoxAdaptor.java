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

public class BoxAdaptor extends ArrayAdapter<Box> {
    public BoxAdaptor(Context context, ArrayList<Box> boxes){
        super(context,R.layout.box_item,boxes);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Box box=getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.box_item,parent,false);
        }
        TextView name=convertView.findViewById(R.id.boxNameItem);
        TextView type=convertView.findViewById(R.id.boxTypeItem);
        TextView balance=convertView.findViewById(R.id.boxBalanceItem);
        name.setText(box.getName());
        type.setText(box.getType().toString());
        balance.setText(box.getBalance().toString());
        return convertView;
    }
}
