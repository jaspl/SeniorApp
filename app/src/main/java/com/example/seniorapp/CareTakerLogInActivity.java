package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CareTakerLogInActivity extends AppCompatActivity {
 //TODO connection with database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_taker_log_in);
        setButtonsClicks();
    }
    private void setButtonsClicks(){
        Button register = findViewById(R.id.care_taker_register_button);
        Button logIn = findViewById(R.id.care_taker_log_in_button);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareTakerLogInActivity.this,CareTakerRegistrationActivity.class);
                startActivity(intent);
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareTakerLogInActivity.this,PatientListActivity.class);
                startActivity(intent);
            }
        });
    }
}
