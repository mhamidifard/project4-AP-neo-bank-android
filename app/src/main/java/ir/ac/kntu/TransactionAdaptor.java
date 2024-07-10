package ir.ac.kntu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class TransactionAdaptor extends ArrayAdapter<Long> {
    public TransactionAdaptor(Context context, ArrayList<Long> transactions) {
        super(context,R.layout.item_transaction,transactions);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Transaction transaction =DataBase.findTransaction(getItem(position));

        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_transaction,parent,false);
        }
        ImageView image=convertView.findViewById(R.id.imageView6);
        TextView type=convertView.findViewById(R.id.transactionTypeItem);
        TextView amount=convertView.findViewById(R.id.transactoinAmountItem);
        TextView navIdView=convertView.findViewById(R.id.navIdItem);
        //image.setImageResource(R.drawable.baseline_attach_money_24);
        type.setText(transaction.getType()+"");
        amount.setText(transaction.getValue()+"");
        navIdView.setText(transaction.getNavId()+"");

        return convertView;
    }
}
