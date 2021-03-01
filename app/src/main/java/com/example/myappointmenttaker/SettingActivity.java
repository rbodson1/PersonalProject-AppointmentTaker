package com.example.myappointmenttaker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        initAddButton();
        initAppointButton();
        initHomeButton();

        initSettings();
        initSortByClick();
        initSortOrderClick();
    }

    private void initHomeButton() {
        ImageButton ibList = findViewById(R.id.imageButtonHome);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAddButton() {
        ImageButton ibList = findViewById(R.id.imageButtonAdd);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, AddAppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAppointButton() {
        ImageButton ibList = findViewById(R.id.imageButtonAppoint);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(SettingActivity.this, AppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }


    private void initSettings() {
        String sortBy = getSharedPreferences("MyAppointmentTakerPreferences",
                Context.MODE_PRIVATE).getString("sortfield","price");
        String sortOrder = getSharedPreferences("MyContactListPreferences",
                Context.MODE_PRIVATE).getString("sortorder","ASC");

        RadioButton rbName = findViewById(R.id.radioDate);
        RadioButton rbAddress = findViewById(R.id.radiostreetaddress);
        RadioButton rbTime = findViewById(R.id.radioTime);
        if (sortBy.equalsIgnoreCase("price")) {
            rbName.setChecked(true);
        }
        else if (sortBy.equalsIgnoreCase("streetaddress")) {
            rbAddress.setChecked(true);
        }
        else {
            rbTime.setChecked(true);
        }

        RadioButton rbAscending = findViewById(R.id.radioAscending);
        RadioButton rbDescending = findViewById(R.id.radioDescending);
        if (sortOrder.equalsIgnoreCase("ASC")) {
            rbAscending.setChecked(true);
        }
        else {
            rbDescending.setChecked(true);
        }
    }

    private void initSortByClick() {
        RadioGroup rgSortBy = findViewById(R.id.radioGroupSortBy);
        rgSortBy.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbDate = findViewById(R.id.radioDate);
                RadioButton rbAddress = findViewById(R.id.radiostreetaddress);
                if (rbDate.isChecked()) {
                    getSharedPreferences("MyAppointmentTakerPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "price").apply();
                }
                else if (rbAddress.isChecked()) {
                    getSharedPreferences("MyAppointmentTakerPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "streetaddress").apply();
                }
                else {
                    getSharedPreferences("MyAppointmentTakerPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortfield", "time").apply();
                }
            }
        });
    }

    private void initSortOrderClick() {
        RadioGroup rgSortOrder = findViewById(R.id.radioGroupSortOrder);
        rgSortOrder.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup arg0, int arg1) {
                RadioButton rbAscending = findViewById(R.id.radioAscending);
                if (rbAscending.isChecked()) {
                    getSharedPreferences("MyAppointmentTakerPreferences",
                            Context.MODE_PRIVATE).edit().putString("sortorder", "ASC").apply();
                }
                else {
                    getSharedPreferences("MyAppointmentTakerPreferences", Context.MODE_PRIVATE).edit().putString("sortorder", "DESC").apply();
                }
            }
        });
    }
}
