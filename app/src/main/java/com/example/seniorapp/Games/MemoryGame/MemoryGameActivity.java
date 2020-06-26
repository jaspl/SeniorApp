package com.example.seniorapp.Games.MemoryGame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.seniorapp.GameSelectorActivity;
import com.example.seniorapp.Games.ColorGame.ColorGameActivity;
import com.example.seniorapp.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MemoryGameActivity extends AppCompatActivity {
    boolean cardClicked = false;
    Card currentCard;
    int currentTime = 0;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory_game);
        final MemoryGameController memoryGameController = new MemoryGameController();
        memoryGameController.setLvl(1);
        setButtonsClicks();
        setAllCardsEnabled(false);
        setTime(memoryGameController.timeInSeconds);
        new java.util.Timer().schedule(
                new java.util.TimerTask() {
                    @Override
                    public void run() {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                turnOverAllCards();
                                setAllCardsEnabled(true);
                                startTimer(memoryGameController.timeInSeconds);
                            }
                        });
                    }
                },
                5000
        );
    }
private void setTime(int timeInSeconds){

    int min = timeInSeconds/60;
    int seconds = timeInSeconds%60;
    updateTimer(min,seconds);
}

    private void setButtonsClicks() {
        getEndGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO set Exit action
                Intent intent = new Intent(MemoryGameActivity.this, GameSelectorActivity.class);
                startActivity(intent);
            }
        });
        setImageOnClicsk();
    }

    private FloatingActionButton getEndGameButton() {
        return findViewById(R.id.end_game_floatig_buton);
    }

    private void setImageOnClicsk() {
        for (final Card card : MemoryGameDataHolder.getInstance().cards) {
            int id = (new MemoryGameController().getIdByColRow(card.column, card.row));
            final ImageView img = findViewById(id);
            img.setEnabled(true);
            img.setImageDrawable(getDrawable(new MemoryGamePictureSeloctor().selector(card.cardSign)));
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardClickAction(card, img);
                }
            });
        }
    }

    private ImageView getImgById(int id) {
        return findViewById(id);
    }

    private void turnOverAllCards() {
        for (Card card : MemoryGameDataHolder.getInstance().cards) {
            ImageView img = getImgById(new MemoryGameController().getIdByColRow(card.column, card.row));
            img.setImageDrawable(getDrawable(R.drawable.pentagon_shape));
        }
    }


    private void cardClickAction(Card card, ImageView img) {
        img.setImageDrawable(getDrawable(new MemoryGamePictureSeloctor().selector(card.cardSign)));
        img.setEnabled(false);
        if (!cardClicked) {
            cardClicked = true;
            currentCard = card;

        } else {
            checkIfPairIsToRemove(card, currentCard);
            cardClicked = false;
            setAllCardsEnabled(false);
            new java.util.Timer().schedule(
                    new java.util.TimerTask() {
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    resetBoard();
                                    setAllCardsEnabled(true);
                                }
                            });
                        }
                    },
                    1000
            );
        }
    }

    private void resetBoard() {
        setImageOnClicsk();
        turnOverAllCards();
    }

    private void checkIfPairIsToRemove(Card card, Card card2) {
        if (card.cardSign == card2.cardSign) {
            new MemoryGameController().removeCardFromList(card);
            new MemoryGameController().removeCardFromList(card2);
            checkIfGameEnded();
        }
    }

    private void checkIfGameEnded() {
        if (MemoryGameDataHolder.getInstance().cards.size() == 0) {
            Log.d("koniec gry", "udało się zakończyć grę");
            countDownTimer.cancel();
            //TODO end game action
        }
    }

    private void setAllCardsEnabled(Boolean bool) {
        for (Card card : MemoryGameDataHolder.getInstance().cards) {
            int id = (new MemoryGameController().getIdByColRow(card.column, card.row));
            ImageView img = findViewById(id);
            img.setEnabled(bool);
        }
    }

    private void startTimer(int timeInSeconds) {
        currentTime = timeInSeconds;

        countDownTimer= new CountDownTimer(timeInSeconds * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                currentTime--;
                int min = currentTime/60;
                int seconds = currentTime%60;
                updateTimer(min,seconds);
            }

            @Override
            public void onFinish() {
                //TODO end game nie udało ci się
                Log.d("koniec gry ","nie udało sie wygrać");

            }
        };
        countDownTimer.start();
    }

    private void updateTimer(int min,int sec) {
        String seconds = String.valueOf(sec);
        TextView time = findViewById(R.id.memory_game_time_text);
        if(sec<10){
            seconds = "0"+seconds;
        }
        time.setText(min+":"+seconds);
    }

}