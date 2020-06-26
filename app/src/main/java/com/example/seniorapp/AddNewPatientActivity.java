package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class AddNewPatientActivity extends AppCompatActivity {
    //TODO adding new patient
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);
        setButtonsOnClick();
    }

    private void setButtonsOnClick(){
        getAddPatientButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAllErrors();
                checkIfAllDataAreCorect();
            }
        });
    }

    private void checkIfAllDataAreCorect() {
        if (checkIfLoginIsOk() & checkIfNameIsOk() &   checkIfSurnameIsOk() & checkPasswordsIsOk()&checkIfPeselIsOk()) {

            //TODO chceck if login matches to database and send Data To database
        }
    }

    private Boolean checkIfNameIsOk() {
        if (getPatientNameEditText().getText().toString().equals("")) {
            getPatientNameInputText().setError("Nie podano imienia");
            return false;
        } else {
            return true;
        }
    }

    private boolean checkIfSurnameIsOk() {
        if (getPatientSurnameEditText().getText().toString().equals("")) {
            getPatientSurnameInputText().setError("Nie podano nazwiska");
            return false;
        } else {
            return true;
        }
    }

    private Boolean checkIfLoginIsOk() {
        if (getPatientLoginEditText().getText().toString().equals("")) {
            getPatientLoginInputText().setError("Nie podano loginu");
            return false;
        } else {
            // ToDo check if exists in database
            return true;
        }
    }

    private Boolean checkIfPeselIsOk() {
        if (getPatientPeselEditText().getText().toString().equals("")) {
            getPatientPeselInputText().setError("Nie podano Peselu");
            return false;
        } else {
            String value = getPatientPeselEditText().getText().toString();
            Long pesel = Long.parseLong(value);
            return true;
        }
    }

    private Boolean checkPasswordsIsOk() {
        if (getPatientPasswordEditText().getText().toString().equals("")) {
            getPatientPasswordInputText().setError("Nie podano hasła");
            return false;
        } else {
            return checkPasswordMatches();
        }
    }

    private Boolean checkPasswordMatches() {
        if (getPatientPasswordEditText().getText().toString().equals(getPatientSecondPasswordEditText().getText().toString())) {
            return true;
        } else {
            getPatientSecondPasswordInputText().setError("Hasła się nie zgadzają");
            return false;
        }
    }

    private TextInputLayout getPatientNameInputText() {
        return findViewById(R.id.add_new_patient_name_input_layout);
    }

    private EditText getPatientNameEditText() {
        return findViewById(R.id.add_new_patient_name);
    }

    private TextInputLayout getPatientSurnameInputText() {
        return findViewById(R.id.add_new_patient_surname_input_layout);
    }

    private EditText getPatientSurnameEditText() {
        return findViewById(R.id.add_new_patient_surname);
    }

    private TextInputLayout getPatientLoginInputText() {
        return findViewById(R.id.add_new_patient_login_error);
    }

    private EditText getPatientLoginEditText() {
        return findViewById(R.id.add_new_patient_login);
    }

    private TextInputLayout getPatientPeselInputText() {
        return findViewById(R.id.add_new_patient_PESEL_input_layout);
    }

    private EditText getPatientPeselEditText() {
        return findViewById(R.id.add_new_patient_PESEL);
    }

    private TextInputLayout getPatientPasswordInputText() {
        return findViewById(R.id.add_new_patient_password_input_layout);
    }

    private EditText getPatientPasswordEditText() {
        return findViewById(R.id.add_new_patient_password);
    }

    private TextInputLayout getPatientSecondPasswordInputText() {
        return findViewById(R.id.add_new_patient_repeat_password_input_layout);
    }

    private EditText getPatientSecondPasswordEditText() {
        return findViewById(R.id.add_new_patient_repeat_password);
    }

    private EditText getPatientDescriptionEditText() {
        return findViewById(R.id.add_new_patient_description_of_patient_condition);
    }

    private Button getAddPatientButton(){
        return findViewById(R.id.add_new_patient_button_to_add);
    }

    private void resetAllErrors(){
        getPatientSecondPasswordInputText().setError(null);
        getPatientPasswordInputText().setError(null);
        getPatientLoginInputText().setError(null);
        getPatientPeselInputText().setError(null);
        getPatientNameInputText().setError(null);
        getPatientSurnameInputText().setError(null);
    }
}
