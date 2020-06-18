package com.example.seniorapp;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import com.example.seniorapp.Adapters.PatientsAdapter;
import com.example.seniorapp.Patterns.Patient;
import java.util.ArrayList;
import java.util.List;

public class PatientListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_list);
        ListView patientList = findViewById(R.id.patient_list);
        //TODO get patients list from database
        patientList.setAdapter(new PatientsAdapter(setPatients(), this));
    }

    public List<Patient> setPatients() {
        List<Patient> patients = new ArrayList<>();
        Patient patient = new Patient("Jan", "Napierała");
        Patient patient1 = new Patient("Stan", "Kowalski");
        Patient patient2 = new Patient("Stan", "Kowalski");
        Patient patient3 = new Patient("Stan", "Kowalski");
        patients.add(patient);
        patients.add(patient1);
        patients.add(patient2);
        patients.add(patient3);
        return patients;
    }
}
