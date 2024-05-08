package com.example.task;

import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {

    private TextView lat, lng, contry, cnt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        String latitude = getIntent().getStringExtra("lat");
        String longitude = getIntent().getStringExtra("lng");
        String contact = getIntent().getStringExtra("contact");
        String country = getIntent().getStringExtra("country");

        lat = findViewById(R.id.first);
        lng = findViewById(R.id.second);
        contry = findViewById(R.id.fourth);
        cnt = findViewById(R.id.third);

        lat.setText(latitude);
        lng.setText(longitude);
        cnt.setText(contact);
        contry.setText(country);



    }
}