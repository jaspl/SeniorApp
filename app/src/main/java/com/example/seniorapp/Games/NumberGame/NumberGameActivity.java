package com.example.seniorapp.Games.NumberGame;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.example.seniorapp.GameSelectorActivity;
import com.example.seniorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

import static java.lang.Integer.*;

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
        //TODO get lvl
        int lvl = 4;
        return lvl;
    }

    private void setLvl() {

        switch (getLvl()) {
            case 1:
                digitsNumber = 10;
                exercises = 0;
                break;
            case 2:
                digitsNumber = 15;
                exercises = 0;
                break;
            case 3:
                digitsNumber = 20;
                exercises = 0;
                break;
            case 4:
                digitsNumber = 15;
                exercises = 1;
                break;
            case 5:
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
            numberButton.setTag(j);
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
            for (int i = 0; i < digitsNumber; i++) {
                randomDigitsList.add(rand.nextInt(10));
            }
            count = 0;
            for (int i = 1; i < digitsNumber; i++) {
                if (randomDigitsList.get(i - 1) + randomDigitsList.get(i) == searchNumber) {
                    count++;
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
            for (int i = 0; i < digitsNumber; i++) {
                int r = rand.nextInt(10);
                randomDigitsList.add(r);
            }
            count = 0;
            for (int i = 1; i < digitsNumber; i++) {
                if (randomDigitsList.get(i - 1) * randomDigitsList.get(i) == searchNumber) {
                    count++;
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
                    if ((exercises == 0 && parseInt(number_one.getText().toString()) + parseInt(number_two.getText().toString()) == searchNumber) ||
                            (exercises == 1 && parseInt(number_one.getText().toString()) * parseInt(number_two.getText().toString()) == searchNumber)) {
                        countNumberPairs++;
                        number_one.setBackgroundColor(Color.GREEN);
                        number_two.setBackgroundColor(Color.GREEN);
                        countNumberPair.setText(countNumberPairs + "/" + count);
                        click_two = 0;
                        if (countNumberPairs == count) {
                            setEndTime();
                            float reactionTimeInSeconds = (float) (endTime - startTime) / 1000;
                            sendResultToDatabase();
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


    private void setEndGameButton() {
        getEndGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //todo set end game action;
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
        builder.setMessage("Czy chcesz kontynuować grę ?")
                .setCancelable(false)
                .setPositiveButton("Tak", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(NumberGameActivity.this, NumberGameActivity.class);
                        startActivity(intent);
                    }
                })
                .setNegativeButton("Nie", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        Intent intent = new Intent(NumberGameActivity.this, GameSelectorActivity.class);
                        startActivity(intent);
                    }
                });
        AlertDialog alert = builder.create();
        //Setting the title manually
        alert.setTitle("?");
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

    private void sendResultToDatabase() {
        //TODO
    }
}
