package com.example.seniorapp.Games.NumberGame;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.GameSelectorActivity;
import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.Models.PairModel;
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

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NumberGameActivity extends AppCompatActivity {
    int digitsNumber = 0;
    int exercises = 0;
    List<Integer> randomDigitsList = new ArrayList<>(); // lista cyfr
    int searchNumber = 0;   // do wyswietlenia jakiej liczby szukamy
    int count = 0;  // do wyswietlenia ile jest par do znalezienia
    int click_two = 0;
    int countNumberPairs = 0;
    Button number_one = null;
    Button number_two = null;
    long startTime = System.currentTimeMillis();
    long endTime = 0;
    TextView countNumberPair = null;
    List<PairModel> positions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);
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
                digitsNumber = 10;
                exercises = 0;
                break;
            case 1:
                digitsNumber = 15;
                exercises = 0;
                break;
            case 2:
                digitsNumber = 20;
                exercises = 0;
                break;
            case 3:
                digitsNumber = 15;
                exercises = 1;
                break;
            case 4:
                digitsNumber = 20;
                exercises = 1;
                break;
        }

        Random rand = new Random();
        exercises = rand.nextInt(exercises + 1);
        TextView exercisesView = findViewById(R.id.exercisesView);
        if (exercises == 0) {
            generateRandomNumbersAdditions(digitsNumber);
            exercisesView.append("Dodawanie");
        } else if (exercises == 1) {
            generateRandomNumbersMultiplications(digitsNumber);
            exercisesView.append("Mnożenie");
        }
        Log.d(String.valueOf(searchNumber), "search: ");
        setButtons(digitsNumber, getButtonsLayout());
        TextView searchNumberView = findViewById(R.id.search_number);
        searchNumberView.setText(String.valueOf(searchNumber));

        countNumberPair = findViewById(R.id.count_number_pair);
        countNumberPair.setText("0/" + String.valueOf(count));
    }

    private void setButtons(int digitsNumber, LinearLayout numberButtonsLayout) {
        GridLayout gridLayout = new GridLayout(NumberGameActivity.this);
        gridLayout.setColumnCount(5);
        gridLayout.setRowCount(digitsNumber / 5);
        for (int i = 0; i < digitsNumber; i++) {
            Button numberButton = new Button(this);
            numberButton.setBackgroundColor(Color.LTGRAY);
            numberButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) numberButton.getLayoutParams();
            params.setMargins(10, 10, 10, 10);
            numberButton.setLayoutParams(params);
            int j = randomDigitsList.get(i);
            numberButton.setText(String.valueOf(j));
            numberButton.setTag(i);
            gridLayout.addView(numberButton);
            setNumberButtonClickAction(numberButton);
        }
        numberButtonsLayout.addView(gridLayout);
    }

    private void generateRandomNumbersAdditions(int digitsNumber) {
        Random rand = new Random(); //instance of random class
        searchNumber = rand.nextInt(19);
        do {
            //generate random values from 0-24
            rand = new Random();
            randomDigitsList.clear();
            positions.clear();
            for (int i = 0; i < digitsNumber; i++) {
                randomDigitsList.add(rand.nextInt(10));
            }
            count = 0;

            for (int i = 0; i < digitsNumber; i++) {
                for (int j = i+1; j < digitsNumber; j++) {
                    if (randomDigitsList.get(i) + randomDigitsList.get(j) == searchNumber) {
                        positions.add(new PairModel(i, j));
                        count++;
                    }
                }
            }
        } while (count == 0);
    }

    private void generateRandomNumbersMultiplications(int digitsNumber) {
        int[] multiplicationList = {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 12, 14, 16, 18,
                15, 21, 24, 27, 20, 28, 32, 36, 25, 30, 35, 40,
                45, 42, 48, 54, 49, 56, 63, 64, 72, 81};
        Random rand = new Random(); //instance of random class
        searchNumber = multiplicationList[rand.nextInt(multiplicationList.length)];
        do {
            rand = new Random();
            randomDigitsList.clear();
            positions.clear();
            for (int i = 0; i < digitsNumber; i++) {
                int r = rand.nextInt(10);
                randomDigitsList.add(r);
            }
            count = 0;

            for (int i = 0; i < digitsNumber; i++) {
                for (int j = i+1; j < digitsNumber; j++) {
                    if (randomDigitsList.get(i) * randomDigitsList.get(j) == searchNumber) {
                        positions.add(new PairModel(i, j));
                        count++;
                    }
                }
            }
        } while (count == 0);
    }

    private void setNumberButtonClickAction(final Button colorButton) {
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(colorButton.getTag().toString(), "onClick:");
                colorButton.setBackgroundColor(Color.BLUE);
                click_two++;
                if (click_two == 1) {
                    number_one = colorButton;
                } else if (click_two == 2) {
                    number_two = colorButton;
                    PairModel pairModel1 = new PairModel(Integer.parseInt(number_one.getTag().toString()),
                            Integer.parseInt(number_two.getTag().toString()));

                    if (positions.contains(pairModel1)) {
                        countNumberPairs++;
                        positions.remove(pairModel1);

                        checkUsed(pairModel1.getNumberOne(), number_one);
                        checkUsed(pairModel1.getNumberTwo(), number_two);

                        countNumberPair.setText(countNumberPairs + "/" + count);
                        click_two = 0;
                        if (countNumberPairs == count) {
                            setEndTime();
                            float reactionTimeInSeconds = (float) (endTime - startTime) / 1000;
                            sendResultToDatabase(StatusGame.SUCCESSFUL, reactionTimeInSeconds);
                            Log.d("Koniec", "Koniec: ");
                            getDialog();
                        }
                    } else {
                        number_one.setBackgroundColor(Color.LTGRAY);
                        number_two.setBackgroundColor(Color.LTGRAY);

                        click_two = 0;
                    }
                }
            }
        });
    }

    private void checkUsed(int numberOnePosition, Button button) {
        for (int j = 0; j < positions.size(); j++) {
            if (positions.get(j).getNumberOne() == numberOnePosition || positions.get(j).getNumberTwo() == numberOnePosition) {
                button.setBackgroundColor(Color.YELLOW);
                return;
            }
        }
        button.setBackgroundColor(Color.GREEN);
        button.setClickable(false);
    }

    private void setEndGameButton() {
        getEndGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEndTime();
                float reactionTimeInSeconds = (float) (endTime - startTime) / 1000;
                sendResultToDatabase(StatusGame.FAILED, reactionTimeInSeconds);

                Intent intent = new Intent(NumberGameActivity.this, GameSelectorActivity.class);
                startActivity(intent);
            }
        });
    }


    public FloatingActionButton getEndGameButton() {
        return findViewById(R.id.end_game_floatig_buton);
    }

    private LinearLayout getButtonsLayout() {
        return findViewById(R.id.number_game_button_layout);
    }

    private void getDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Wygrałeś!\n\n")
                .setCancelable(false)
                .setPositiveButton("Zagraj ponownie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(NumberGameActivity.this, NumberGameActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Zakończ", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(NumberGameActivity.this, GameSelectorActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("JEJ");
        alert.show();
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
                NameGame.NUMBERPAIR));
        call.enqueue(new Callback<GamesObject>() {
            @Override
            public void onResponse(Call<GamesObject> call, Response<GamesObject> response) {
                if (!response.isSuccessful()) {
                    Log.d("code:", "" + response.code());
                    noSerwerConnectionError(getApplicationContext());
                    progressDialog.dismiss();
                } else {
                    progressDialog.dismiss();
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
}
