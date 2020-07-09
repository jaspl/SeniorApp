package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Models.PatientsObject;
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
        setSpinner();
        Log.d("id", "" + new SharedPrefs(this).getId());
        getPatientInfo();
        setAllButtonsOnClick();
        setAllGapsEnabled(false);

    }

    private void setAllButtonsOnClick() {
        getEditButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (getPatientNameEditText().isEnabled()) {
                    setAllGapsEnabled(false);
                } else setAllGapsEnabled(true);
            }
        });
        getUpdatePatientButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkIfAllDataAreCorect();
            }
        });

    }

    private void setAllGapsEnabled(Boolean bool) {
        getPatientLvlSpinner().setEnabled(bool);
        getPatientNameEditText().setEnabled(bool);
        getPatientSurnameEditText().setEnabled(bool);
        getYesRadioButton().setEnabled(false);
        getNoRadioButton().setEnabled(false);
        getPatientLoginEditText().setEnabled(bool);
        getPatientPeselEditText().setEnabled(false);
        getPatientDescriptionEditText().setEnabled(bool);
        if (bool) {
            getUpdatePatientButton().setVisibility(View.VISIBLE);
            getEditButton().setText("ANULUJ");

        } else {
            getUpdatePatientButton().setVisibility(View.GONE);
            getEditButton().setText("EDYTUJ");
            getPatientInfo();
        }
    }

    private void setSpinner() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.spinner_item, getResources().getStringArray(R.array.lvls));
        getPatientLvlSpinner().setAdapter(adapter);
    }

    private void checkIfAllDataAreCorect() {
        if (checkIfNameIsOk() &
                checkIfSurnameIsOk() &
                checkIfPeselIsOk()) {
            updatePatientInfo();
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
        resetAllErrors();
        String value = getPatientPeselEditText().getText().toString();
        if (getPatientPeselEditText().getText().toString().equals("") || !value.matches("\\d*")) {
            getPatientPeselInputText().setError("Błędny pesel");
            return false;
        } else {
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


    private Spinner getPatientLvlSpinner() {
        return findViewById(R.id.patirnt_info_lvl_spinner);
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

    private RadioButton getYesRadioButton() {
        return findViewById(R.id.patirnt_info_MMSE_yes);
    }

    private RadioButton getNoRadioButton() {
        return findViewById(R.id.patirnt_info_MMSE_no);
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
        PatientsObject patientsObject = new PatientsObject(
                getPatientNameEditText().getText().toString(),
                getPatientSurnameEditText().getText().toString(),
                getPatientLoginEditText().getText().toString(),
                getPatientPasswordEditText().getText().toString(),
                getPatientPeselEditText().getText().toString(),
                getPatientDescriptionEditText().getText().toString(),
                //TODO reset
                LevelGame.EASY, false);
        return patientsObject;
    }

    private void updatePatientInfo() {
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        Call<PatientsObject> call = api.updatePatientData(getUpdatedPatientInfo());
        call.enqueue(new Callback<PatientsObject>() {
            @Override
            public void onResponse(Call<PatientsObject> call, Response<PatientsObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    PatientsObject patientsObject = response.body();
                    getPatientInfo();
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<PatientsObject> call, Throwable t) {
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
        setMMSEIfitsUsed(patientsObject.getLevelOfMMSE());
        setLvlOnSpinner(patientsObject.getLevel());
    }

    private PatientsObject getUpdatedPatientInfo() {
        //TODO set
        PatientsObject patientsObject = new PatientsObject(
                getPatientNameEditText().getText().toString(),
                getPatientSurnameEditText().getText().toString(),
                getPatientLoginEditText().getText().toString(),
                getPatientPeselEditText().getText().toString(),
                getPatientDescriptionEditText().getText().toString(), getLevelGame(),
                getSelectedMMSEYesOrNo()
        );
        patientsObject.setId(new SharedPrefs(this).getId());
        return patientsObject;
    }

    private void setMMSEIfitsUsed(boolean bool) {
        if (bool) {
            getYesRadioButton().setChecked(true);
            getNoRadioButton().setChecked(false);
        } else {
            getYesRadioButton().setChecked(false);
            getNoRadioButton().setChecked(true);
        }
    }

    private LevelGame getLevelGame() {
        int lvl = getPatientLvlSpinner().getSelectedItemPosition();
        if (lvl == 0) return LevelGame.VERYLOW;
        else if (lvl == 1) return LevelGame.EASY;
        else if (lvl == 2) return LevelGame.MEDIUM;
        else if (lvl == 3) return LevelGame.HIGH;
        else return LevelGame.HARD;
    }

    private Boolean getSelectedMMSEYesOrNo() {
        if (getYesRadioButton().isSelected()) {
            return true;
        } else return false;
    }

    private void setLvlOnSpinner(LevelGame levelGame) {
        if (levelGame.toString().equals("VERYLOW")) {
            getPatientLvlSpinner().setSelection(0);
        } else if (levelGame.toString().equals("EASY")) {
            getPatientLvlSpinner().setSelection(1);
        } else if (levelGame.toString().equals("MEDIUM")) {
            getPatientLvlSpinner().setSelection(2);
        } else if (levelGame.toString().equals("HIGH")) {
            getPatientLvlSpinner().setSelection(3);
        } else {
            getPatientLvlSpinner().setSelection(4);
        }
    }

}