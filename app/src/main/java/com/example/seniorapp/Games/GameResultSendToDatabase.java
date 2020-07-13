package com.example.seniorapp.Games;

import android.app.ProgressDialog;
import android.content.Context;
import android.util.Log;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.Models.TestMmseObject;
import com.example.seniorapp.NoSerwerConnectionErrorDialog;
import com.example.seniorapp.ProgressDialogClass;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GameResultSendToDatabase {
    public void sendDataToDatabase( Context context,GamesObject gamesObject){
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(context, "wczytywanie");
        progressDialog.show();
        Call<GamesObject> call = api.addGame(gamesObject);
        call.enqueue(new Callback<GamesObject>() {
            @Override
            public void onResponse(Call<GamesObject> call, Response<GamesObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    noSerwerConnectionError(context);
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                }
            }

            @Override
            public void onFailure(Call<GamesObject> call, Throwable t) {
                progressDialog.dismiss();
                noSerwerConnectionError(context);
            }
        });
    }
    private void noSerwerConnectionError(Context context) {
        new NoSerwerConnectionErrorDialog(context).startErrorDialog().show();
    }
}
