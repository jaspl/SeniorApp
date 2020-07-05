package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Patterns.Patient;
import com.example.seniorapp.Utils.LevelGame;
import com.google.android.material.textfield.TextInputLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientInfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_info);
        Log.d("id", "" + new SharedPrefs(this).getId());
        getPatientInfo();
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
            return true;
        }
    }

    private TextInputLayout getPatientNameInputText() {
        return findViewById(R.id.patirnt_info_name_error);
    }

    private EditText getPatientNameEditText() {
        return findViewById(R.id.patirnt_info_name);
    }

    private TextInputLayout getPatientSurnameInputText() {
        return findViewById(R.id.patirnt_info_surname_error);
    }

    private EditText getPatientSurnameEditText() {
        return findViewById(R.id.patirnt_info_surname);
    }

    private TextInputLayout getPatientLvlInputText() {
        return findViewById(R.id.patirnt_info_lvl_error);
    }

    private EditText getPatientLvlEditText() {
        return findViewById(R.id.patirnt_info_lvl);
    }

    private TextInputLayout getPatientLoginInputText() {
        return findViewById(R.id.patirnt_info_login_error);
    }

    private EditText getPatientLoginEditText() {
        return findViewById(R.id.patirnt_info_login);
    }

    private TextInputLayout getPatientPeselInputText() {
        return findViewById(R.id.patirnt_info_PESEL_error);
    }

    private EditText getPatientPeselEditText() {
        return findViewById(R.id.patirnt_info_PESEL);
    }

    private TextInputLayout getPatientPasswordInputText() {
        return findViewById(R.id.patirnt_info_password_error);
    }

    private EditText getPatientPasswordEditText() {
        return findViewById(R.id.patirnt_info_password);
    }

    private EditText getPatientDescriptionEditText() {
        return findViewById(R.id.patirnt_info_desc);
    }

    private Button getUpdatePatientButton() {
        return findViewById(R.id.patirnt_info_save_edited_data);
    }

    private Button getEditButton() {
        return findViewById(R.id.patirnt_info_edit_button);
    }

    private void resetAllErrors() {
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
                //TODO reset
                LevelGame.EASY, false);
        return patientsObject;
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
                        //TODO update patient data
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

    private void getPatientInfo() {
        Api api = new ApiClass().getApi();
        PatientsObject patientsObject = new PatientsObject();
        Call<PatientsObject> call = api.getPatientInfo(new SharedPrefs(this).getId());
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        call.enqueue(new Callback<PatientsObject>() {
            @Override
            public void onResponse(Call<PatientsObject> call, Response<PatientsObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    progressDialog.dismiss();
                } else {
                    PatientsObject patientsObject = response.body();
                    progressDialog.dismiss();
                    setPatientInfo(patientsObject);
                }
            }

            @Override
            public void onFailure(Call<PatientsObject> call, Throwable t) {
                //TODO set error dialog
                Log.d("caretaker logIn", "onFailure: " + t.getMessage());
                progressDialog.dismiss();
            }
        });
    }

    private void setPatientInfo(PatientsObject patientsObject) {
        getPatientNameEditText().setText(patientsObject.getName());
        getPatientSurnameEditText().setText(patientsObject.getSurname());
        getPatientLoginEditText().setText(patientsObject.getLogin());
        getPatientPeselEditText().setText(patientsObject.getPersonalIdentity());
        getPatientDescriptionEditText().setText(patientsObject.getInformation());
        getPatientLvlEditText().setText(changeLvlToString(patientsObject.getLevel()));

    }

    private String changeLvlToString(LevelGame levelGame) {
        if (levelGame.toString().equals("VERYLOW")) {
            return "Bardzo łatwy";
        } else if (levelGame.toString().equals("EASY")) {
            return "łatwy";
        } else if (levelGame.toString().equals("MEDIUM")) {
            return "średni";
        } else if (levelGame.toString().equals("HIGH")) {
            return "Trudny";
        } else {
            return "Bardzo trudny";
        }
    }

}