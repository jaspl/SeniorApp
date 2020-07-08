package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.renderscript.ScriptGroup;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Games.ColorGame.ColorGameActivity;
import com.example.seniorapp.Games.MemoryGame.MemoryGameActivity;
import com.example.seniorapp.Games.SymbolsGame.SymbolsGameActivity;
import com.example.seniorapp.Models.CaregiversObject;
import com.example.seniorapp.Models.PatientsObject;
import com.google.android.material.textfield.TextInputLayout;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
              logIn();
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
    private void logIn(){
        Api api = new ApiClass().getApi();
        Call<PatientsObject> call = api.getPatient(getLogin(),getPassword());
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this,"wczytywanie");
        progressDialog.show();
        call.enqueue(new Callback<PatientsObject>() {
            @Override
            public void onResponse(Call<PatientsObject> call, Response<PatientsObject> response) {

                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    setError();
                    progressDialog.dismiss();
                }else {
                    Intent intent = new Intent(PatientLogInActivity.this, GameSelectorActivity.class);
                    startActivity(intent);
                    PatientsObject patientsObject= response.body();
                    new SharedPrefs(getApplicationContext()).saveId(patientsObject.getId());
                    new SharedPrefs(getApplicationContext()).saveLvl(patientsObject.getLevel());
                    Log.d("login", new SharedPrefs(getApplicationContext()).getId()+"");
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<PatientsObject> call, Throwable t) {
                //TODO set error dialog
                Log.d("caretaker logIn", "onFailure: "+t.getMessage());
                progressDialog.dismiss();
            }
        });
    }
}
