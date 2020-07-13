package com.example.seniorapp.Games.ColorGame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.GameSelectorActivity;
import com.example.seniorapp.Games.GameResultSendToDatabase;
import com.example.seniorapp.Games.SymbolsGame.SymbolsGameActivity;
import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.NoSerwerConnectionErrorDialog;
import com.example.seniorapp.ProgressDialogClass;
import com.example.seniorapp.R;
import com.example.seniorapp.SharedPrefs;
import com.example.seniorapp.Utils.NameGame;
import com.example.seniorapp.Utils.StatusGame;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ColorGameActivity extends AppCompatActivity {
    int colorNumber = 0;
    long startTime = System.currentTimeMillis();
    long endTime = 0;
    List<Button> colorButtonsList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        setLvl();
        setEndGameButton();
        setStartTime();
    }

    @Override
    public void onBackPressed() {
    }

    private int getLvl() {
        return new SharedPrefs(this).getlvlInInt();
    }

    private void setLvl() {

        switch (getLvl()) {
            case 0:
                colorNumber = 3;
                break;
            case 1:
                colorNumber = 4;
                break;
            case 2:
                colorNumber = 5;
                break;
            case 3:
                colorNumber = 6;
                break;
            case 4:
                colorNumber = 7;
                break;
        }

        setButtons(colorNumber, getButtonsLayout());
        setColorText(colorNumber);
    }

    private void setButtons(int colorNumber, LinearLayout colorByttonsLayout) {
        TypedArray colors = getResources().obtainTypedArray(R.array.colors);
        for (int i = 0; i < colorNumber; i++) {
            Button colorButton = new Button(this);
            colorButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) colorButton.getLayoutParams();
            params.setMargins(0, 0, 10, 0);
            colorButton.setLayoutParams(params);
            colorButton.setText("");
            colorButton.setTag(i);
            colorButton.setBackgroundColor(colors.getColor(i, 0));
            colorByttonsLayout.addView(colorButton);
            setColorButtonClickAction(colorButton);
            colorButtonsList.add(colorButton);
        }
    }

    private void setAllButtonsEnabled(Boolean bool) {
        for (Button colorbutton : colorButtonsList) {
            colorbutton.setEnabled(bool);
        }
    }

    private void setColorButtonClickAction(final Button colorButton) {
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAllButtonsEnabled(false);
                setEndTime();
                float reactionTimeInSeconds = (float) (endTime - startTime) / 1000;
                if (colorButton.getTag().equals(getColotText().getTag())) {
                    sendResultToDatabase(StatusGame.SUCCESSFUL, reactionTimeInSeconds);
                } else {
                    sendResultToDatabase(StatusGame.FAILED, reactionTimeInSeconds);
                }
            }
        });
    }

    private void resetBoard() {
        setColorText(colorNumber);
        getStatusShower().setImageDrawable(null);
        setAllButtonsEnabled(true);
        setStartTime();
    }

    private void sendResultToDatabase(StatusGame statusGame, float reactionTimeInSeconds) {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
        Date date = java.util.Calendar.getInstance().getTime();
        Api api = new ApiClass().getApi();
        ProgressDialog progressDialog = new ProgressDialogClass().CustomCallBack(this, "wczytywanie");
        progressDialog.show();
        Call<GamesObject> call = api.addGame(new GamesObject(statusGame,
                String.valueOf(reactionTimeInSeconds),
                simpleDateFormat.format(date),
                new SharedPrefs(getApplicationContext()).getId(),
                new SharedPrefs(getApplicationContext()).getLvl(),
                NameGame.COLORS));
        call.enqueue(new Callback<GamesObject>() {
            @Override
            public void onResponse(Call<GamesObject> call, Response<GamesObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    noSerwerConnectionError(getApplicationContext());
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
                    if (statusGame == StatusGame.SUCCESSFUL)
                        getStatusShower().setImageDrawable(getDrawable(R.drawable.green_circle));
                    else getStatusShower().setImageDrawable(getDrawable(R.drawable.red_circle));
                    new java.util.Timer().schedule(
                            new java.util.TimerTask() {
                                @Override
                                public void run() {
                                    runOnUiThread(new Runnable() {
                                        @Override
                                        public void run() {
                                            resetBoard();
                                        }
                                    });
                                }
                            },
                            2000
                    );
                }
            }

            @Override
            public void onFailure(Call<GamesObject> call, Throwable t) {
                progressDialog.dismiss();
                noSerwerConnectionError(getApplicationContext());
            }
        });
    }

    private void noSerwerConnectionError(Context context) {
        new NoSerwerConnectionErrorDialog(context).startErrorDialog().show();
    }

    private void setColorText(int colorNumber) {
        TypedArray colors = getResources().obtainTypedArray(R.array.colors);
        TypedArray colorNames = getResources().obtainTypedArray(R.array.colorNames);
        TextView colorText = getColotText();
        int tag = ThreadLocalRandom.current().nextInt(0, colorNumber);
        colorText.setTag(tag);
        colorText.setTextColor(colors.getColor(getRandomExceptOne(tag), 0));
        colorText.setText(colorNames.getText(tag));
    }

    private int getRandomExceptOne(int number) {
        int temp = number; // This will be your value to be compared to random value
        for (int i = 0; i < colorNumber; i++) { // assuming your filteredArraylist is size 10
            int randomValue = ThreadLocalRandom.current().nextInt(0, colorNumber);
            if (randomValue == temp) {
                i--; // it will repeat the generation of random value if temp and randomValue is same
            } else {
                temp = randomValue; // you will put here the ne random value to be compared to next value
                return randomValue;
            }
        }
        return temp;
    }

    private void setEndGameButton() {
        getEndGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ColorGameActivity.this, GameSelectorActivity.class);
                startActivity(intent);
            }
        });
    }


    public FloatingActionButton getEndGameButton() {
        return findViewById(R.id.end_game_floatig_buton);
    }

    private LinearLayout getButtonsLayout() {
        return findViewById(R.id.color_game_button_layout);
    }

    private TextView getColotText() {
        return findViewById(R.id.color_game_text);
    }

    private long getTime() {
        return System.currentTimeMillis();
    }

    private void setStartTime() {
        startTime = getTime();
    }

    private void setEndTime() {
        endTime = getTime();
    }

    private ImageView getStatusShower() {
        return findViewById(R.id.color_game_satus_shower);
    }


}