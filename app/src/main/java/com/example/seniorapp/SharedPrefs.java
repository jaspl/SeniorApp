package com.example.seniorapp;


import android.content.Context;
import android.content.SharedPreferences;

import com.example.seniorapp.Utils.LevelGame;

import static android.content.Context.MODE_PRIVATE;

public class SharedPrefs {
    private SharedPreferences preferences;
    private static final String PREFERENCES_NAME = "PatientInfo";
    private static final String ID = "id";
    private static final String LVL = "lvl";
    private static final String PatientName = "fullName";
    private static final String PatientLogin = "login";

    public SharedPrefs(Context context) {
        preferences = context.getSharedPreferences(PREFERENCES_NAME, MODE_PRIVATE);
    }

    public void saveId(int id) {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putInt(ID, id);
        preferencesEditor.commit();
    }

    public void saveName(String name) {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(PatientName, name);
        preferencesEditor.commit();
    }

    public void saveLogin(String login) {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putString(PatientLogin, login);
        preferencesEditor.commit();
    }

    public void saveLvlInSharedPrefs(int lvl) {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.putInt(LVL, lvl);
        preferencesEditor.commit();
    }

    public int getId() {
        return preferences.getInt(ID, 0);
    }

    public String getName() {
        return preferences.getString(PatientName, " ");
    }

    public String getLogin() {
        return preferences.getString(PatientLogin, " ");
    }

    public int getlvlInInt() {
        return preferences.getInt(LVL, 0);
    }

    public void removeAllFromSP() {
        SharedPreferences.Editor preferencesEditor = preferences.edit();
        preferencesEditor.clear().commit();
    }

    public void saveLvl(LevelGame levelGame) {
        if (levelGame.equals(LevelGame.VERYLOW)) saveLvlInSharedPrefs(0);
        else if (levelGame.equals(LevelGame.EASY)) saveLvlInSharedPrefs(1);
        else if (levelGame.equals(LevelGame.MEDIUM)) saveLvlInSharedPrefs(2);
        else if (levelGame.equals(LevelGame.HIGH)) saveLvlInSharedPrefs(3);
        else saveLvlInSharedPrefs(4);
    }

    public LevelGame getLvl() {
        if (getlvlInInt() == 0) return LevelGame.VERYLOW;
        else if (getlvlInInt() == 1) return LevelGame.EASY;
        else if (getlvlInInt() == 1) return LevelGame.MEDIUM;
        else if (getlvlInInt() == 3) return LevelGame.HIGH;
        else return LevelGame.HARD;
    }

}
