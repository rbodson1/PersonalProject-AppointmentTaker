package com.example.myappointmenttaker;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class StyleActivity extends AppCompatActivity {

    private static final int GALLERYREQUEST = 123;

    ImageView imageView;
    Button buttonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_style);

        imageView = findViewById(R.id.imageAdd1);
        buttonAdd = findViewById(R.id.buttonAddStyle);
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);

                startActivityForResult(Intent.createChooser(intent, "Pick an image"), GALLERYREQUEST);
            }
        });

        initAddButton();
        initAppointButton();
        initHomeButton();
    }

    private void initHomeButton() {
        ImageButton ibList = findViewById(R.id.imageButtonHome);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StyleActivity.this, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAddButton() {
        ImageButton ibList = findViewById(R.id.imageButtonAdd);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StyleActivity.this, AddAppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initAppointButton() {
        ImageButton ibList = findViewById(R.id.imageButtonAppoint);
        ibList.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(StyleActivity.this, AppointmentActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == GALLERYREQUEST && resultCode == RESULT_OK && data != null) {

            Uri imageData = data.getData();

            imageView.setImageURI(imageData);


        }


    }
}