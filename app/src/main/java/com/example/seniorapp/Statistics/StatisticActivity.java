package com.example.seniorapp.Statistics;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.ProgressDialogClass;
import com.example.seniorapp.R;
import com.example.seniorapp.SelectedPatientActivity;
import com.example.seniorapp.SharedPrefs;
import com.example.seniorapp.Utils.LevelGame;
import com.example.seniorapp.Utils.NameGame;
import com.example.seniorapp.Utils.StatusGame;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class StatisticActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_statistic);
        setButtonsOnClicks();
    }

    private void setButtonsOnClicks() {
        LinearLayout mmse = findViewById(R.id.MMSE_statistuc);
        mmse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // getDataAboutGame("MMSE",NameGame.COLORS);
            //TODO
            }
        });
        LinearLayout hangman = findViewById(R.id.hangman_statistic);
        hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAboutGame("Wisielec",NameGame.HANGMAN);
            }
        });
        LinearLayout symbols = findViewById(R.id.symbol_game_statistic);
        symbols.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAboutGame("Symbole",NameGame.SYMBOLS);
            }
        });
        LinearLayout numbers = findViewById(R.id.number_game_statistic);
        numbers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAboutGame("Cyfry",NameGame.NUMBERPAIR);
            }
        });
        LinearLayout memory = findViewById(R.id.memory_game_statistic);
        memory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAboutGame("Meomory",NameGame.MEMORY);
            }
        });
        LinearLayout colors = findViewById(R.id.color_game_statistic);
        colors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDataAboutGame("Kolory",NameGame.COLORS);
            }
        });
        FloatingActionButton exit = findViewById(R.id.end_game_floatig_buton);
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent( StatisticActivity.this, SelectedPatientActivity.class));
            }
        });
    }

    private void getDataAboutGame(String chartTitle,NameGame nameGame) {
        Intent intent = new Intent(getApplicationContext(), ChartsActivity.class);
        intent.putExtra("gameTitle", chartTitle);
        intent.putExtra("gameName",nameGame);

            Log.d("TAG", "getAllResults: " + nameGame);

            Api api = new ApiClass().getApi();
            ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
            progressDialog.show();
            Call<List<GamesObject>> call = api.getGames(new SharedPrefs(getApplicationContext()).getId(),nameGame);
            call.enqueue(new Callback<List<GamesObject>>() {
                @Override
                public void onResponse(Call<List<GamesObject>> call, Response<List<GamesObject>> response) {
                    if (!response.isSuccessful()) {
                        Log.d("code:", "" + response.code());
                        //TODO error handler when sth i wrong
                        progressDialog.dismiss();

                    } else {
                        progressDialog.dismiss();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                List<GamesObject> gamesObjects = response.body();
                                Bundle bundle = new Bundle();
                                bundle.putSerializable("ARRAYLIST",(Serializable)gamesObjects);
                                intent.putExtra("BUNDLE",bundle);
                                startActivity(intent);
                            }
                        });


                    }
                }

                @Override
                public void onFailure(Call<List<GamesObject>> call, Throwable t) {
                    progressDialog.dismiss();
                }
            });

    }

}