package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.seniorapp.Games.ColorGame.ColorGameActivity;

public class PatientLogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_log_in);
        setButtonsOnClics();
    }
    private void setButtonsOnClics(){
        Button patientLogInButton = findViewById(R.id.patient_log_in_button);
        patientLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Patient Log in action
                Intent intent = new Intent(PatientLogInActivity.this, ColorGameActivity.class);
                startActivity(intent);
            }
        });
    }
}
