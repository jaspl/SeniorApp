package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

public class CareTakerRegistrationActivity extends AppCompatActivity {
    //TODO
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_taker_registration);
        setButtonsOnClicks();
    }

    private void setButtonsOnClicks() {
        getRegisterButon().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAllErrors();
                checkIfAllDataCorrect();
            }
        });
    }

    private void checkIfAllDataCorrect(){
        if (checkIfLoginCorrect()&checkIfNameCorrect()&checkIfSurnameCorrect()&checkPasswordsIsOk()){
            checkIfLoginIsCOrrectWithDataBase();
        }
    }

    private Button getRegisterButon(){
        return findViewById(R.id.care_taker_button_to_register);
    }
    private EditText getNameEditText() {
        return findViewById(R.id.care_taker_register_name);
    }

    private TextInputLayout getNameInputText() {
        return findViewById(R.id.care_taker_register_name_error);
    }

    private EditText getSurnameEditText() {
        return findViewById(R.id.care_taker_register_surname);
    }

    private TextInputLayout getSurnameInputText() {
        return findViewById(R.id.care_taker_register_surname_error);
    }

    private EditText getLoginEditText() {
        return findViewById(R.id.care_taker_register_login);
    }

    private TextInputLayout getLoginInputText() {
        return findViewById(R.id.care_taker_register_login_error);
    }

    private EditText getPasswordEditText() {
        return findViewById(R.id.care_taker_register_password);
    }

    private TextInputLayout getPasswordInputText() {
        return findViewById(R.id.care_taker_register_password_error);
    }

    private EditText getSecondPasswordEditText() {
        return findViewById(R.id.care_taker_register_repeat_password);
    }

    private TextInputLayout getSecondPasswordInputText() {
        return findViewById(R.id.care_taker_register_repeat_password_error);
    }

    private boolean checkIfNameCorrect() {
        if (getNameEditText().getText().toString().equals("")) {
            getNameInputText().setError("Nie podano imienia!");
            return false;
        }else{
            return true;
        }
    }
    private boolean checkIfSurnameCorrect() {
        if (getSurnameEditText().getText().toString().equals("")) {
            getSurnameInputText().setError("Nie podano nazwiska!");
            return false;
        }else{
            return true;
        }
    }
    private boolean checkIfLoginCorrect() {
        if (getLoginEditText().getText().toString().equals("")) {
            getLoginInputText().setError("Nie podano loginu!");
            return false;
        }else{
            return true;
        }
    }

    private Boolean checkPasswordsIsOk() {
        if (getPasswordEditText().getText().toString().equals("")) {
            getPasswordInputText().setError("Nie podano hasła");
            return false;
        } else {
            return checkPasswordMatches();
        }
    }

    private Boolean checkPasswordMatches() {
        if (getPasswordEditText().getText().toString().equals(getSecondPasswordEditText().getText().toString())) {
            return true;
        } else {
            getSecondPasswordInputText().setError("Hasła się nie zgadzają");
            return false;
        }
    }
    private void checkIfLoginIsCOrrectWithDataBase(){
        //TODO
    }

    private void resetAllErrors(){
        getSecondPasswordInputText().setError(null);
        getPasswordInputText().setError(null);
        getLoginInputText().setError(null);
        getNameInputText().setError(null);
        getSurnameInputText().setError(null);
    }
}
