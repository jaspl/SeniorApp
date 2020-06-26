package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.seniorapp.Games.ColorGame.ColorGameActivity;
import com.example.seniorapp.Games.MemoryGame.MemoryGameActivity;
import com.example.seniorapp.Games.SymbolsGame.SymbolsGameActivity;
import com.google.android.material.textfield.TextInputLayout;

public class PatientLogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_log_in);
        setButtonsOnClics();
    }

    private void setButtonsOnClics() {
        Button patientLogInButton = findViewById(R.id.patient_log_in_button);
        patientLogInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO Patient Log in action
                Intent intent = new Intent(PatientLogInActivity.this, GameSelectorActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getPassword() {
        EditText password = findViewById(R.id.patient_log_in_password);
        return password.getText().toString();
    }

    private String getLogin() {
        EditText login = findViewById(R.id.patient_log_in_login);
        return login.getText().toString();
    }
    private void setError(){
        TextInputLayout textInputLayout = findViewById(R.id.patient_log_in_password_error);
        textInputLayout.setError("Login lub hasło jest nieprawidłowe!");
    }
}
