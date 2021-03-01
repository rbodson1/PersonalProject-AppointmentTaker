package com.example.myappointmenttaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class AppointmentActivity extends AppCompatActivity {

    RecyclerView appointList;
    AppointmtAdapter appointAdapter;

    ArrayList<Appointmt> appoints;
    private View.OnClickListener onItemClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            int appointmentId = appoints.get(position).getAppointmtID();
            Intent intent = new Intent(AppointmentActivity.this, AddAppointmentActivity.class);
            intent.putExtra("appointmtId", appointmentId);
            startActivity(intent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointment);

        initAddButton();
        initHomeButton();
        initStyleButton();

        initDoneSwitch();
        initSettingButton();
    }


    private void initHomeButton() {
        ImageButton ibList = findViewById(R.id.imageButtonHome);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAddButton() {
        ImageButton ibList = findViewById(R.id.imageButtonAdd);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentActivity.this, AddAppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initStyleButton() {
        ImageButton ibList = findViewById(R.id.imageButtonStyle);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AppointmentActivity.this, StyleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();

        String sortBy = getSharedPreferences("MyAppointmentTakerPreferences", Context.MODE_PRIVATE).getString("sortfield", "appointeename");
        String sortOrder = getSharedPreferences("MyAppointmentTakerPreferences", Context.MODE_PRIVATE).getString("sortorder", "ASC");

        AppointmtDataSource ds = new AppointmtDataSource(this);
        try {
            ds.open();
            appoints = ds.getAppointments(sortBy, sortOrder);
            ds.close();
            if (appoints.size() > 0) {
                appointList = findViewById(R.id.rvContacts);
                RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
                appointList.setLayoutManager(layoutManager);
                appointAdapter = new AppointmtAdapter(appoints, this);
                appointAdapter.setOnItemClickListener(onItemClickListener);
                appointList.setAdapter(appointAdapter);
            }
            else {
                Intent intent = new Intent(AppointmentActivity.this, AddAppointmentActivity.class);
                startActivity(intent);
            }
        }
        catch (Exception e) {
            Toast.makeText(this, "Error retrieving appointments", Toast.LENGTH_LONG).show();
        }

    }
    private void initSettingButton() {
        Button newContact = findViewById(R.id.buttonSetting);
        newContact.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(AppointmentActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initDoneSwitch() {
        Switch s = findViewById(R.id.switchDelete);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                boolean status = compoundButton.isChecked();
                appointAdapter.setDelete(status);
                appointAdapter.notifyDataSetChanged();
            }
        });
    }


}

