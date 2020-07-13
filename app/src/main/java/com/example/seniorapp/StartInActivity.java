package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

public class StartInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setOnClicks();
    }

    @Override
    public void onBackPressed() {
    }

    private void setOnClicks() {
        LinearLayout pupil = findViewById(R.id.log_in_as_pupil);
        pupil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartInActivity.this, PatientLogInActivity.class);
                startActivity(intent);
            }
        });

        LinearLayout careTaker = findViewById(R.id.log_in_as_care_taker);
        careTaker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StartInActivity.this, CareTakerLogInActivity.class);
                startActivity(intent);
            }
        });
    }
}
