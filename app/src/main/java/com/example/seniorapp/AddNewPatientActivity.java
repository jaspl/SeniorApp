package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Adapters.PatientsAdapter;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Patterns.Patient;
import com.example.seniorapp.Utils.LevelGame;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddNewPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_patient);
        setButtonsOnClick();
    }

    @Override
    public void onBackPressed() {
    }

    private void setButtonsOnClick() {
        getAddPatientButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetAllErrors();
                checkIfAllDataAreCorect();
            }
        });
        getExitButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddNewPatientActivity.this, PatientListActivity.class);
                startActivity(intent);
            }
        });
    }

    private void checkIfAllDataAreCorect() {
        if (checkIfLoginIsOk() & checkIfNameIsOk() & checkIfSurnameIsOk() & checkPasswordsIsOk() & checkIfPeselIsOk()) {
            getPatienstList();
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

    private Button getAddPatientButton() {
        return findViewById(R.id.add_new_patient_button_to_add);
    }

    private FloatingActionButton getExitButton() {
        return findViewById(R.id.end_game_floatig_buton);
    }

    private void resetAllErrors() {
        getPatientSecondPasswordInputText().setError(null);
        getPatientPasswordInputText().setError(null);
        getPatientLoginInputText().setError(null);
        getPatientPeselInputText().setError(null);
        getPatientNameInputText().setError(null);
        getPatientSurnameInputText().setError(null);
    }

    private PatientsObject getPatientObject() {
        PatientsObject patientsObject = new PatientsObject(getPatientNameEditText().getText().toString(),
                getPatientSurnameEditText().getText().toString(),
                getPatientLoginEditText().getText().toString(),
                getPatientPasswordEditText().getText().toString(),
                getPatientPeselEditText().getText().toString(),
                getPatientDescriptionEditText().getText().toString(),
                LevelGame.VERYLOW, false);
        return patientsObject;
    }

    private void sendDataToDatabase() {
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        Call<PatientsObject> call = api.addPatient(getPatientObject());
        call.enqueue(new Callback<PatientsObject>() {
            @Override
            public void onResponse(Call<PatientsObject> call, Response<PatientsObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    noSerwerConnectionError();
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    startActivity(new Intent(AddNewPatientActivity.this, PatientListActivity.class));
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

    private void getPatienstList() {
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        Call<List<PatientsObject>> call = api.getPatientList();
        call.enqueue(new Callback<List<PatientsObject>>() {
            @Override
            public void onResponse(Call<List<PatientsObject>> call, Response<List<PatientsObject>> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    List<PatientsObject> patientsObjectList = response.body();
                    if (!checkIfThereIsNomeoneWithSameLogin(patientsObjectList)) {
                        sendDataToDatabase();
                    }
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<PatientsObject>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

    private Boolean checkIfThereIsNomeoneWithSameLogin(List<PatientsObject> patientsObjectList) {
        Boolean is = false;
        for (PatientsObject patient : patientsObjectList) {
            if (patient.getLogin().equals(getPatientLoginEditText().getText().toString())) {
                getPatientLoginInputText().setError("Użytkownik o takim loginie juz istnieje!");
                is = true;
                break;
            }
        }
        return is;
    }
}
