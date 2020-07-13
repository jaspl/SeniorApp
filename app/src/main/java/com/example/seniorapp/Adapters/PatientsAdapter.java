package com.example.seniorapp.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seniorapp.Models.PatientsObject;
import com.example.seniorapp.Patterns.Patient;
import com.example.seniorapp.R;
import com.example.seniorapp.SelectedPatientActivity;
import com.example.seniorapp.SharedPrefs;

import java.util.List;

public class PatientsAdapter extends BaseAdapter {
    Activity activity;
    List<PatientsObject> patientObjects;


    public PatientsAdapter(List<PatientsObject> patientObjects, Activity activity) {
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
        TextView name = view.findViewById(R.id.patient_list_patient_name);
        name.setText(patientObjects.get(i).getName());
        TextView surName = view.findViewById(R.id.patient_list_patient_surname);
        surName.setText(patientObjects.get(i).getSurname());
        LinearLayout button = view.findViewById(R.id.patient_list_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity.getBaseContext(), SelectedPatientActivity.class);
                new SharedPrefs(activity).saveName(patientObjects.get(i).getName()+" "+patientObjects.get(i).getSurname());
                activity.startActivity(intent);
                new SharedPrefs(activity).saveId(patientObjects.get(i).getId());
                new SharedPrefs(activity).saveLvl(patientObjects.get(i).getLevel());
                int id = new SharedPrefs(activity).getId();
                Log.d("id", ""+id);
            }
        });

        return view;
    }
}
