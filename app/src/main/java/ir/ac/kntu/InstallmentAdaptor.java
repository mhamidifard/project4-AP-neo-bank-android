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

public class InstallmentAdaptor extends ArrayAdapter<Installment> {
    public InstallmentAdaptor(@NonNull Context context, ArrayList<Installment> installments) {
        super(context,R.layout.installment_item, installments);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Installment installment=getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.installment_item,parent,false);
        }


        TextView amount=convertView.findViewById(R.id.installmentAmount);
        TextView status=convertView.findViewById(R.id.installmentStatus);
        TextView date=convertView.findViewById(R.id.installmentDate);
        ImageView image=convertView.findViewById(R.id.installmentImage);
        amount.setText((position+1)+" :     "+installment.getAmount());
        date.setText(Helper.DateToStringJustDay(installment.getDate()));
        status.setText(installment.getStatus().toString());
//        if(installment.isPeyed()){
//            image.setImageResource(R.drawable.baseline_check_24);
//            status.setText("peyed");
//        } else if (installment.getDate().isBefore(LoanDetailesActivity.getNowDate())) {
//            image.setImageResource(R.drawable.baseline_clear_24);
//            status.setText("delayed");
//        }
        return convertView;
    }
}
