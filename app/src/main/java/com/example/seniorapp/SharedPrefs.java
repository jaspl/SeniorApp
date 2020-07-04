package com.example.seniorapp;


import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefs {
    private SharedPreferences preferences;
    private static final String PREFERENCES_NAME = "PatientInfo";
    private static final String ID = "id";
    private static final String LVL = "lvl";

    public SharedPrefs(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    public void saveId(int id) {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putInt(ID, id);
        preferencesEditor.commit();
    }

    public int getId() {
        return preferences.getInt(ID, 0);
    }

    public void removeAllFromSP() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.clear().commit();
    }
}
