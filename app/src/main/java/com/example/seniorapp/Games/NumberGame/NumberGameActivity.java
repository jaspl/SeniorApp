package com.example.seniorapp.Games.NumberGame;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.seniorapp.GameSelectorActivity;
import com.example.seniorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;

public class NumberGameActivity extends AppCompatActivity {
    int digitsNumber = 0;
    int exercises = 0;
    List<Integer> randomDigitsList; // lista cyfr
    int searchNumber = 0;   // do wyswietlenia jakiej liczby szukamy
    int count = 0;  // do wyswietlenia ile jest par do znalezienia

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_game);
        setLvl();
        setEndGameButton();
    }

    private int getLvl() {
        //TODO get lvl
        int lvl = 5;
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
        int exercise = rand.nextInt(exercises);
        if (exercise == 0) {
            generateRandomNumbersAdditions(digitsNumber);
        } else if( exercise == 1) {
            generateRandomNumbersMultiplications(digitsNumber);
        }
        setButtons(digitsNumber, getButtonsLayout());
    }

    private void setButtons(int digitsNumber, LinearLayout numberButtonsLayout) {
        for (int i = 0; i < digitsNumber; i++) {
            Button numberButton = new Button(this);
            numberButton.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT));
            LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) numberButton.getLayoutParams();
            params.setMargins(0, 0, 10, 0);
            numberButton.setLayoutParams(params);
            numberButton.setText(randomDigitsList.get(i));
            numberButtonsLayout.addView(numberButton);
            setNumberButtonClickAction(numberButton);
        }
    }

    private void generateRandomNumbersAdditions(int digitsNumber) {
        Random rand = new Random(); //instance of random class
        int upperbound = 9;
        searchNumber = rand.nextInt(18);
        do {
            //generate random values from 0-24
            for (int i = 0; i < digitsNumber; i++) {
                randomDigitsList.add(rand.nextInt(upperbound));
            }
            count = 0;
            for (int i = 1; i < digitsNumber; i++) {
                if (randomDigitsList.get(i-1) + randomDigitsList.get(i) == searchNumber) {
                    count++;
                }
            }
        } while(count==0);
    }

    private void generateRandomNumbersMultiplications(int digitsNumber) {
        Random rand = new Random(); //instance of random class
        int upperbound = 9;
        searchNumber = rand.nextInt(81);
        do {
            //generate random values from 0-24
            for (int i = 0; i < digitsNumber; i++) {
                randomDigitsList.add(rand.nextInt(upperbound));
            }
            count = 0;
            for (int i = 1; i < digitsNumber; i++) {
                if (randomDigitsList.get(i-1) * randomDigitsList.get(i) == searchNumber) {
                    count++;
                }
            }
        } while(count==0);
    }

    private void setNumberButtonClickAction(final Button colorButton) {
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

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
}
