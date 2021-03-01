package com.example.myappointmenttaker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmtAdapter extends RecyclerView.Adapter{
    private ArrayList<Appointmt> appointmtData;
    private View.OnClickListener mOnItemClickListener;
    private boolean isDeleting;
    private Context parentContext;

    public class AppointmentViewHolder extends RecyclerView.ViewHolder {

        public TextView textViewContact;
        public TextView textAddress;
        public TextView textPrice;
        public TextView textTime;

        public Button deleteButton;
        public AppointmentViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewContact = itemView.findViewById(R.id.textContactName);
            textAddress = itemView.findViewById(R.id.textAddress);
            textPrice = itemView.findViewById(R.id.textPrice);
            textTime = itemView.findViewById(R.id.textTime);




            deleteButton = itemView.findViewById(R.id.buttonDeleteContact);
            itemView.setTag(this);
            itemView.setOnClickListener(mOnItemClickListener);
        }

        public TextView getContactTextView() {
            return textViewContact;
        }
        public TextView getAddressTextView() {
            return textAddress;
        }
        public TextView getPriceTextView() {
            return textPrice;
        }
        public TextView getTimeTextView() {
            return textTime;
        }


        public Button getDeleteButton() {
            return deleteButton;
        }
    }

    public AppointmtAdapter(ArrayList<Appointmt> arrayList, Context context) {
        appointmtData = arrayList;
        parentContext = context;
    }

    public void setOnItemClickListener(View.OnClickListener itemClickListener) {
        mOnItemClickListener = itemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item, parent, false);
        return new AppointmentViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        AppointmentViewHolder cvh = (AppointmentViewHolder) holder;
        cvh.getContactTextView().setText(appointmtData.get(position).getAppointeeName());
        cvh.getAddressTextView().setText(appointmtData.get(position).getStreetAddress());
        cvh.getPriceTextView().setText(appointmtData.get(position).getPrice());
        cvh.getTimeTextView().setText(appointmtData.get(position).getTime());



        if (isDeleting) {
            cvh.getDeleteButton().setVisibility(View.VISIBLE);
            cvh.getDeleteButton().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    deleteItem(position);
                }
            });
        }
        else {
            cvh.getDeleteButton().setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return appointmtData.size();
    }

    private void deleteItem(int position) {
        Appointmt contact = appointmtData.get(position);
        AppointmtDataSource ds = new AppointmtDataSource(parentContext);
        try {
            ds.open();
            boolean didDelete = ds.deleteAppointment(contact.getAppointmtID());
            ds.close();
            if (didDelete) {
                appointmtData.remove(position);
                notifyDataSetChanged();
            }
            else {
                Toast.makeText(parentContext, "Delete Failed!", Toast.LENGTH_LONG).show();
            }

        }
        catch (Exception e) {

        }
    }

    public void setDelete(boolean b) {
        isDeleting = b;
    }
}
