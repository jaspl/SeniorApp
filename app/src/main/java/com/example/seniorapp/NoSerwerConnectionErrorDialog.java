package com.example.seniorapp;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.gson.internal.$Gson$Preconditions;

import java.security.AllPermission;


public class NoSerwerConnectionErrorDialog {
    Context context;

    public NoSerwerConnectionErrorDialog(Context context) {
        this.context = context;
    }

    public AlertDialog startErrorDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Coś poszło nie tak! ");
        builder.setMessage("\nBrak połączenia z serwerem");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                context.startActivity(new Intent(context,StartInActivity.class));
            }
        });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }
    public AlertDialog logInRegistrError(){
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Nieprawidłowe dane ");
        builder.setMessage("\nSprawdź poprawność wprowadzonych danych");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = builder.create();

        return alertDialog;
    }
    public AlertDialog checkCodeAndSetErrorDialog(int code){
        if(code!=200){
            return startErrorDialog();
        }
        else return null;
    }
}
