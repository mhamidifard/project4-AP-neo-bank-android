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

public class LoanAdaptor extends ArrayAdapter<Loan> {

    public LoanAdaptor(@NonNull Context context, ArrayList<Loan> loans) {
        super(context, R.layout.loan_item,loans);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.loan_item,parent,false);
        }
        Loan loan=getItem(position);
        TextView amount=convertView.findViewById(R.id.loanItemAmount);
        TextView monthes=convertView.findViewById(R.id.loanItemMonthes);
        TextView status=convertView.findViewById(R.id.loanItemStatus);
        amount.setText("amount : "+loan.getAmount());
        monthes.setText("monthes : "+loan.getMonthes());
        status.setText(loan.getStatus().toString());
        return convertView;
    }
}
