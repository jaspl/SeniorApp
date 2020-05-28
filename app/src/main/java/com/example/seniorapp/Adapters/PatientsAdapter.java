package com.example.seniorapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;

import com.example.seniorapp.Patterns.Patient;
import com.example.seniorapp.R;
import com.example.seniorapp.SelectedPatientActivity;

import java.util.List;

public class PatientsAdapter extends BaseAdapter {
    Activity activity;
    List<Patient> patientObjects;


    public PatientsAdapter(List<Patient> patientObjects, Activity activity) {
        this.patientObjects = patientObjects;
        this.activity = activity;
    }

    @Override
    public int getCount() {
        return patientObjects.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LayoutInflater layoutInflater = activity.getLayoutInflater();
        view = layoutInflater.inflate(R.layout.single_patient_layout, null, true);
        LinearLayout button = view.findViewById(R.id.patient_list_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getBaseContext(), SelectedPatientActivity.class);
                activity.startActivity(intent);
            }
        });

        return view;
    }
}
