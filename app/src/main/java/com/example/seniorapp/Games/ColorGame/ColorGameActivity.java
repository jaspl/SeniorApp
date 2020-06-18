package com.example.seniorapp.Games.ColorGame;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seniorapp.R;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class ColorGameActivity extends AppCompatActivity {
    int colorNumber = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_color_game);
        setLvl();
    }

    private int getLvl() {
        //TODO get lvl
        int lvl = 5;
        return lvl;
    }

    private void setLvl() {

        switch (getLvl()) {
            case 1:
                colorNumber = 3;
                break;
            case 2:
                colorNumber = 4;
                break;
            case 3:
                colorNumber = 5;
                break;
            case 4:
                colorNumber = 6;
                break;
            case 5:
                colorNumber = 7;
                break;
        }

        setButtons(colorNumber);
        setColorText(colorNumber);
    }

    private void setButtons(int colorNumber) {
        LinearLayout colorByttonsLayout = getButtonsLayout();
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
        }
    }

    private void setColorButtonClickAction(final Button colorButton) {
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), colorButton.getTag().toString(),
                        Toast.LENGTH_LONG).show();
                if (colorButton.getTag().equals(getColotText().getTag())) {
                    setColorText(colorNumber);
                    //TODO set counter
                }
            }
        });
    }


    private void setColorText(int colorNumber) {
        TypedArray colors = getResources().obtainTypedArray(R.array.colors);
        TypedArray colorNames = getResources().obtainTypedArray(R.array.colorNames);
        TextView colorText = getColotText();
        Random r = new Random();
        int tag = ThreadLocalRandom.current().nextInt(0, colorNumber);
        colorText.setTag(tag);
        colorText.setTextColor(colors.getColor(tag, 0));
        colorText.setText(colorNames.getText(getRandomExceptOne(tag)));
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

    private LinearLayout getButtonsLayout() {
        return findViewById(R.id.color_game_button_layout);
    }

    private TextView getColotText() {
        return findViewById(R.id.color_game_text);
    }

}