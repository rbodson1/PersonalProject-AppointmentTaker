package com.example.myappointmenttaker;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.PhoneNumberFormattingTextWatcher;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ScrollView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.appcompat.app.AppCompatActivity;


public class AddAppointmentActivity extends AppCompatActivity {

    private Appointmt currentAppointmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_appointment);

        initAppointButton();
        initHomeButton();
        initStyleButton();
        initToggleButton();
        setForEditing(false);
        initTextChangedEvents();
        initSaveButton();

        Bundle extras = getIntent().getExtras();
        if(extras != null) {
            initAppointment(extras.getInt("appointmtId"));
        }
        else {
            currentAppointmt = new Appointmt();
        }


    }

    private void initHomeButton() {
        ImageButton ibList = findViewById(R.id.imageButtonHome);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddAppointmentActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAppointButton() {
        ImageButton ibList = findViewById(R.id.imageButtonAppoint);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddAppointmentActivity.this, AppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initStyleButton() {
        ImageButton ibList = findViewById(R.id.imageButtonStyle);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(AddAppointmentActivity.this, StyleActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initToggleButton() {
        final ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
        editToggle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setForEditing(editToggle.isChecked());
            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editTime = findViewById(R.id.editTime);
        EditText editStyle = findViewById(R.id.editStyle);
        EditText editPrice = findViewById(R.id.editPrice);
        EditText editEstimateTime = findViewById(R.id.editEstimateTime);
        Button buttonSave = findViewById(R.id.buttonSave);

        editName.setEnabled(enabled);
        editAddress.setEnabled(enabled);
        editPhone.setEnabled(enabled);
        editTime.setEnabled(enabled);
        editStyle.setEnabled(enabled);
        editPrice.setEnabled(enabled);
        editEstimateTime.setEnabled(enabled);
        buttonSave.setEnabled(enabled);

        if (enabled) {
            editName.requestFocus();
        }
        else {
            ScrollView s = findViewById(R.id.scrollView);
            s.fullScroll(ScrollView.FOCUS_UP);
        }

    }



    private void initTextChangedEvents(){
        final EditText etContactName = findViewById(R.id.editName);
        etContactName.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentAppointmt.setAppointeeName(etContactName.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etStreetAddress = findViewById(R.id.editAddress);
        etStreetAddress.addTextChangedListener(new TextWatcher() {
            public void afterTextChanged(Editable s) {
                currentAppointmt.setStreetAddress(etStreetAddress.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etPhone = findViewById(R.id.editHome);
        etPhone.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentAppointmt.setPhoneNumber(etPhone.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etTime = findViewById(R.id.editTime);
        etTime.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentAppointmt.setTime(etTime.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etStyle= findViewById(R.id.editStyle);
        etStyle.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentAppointmt.setStyle(etStyle.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etPrice = findViewById(R.id.editPrice);
        etPrice.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentAppointmt.setPrice(etPrice.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });

        final EditText etEstimateTime = findViewById(R.id.editEstimateTime);
        etEstimateTime.addTextChangedListener(new TextWatcher() {

            public void afterTextChanged(Editable s) {
                currentAppointmt.setEstimateTime(etEstimateTime.getText().toString());
            }
            public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
                //  Auto-generated method stub
            }
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //  Auto-generated method stub
            }
        });



        etPhone.addTextChangedListener(new PhoneNumberFormattingTextWatcher());
    }

    private void initSaveButton() {
        Button saveButton = findViewById(R.id.buttonSave);
        saveButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                hideKeyboard();
                AppointmtDataSource ds = new AppointmtDataSource(AddAppointmentActivity.this);
                try {
                    ds.open();

                    if (currentAppointmt.getAppointmtID() == -1) {
                        wasSuccessful = ds.insertAppointment(currentAppointmt);
                        if (wasSuccessful) {
                            int newId = ds.getLastAppointmentId();
                            currentAppointmt.setAppointmtID(newId);
                        }

                    }
                    else {
                        wasSuccessful = ds.updateAppointment(currentAppointmt);
                    }
                    ds.close();
                }
                catch (Exception e) {
                    wasSuccessful = false;
                }

                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButtonEdit);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }

    private void hideKeyboard() {
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        EditText editName = findViewById(R.id.editName);
        imm.hideSoftInputFromWindow(editName.getWindowToken(), 0);
        EditText editAddress = findViewById(R.id.editAddress);
        imm.hideSoftInputFromWindow(editAddress.getWindowToken(), 0);
        EditText et = findViewById(R.id.editTime);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editStyle);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editPrice);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editHome);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);
        et = findViewById(R.id.editEstimateTime);
        imm.hideSoftInputFromWindow(et.getWindowToken(), 0);

    }

    private void initAppointment(int id) {

        AppointmtDataSource ds = new AppointmtDataSource(AddAppointmentActivity.this);
        try {
            ds.open();
            currentAppointmt = ds.getSpecificAppointment(id);
            ds.close();
        }
        catch (Exception e) {
            Toast.makeText(this, "Load Contact Failed", Toast.LENGTH_LONG).show();
        }

        EditText editName = findViewById(R.id.editName);
        EditText editAddress = findViewById(R.id.editAddress);
        EditText editTime = findViewById(R.id.editTime);
        EditText editStyle = findViewById(R.id.editStyle);
        EditText editPrice = findViewById(R.id.editPrice);
        EditText editPhone = findViewById(R.id.editHome);
        EditText editEstimateTime = findViewById(R.id.editEstimateTime);

        editName.setText(currentAppointmt.getAppointeeName());
        editAddress.setText(currentAppointmt.getStreetAddress());
        editTime.setText(currentAppointmt.getTime());
        editStyle.setText(currentAppointmt.getStyle());
        editPrice.setText(currentAppointmt.getPrice());
        editPhone.setText(currentAppointmt.getPhoneNumber());
        editEstimateTime.setText(currentAppointmt.getEstimateTime());

    }



}
