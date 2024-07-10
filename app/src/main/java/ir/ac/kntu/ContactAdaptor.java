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

public class ContactAdaptor extends ArrayAdapter<Contact> {

    public ContactAdaptor(Context context, ArrayList<Contact> contacts) {
        super(context,R.layout.contact_item,contacts);

    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Contact contact =getItem(position);
        if (convertView==null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.contact_item,parent,false);
        }
        TextView firstName=convertView.findViewById(R.id.contactItemFirstame);
        TextView lastName=convertView.findViewById(R.id.contactItemLastname);
        firstName.setText(contact.getFirstName());
        lastName.setText(contact.getLastName());

        return convertView;
    }
}
