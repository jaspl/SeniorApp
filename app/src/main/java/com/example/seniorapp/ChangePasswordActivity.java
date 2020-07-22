package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Models.PatientsObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password);
        setOnButtonsClick();
        setTitle(getIntent().getStringExtra("changePasswordTitle"));
    }

    @Override
    public void onBackPressed() {
    }

    private void setOnButtonsClick() {
        FloatingActionButton exitButton = findViewById(R.id.end_game_floatig_buton);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ChangePasswordActivity.this, SelectedPatientActivity.class));
            }
        });
        Button savePatientPassword = findViewById(R.id.change_patient_password_button);
        savePatientPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAllErrors();
                if (checkPasswordsIsOk()) {
                    changePassword();
                }
            }
        });
    }

    private void setTitle(String title) {
        TextView titleText = findViewById(R.id.change_password_activity_title);
        titleText.setText(title);
    }

    private String getLogin() {
        return new SharedPrefs(this).getLogin();
    }

    private Boolean checkPasswordsIsOk() {
        if (getPassword().getText().toString().equals("")) {
            getPasswordInputLayout().setError("Nie podano hasła");
            return false;
        } else {
            return checkPasswordMatches();
        }
    }


    private Boolean checkPasswordMatches() {
        if (getPassword().getText().toString().equals(getSecondPassword().getText().toString())) {
            return true;
        } else {
            getSecondPasswordInputLayout().setError("Hasła się nie zgadzają");
            return false;
        }
    }

    private EditText getPassword() {
        return findViewById(R.id.change_password);
    }

    private EditText getSecondPassword() {
        return findViewById(R.id.change_second_password);
    }

    private TextInputLayout getPasswordInputLayout() {
        return findViewById(R.id.change_password_input_layout);
    }

    private TextInputLayout getSecondPasswordInputLayout() {
        return findViewById(R.id.change_second_password_input_layout);
    }

    private void resetAllErrors() {
        getPasswordInputLayout().setError(null);
        getSecondPasswordInputLayout().setError(null);
    }

    private void changePassword() {
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        Call<PatientsObject> call = api.changePatientPassword(new SharedPrefs(this).getLogin(), getPassword().getText().toString());
        call.enqueue(new Callback<PatientsObject>() {
            @Override
            public void onResponse(Call<PatientsObject> call, Response<PatientsObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    //TODO error handler when sth i wrong
                    progressDialog.dismiss();
                    noSerwerConnectionError();
                } else {
                    progressDialog.dismiss();
                    startActivity(new Intent(ChangePasswordActivity.this, PatientListActivity.class));
                }
            }

            @Override
            public void onFailure(Call<PatientsObject> call, Throwable t) {
                progressDialog.dismiss();
                noSerwerConnectionError();
            }
        });
    }

    private void noSerwerConnectionError() {
        new NoSerwerConnectionErrorDialog(this).startErrorDialog().show();
    }
}