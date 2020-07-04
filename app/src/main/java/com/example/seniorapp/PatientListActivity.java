package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Adapters.PatientsAdapter;
import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Patterns.Patient;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PatientListActivity extends AppCompatActivity {
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        context = this;
        getPatienstList();
        setButtonAcctions();
    }

    @Override
    protected void onStart() {
        super.onStart();
        getPatienstList();
    }

    private void setButtonAcctions() {
        getFloattingButtonAddPatient().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PatientListActivity.this, AddNewPatientActivity.class);
                startActivity(intent);
            }
        });
    }

    private FloatingActionButton getFloattingButtonAddPatient() {
        return findViewById(R.id.add_new_patient_floating_button);
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
                    ListView patientList = findViewById(R.id.patient_list);
                    patientList.setAdapter(new PatientsAdapter(patientsObjectList, (Activity) context));
                }
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<List<PatientsObject>> call, Throwable t) {
                progressDialog.dismiss();
            }
        });
    }

}
