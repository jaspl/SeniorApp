package com.example.seniorapp.Games.MemoryGame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.view.View;
import android.widget.ImageView;

import com.example.seniorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MemoryGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        MemoryGameController memoryGameController = new MemoryGameController();
        memoryGameController.setGame(2);
        setButtonsClicks();
    }

    private void showCards() {
        for (Card card : MemoryGameDataHolder.getInstance().cards) {
            System.out.println("Id:" + card.cardSign + "    row:" + card.column + "     col:" + card.column);
        }

    }

    private void setButtonsClicks() {
        getEndGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCards();
            }
        });
    }

    private FloatingActionButton getEndGameButton() {
        return findViewById(R.id.end_game_floatig_buton);
    }
}