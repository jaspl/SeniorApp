package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Models.CaregiversObject;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CareTakerLogInActivity extends AppCompatActivity {
    //TODO connection with database
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_care_taker_log_in);
        setButtonsClicks();
    }

    @Override
    public void onBackPressed() {
    }

    private void setButtonsClicks() {
        Button register = findViewById(R.id.care_taker_register_button);
        Button logIn = findViewById(R.id.care_taker_log_in_button);
        FloatingActionButton exitButton = findViewById(R.id.end_game_floatig_buton);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(CareTakerLogInActivity.this, CareTakerRegistrationActivity.class);
                startActivity(intent);
            }
        });
        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logIn();
            }
        });
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CareTakerLogInActivity.this, StartInActivity.class);
                startActivity(intent);
            }
        });
    }

    private String getLogin() {
        EditText login = findViewById(R.id.care_taker_log_in_login);
        return login.getText().toString();
    }

    private String getPassword() {
        EditText password = findViewById(R.id.care_taker_log_in_password);
        return password.getText().toString();
    }

    private void setError(String message) {
        TextInputLayout textInputLayout = findViewById(R.id.care_taker_log_in_password_error);
        textInputLayout.setError(message);
    }

    private void logIn() {
        Api api = new ApiClass().getApi();
        Call<CaregiversObject> call = api.caregiverInfoGetter(getLogin(), getPassword());
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");

        progressDialog.show();
        call.enqueue(new Callback<CaregiversObject>() {
            @Override
            public void onResponse(Call<CaregiversObject> call, Response<CaregiversObject> response) {

                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    setError("Login lub hasło jest nieprawidłowe!");
                    progressDialog.dismiss();
                } else {
                    Intent intent = new Intent(CareTakerLogInActivity.this, PatientListActivity.class);
                    startActivity(intent);
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<CaregiversObject> call, Throwable t) {
                //TODO set error dialog
                Log.d("caretaker logIn", "onFailure: " + t.getMessage());
                noSerwerConnectionError();
                progressDialog.dismiss();
            }
        });
    }

    private void noSerwerConnectionError() {
        new NoSerwerConnectionErrorDialog(this).startErrorDialog().show();
    }
}
