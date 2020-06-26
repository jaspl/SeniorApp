package com.example.seniorapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.seniorapp.Games.ColorGame.ColorGameActivity;
import com.example.seniorapp.Games.MemoryGame.MemoryGameActivity;
import com.example.seniorapp.Games.SymbolsGame.SymbolsGameActivity;

public class GameSelectorActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_selector);
        setAllButtonsClick();
    }

    private void setAllButtonsClick() {
        LinearLayout hangman = findViewById(R.id.hangman);
        hangman.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO change activity
                //startActivity(new Intent(GameSelectorActivity.this, MemoryGameActivity.class));
            }
        });
        LinearLayout symbolGame = findViewById(R.id.symbol_game);
        symbolGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, SymbolsGameActivity.class));
            }
        });
        LinearLayout colorGame = findViewById(R.id.color_game);
        colorGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, ColorGameActivity.class));
            }
        });
        LinearLayout memoryGame = findViewById(R.id.memory_game);
        memoryGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameSelectorActivity.this, MemoryGameActivity.class));
            }
        });
        LinearLayout numberGame = findViewById(R.id.number_game);
        numberGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO change activity
                //startActivity(new Intent(GameSelectorActivity.this, MemoryGameActivity.class));
            }
        });
        LinearLayout logOut = findViewById(R.id.log_out);
        logOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO log Out Action
                startActivity(new Intent(GameSelectorActivity.this, StartInActivity.class));
            }
        });
    }
}