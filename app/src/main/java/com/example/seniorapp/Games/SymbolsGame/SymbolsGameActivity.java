package com.example.seniorapp.Games.SymbolsGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.example.seniorapp.R;

import java.util.ArrayList;
import java.util.List;

public class SymbolsGameActivity extends AppCompatActivity {
    List<LinearLayout> shapeButtons = new ArrayList();
    List<LinearLayout> colorButtons = new ArrayList();
    ShapeDetails shapeDetails = new ShapeDetails();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symbols_game);
        setColorButtons(4, getColorButtonsLayout());
        setShapeButtons(4, getShapeButtonsLayout());
        ImagesSeter();
    }

    private void setColorButtons(int colorNumber, LinearLayout colorByttonsLayout) {
        TypedArray colors = getResources().obtainTypedArray(R.array.colors);
        for (int i = 0; i < colorNumber; i++) {
            LinearLayout colorButton = linearButtonCreator(colorByttonsLayout, i);
            ImageView colorImage = new ImageView(this);
            colorImage.setBackgroundColor(colors.getColor(i, 0));
            colorImage.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            colorImage.setLayoutParams(layoutParams);
            setColorButtonClickAction(colorButton);
            colorButton.addView(colorImage);
            colorButtons.add(colorButton);
        }
    }

    private void setShapeButtons(int shapesNumber, LinearLayout colorByttonsLayout) {
        TypedArray shapes = getResources().obtainTypedArray(R.array.sahpes);
        for (int i = 0; i < shapesNumber; i++) {
            LinearLayout shapeButton = linearButtonCreator(colorByttonsLayout, i);
            ImageView colorImage = new ImageView(this);
            colorImage.setImageDrawable(shapes.getDrawable(i));
            colorImage.setPadding(10, 10, 10, 10);
            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(100, 100);
            colorImage.setLayoutParams(layoutParams);
            setShapeButtonClickAction(shapeButton);
            shapeButton.addView(colorImage);
            shapeButtons.add(shapeButton);
        }
    }

    private LinearLayout linearButtonCreator(LinearLayout colorByttonsLayout, int tagid) {
        LinearLayout colorButton = new LinearLayout(this);
        colorButton.setLayoutParams(new LinearLayout.LayoutParams(100, 100));
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) colorButton.getLayoutParams();
        params.setMargins(0, 0, 20, 0);
        colorButton.setLayoutParams(params);
        colorButton.setTag(tagid);
        colorButton.setGravity(Gravity.CENTER);
        colorButton.setOrientation(LinearLayout.HORIZONTAL);
        colorButton.setBackgroundColor(Color.GRAY);
        colorButton.setPadding(10, 10, 10, 10);
        colorByttonsLayout.addView(colorButton);
        return colorButton;
    }

    private void setColorButtonClickAction(final LinearLayout colorButton) {
        colorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), colorButton.getTag().toString(),
                        Toast.LENGTH_LONG).show();
                clearColorButtonsColorsToGray();
                colorButton.setBackgroundColor(Color.YELLOW);
                shapeDetails.setColor((int) colorButton.getTag());
                //TODO resets other colors
            }
        });
    }

    private void setShapeButtonClickAction(final LinearLayout shapeButton) {
        shapeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), shapeButton.getTag().toString(),
                        Toast.LENGTH_LONG).show();
                clearShapeButtonColorsToGray();
                shapeButton.setBackgroundColor(Color.YELLOW);
                shapeDetails.setShape((int) shapeButton.getTag());
            }
        });
    }

    private void clearColorButtonsColorsToGray() {
        for (LinearLayout button : colorButtons) {
            button.setBackgroundColor(Color.GRAY);
        }
    }

    private void clearShapeButtonColorsToGray() {
        for (LinearLayout button : shapeButtons) {
            button.setBackgroundColor(Color.GRAY);
        }
    }

    private LinearLayout getColorButtonsLayout() {
        return findViewById(R.id.symbol_game_colors_button_layout);
    }

    private LinearLayout getShapeButtonsLayout() {
        return findViewById(R.id.symbol_game_shape_button);
    }

    private void ImagesSeter() {
        LinearLayout button00 = findViewById(R.id.symbol_game_bytton_c0r0);
        LinearLayout button10 = findViewById(R.id.symbol_game_bytton_c1r0);
        LinearLayout button20 = findViewById(R.id.symbol_game_bytton_c2r0);
        LinearLayout button01 = findViewById(R.id.symbol_game_bytton_c0r1);
        LinearLayout button11 = findViewById(R.id.symbol_game_bytton_c1r1);
        LinearLayout button21 = findViewById(R.id.symbol_game_bytton_c2r1);
        LinearLayout button02 = findViewById(R.id.symbol_game_bytton_c0r2);
        LinearLayout button12 = findViewById(R.id.symbol_game_bytton_c1r2);
        LinearLayout button22 = findViewById(R.id.symbol_game_bytton_c2r2);
        button00.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c0r0);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));
            }
        });
        button10.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c1r0);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));

            }
        });
        button20.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c2r0);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));

            }
        });
        button01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c0r1);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));

            }
        });
        button11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c1r1);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));

            }
        });
        button21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c2r1);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));

            }
        });
        button02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c0r2);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));

            }
        });
        button12.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c1r2);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));

            }
        });
        button22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ImageView image = findViewById(R.id.symbol_game_image_c2r2);
                image.setImageDrawable(getResources().getDrawable(new ShapeSelector().shapeSelector(shapeDetails.color,shapeDetails.shape), getTheme()));
            }
        });
    }

    private void setShapeDetailsColRow(int col, int row) {
        shapeDetails.setRow(row);
        shapeDetails.setColumn(col);

    }
}