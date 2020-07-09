package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Statistics.StatisticActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SelectedPatientActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selected_patient);
        setButtonsOnClics();
        setPatientName();

    }

    private void setPatientName() {
        Intent intent = getIntent();
        String patientFullName = intent.getStringExtra("name") + " " + intent.getStringExtra("surname");
        TextView textView = findViewById(R.id.selected_patient);
        textView.append(patientFullName);
    }

    private void setButtonsOnClics() {
        final Button deleteButton = findViewById(R.id.selected_patient_delete_patient_button);
        Button infoButton = findViewById(R.id.selected_patient_info_button);
        Button MMSEButton = findViewById(R.id.selected_patient_MMSE_button);
        Button statisticButton = findViewById(R.id.selected_patient_statistics_button);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                deletePatient();
            }
        });
        infoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(SelectedPatientActivity.this, PatientInfoActivity.class));
            }
        });
        MMSEButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SelectedPatientActivity.this, MmseActivity.class);
                startActivity(intent);
            }
        });
        statisticButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //TODO statistic
                startActivity(new Intent(SelectedPatientActivity.this, StatisticActivity.class));
            }
        });
    }

    private void deletePatient() {
        final AlertDialog.Builder dialog = new AlertDialog.Builder(SelectedPatientActivity.this);
        dialog.setMessage("Czy na pewno chcesz usunąć pacjenta?").setCancelable(false);
        dialog.setPositiveButton("TAK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removaPatientFromSerwer();
            }
        });
        dialog.setNegativeButton("NIE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.cancel();
            }
        });
        AlertDialog alertDialog = dialog.create();
        alertDialog.show();
    }

    private void removaPatientFromSerwer() {
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        Call<PatientsObject> call = api.deletePatient(new SharedPrefs(this).getId());
        call.enqueue(new Callback<PatientsObject>() {
            @Override
            public void onResponse(Call<PatientsObject> call, Response<PatientsObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    //TODO error handler when sth i wrong
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    startActivity(new Intent(SelectedPatientActivity.this, PatientListActivity.class));
                }
            }

            @Override
            public void onFailure(Call<PatientsObject> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }
}
