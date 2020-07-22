package com.example.seniorapp.Games.HangManGame;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.content.res.Resources;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup.LayoutParams;
import android.widget.LinearLayout;

import com.example.seniorapp.API.Api;
import com.example.seniorapp.API.ApiClass;
import com.example.seniorapp.GameSelectorActivity;
import com.example.seniorapp.Models.GamesObject;
import com.example.seniorapp.NoSerwerConnectionErrorDialog;
import com.example.seniorapp.ProgressDialogClass;
import com.example.seniorapp.R;
import com.example.seniorapp.SharedPrefs;
import com.example.seniorapp.Utils.Category;
import com.example.seniorapp.Utils.NameGame;
import com.example.seniorapp.Utils.StatusGame;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import androidx.appcompat.app.AppCompatActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HangManActivity extends AppCompatActivity {
    private ImageView[] bodyParts;
    private int numParts=10;
    private int currPart;
    private int numChars;
    private int numCorr;
    private String[] words;
    private Random rand;
    private String currWord;
    private LinearLayout wordLayout;
    private TextView[] charViews;
    private GridView letters;
    private LetterAdapter ltrAdapt;
    private String category;

    long startTime = System.currentTimeMillis();
    long endTime = 0;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hang_man);
        setEndGameButton();
        setSkipGameButton();
        category = "";
        rand = new Random();
        Resources res = getResources();
        words = res.getStringArray(setLvl());
        currWord = "";
        wordLayout = (LinearLayout)findViewById(R.id.word);
        TextView textViewCategory = findViewById(R.id.category);
        textViewCategory.setText(String.format("%s %s", res.getString(R.string.category), Category.valueOf(category)));
        letters = (GridView)findViewById(R.id.letters);
        bodyParts = new ImageView[numParts];
        bodyParts[0] = (ImageView)findViewById(R.id.construction1);
        bodyParts[1] = (ImageView)findViewById(R.id.construction2);
        bodyParts[2] = (ImageView)findViewById(R.id.construction3);
        bodyParts[3] = (ImageView)findViewById(R.id.construction4);
        bodyParts[4] = (ImageView)findViewById(R.id.head);
        bodyParts[5] = (ImageView)findViewById(R.id.body);
        bodyParts[6] = (ImageView)findViewById(R.id.arm1);
        bodyParts[7] = (ImageView)findViewById(R.id.arm2);
        bodyParts[8] = (ImageView)findViewById(R.id.leg1);
        bodyParts[9] = (ImageView)findViewById(R.id.leg2);
        playGame();
        setStartTime();
    }

    private int getLvl() {
        return new SharedPrefs(this).getlvlInInt();
    }

    private int setLvl() {
        Resources res = getResources();
        switch (getLvl()) {
            case 0:
                Integer[] wordLists = {R.array.KOLORY, R.array.ZWERZĘTA, R.array.RODZINA, R.array.OWOCE, R.array.WARZYWA};
                int list = rand.nextInt(wordLists.length);
                category = res.getResourceEntryName(wordLists[list]);
                return wordLists[list];
            case 1:
                Integer[] wordLists2 = {R.array.DOM_MIESZKANIE, R.array.EMOCJE, R.array.CZŁOWIEK, R.array.W_MIESZKANIU, R.array.RZECZY_OSOBISTE};
                int list2 = rand.nextInt(wordLists2.length);
                category = res.getResourceEntryName(wordLists2[list2]);
                return wordLists2[list2];
            case 2:
                Integer[] wordLists3 = {R.array.POGODA_I_KLIMAT, R.array.ZAWODY, R.array.ZAKUPY_I_USŁUGI, R.array.KULTURA, R.array.SPORT};
                int list3 = rand.nextInt(wordLists3.length);
                category = res.getResourceEntryName(wordLists3[list3]);
                return wordLists3[list3];
            case 3:
                Integer[] wordLists4 = {R.array.ZDROWIE, R.array.PAŃSTWO_I_SPOŁECZEŃSTWO, R.array.BUDOWNICTWO_I_ARCHITEKTURA, R.array.EKOLOGIA, R.array.CHARAKTER};
                int list4 = rand.nextInt(wordLists4.length);
                category = res.getResourceEntryName(wordLists4[list4]);
                return wordLists4[list4];
            case 4:
                Integer[] wordLists5 = {R.array.PODRÓŻOWANIE_I_TURYSTYKA, R.array.NAUKA_I_TECHNIKA, R.array.MOTORYZACJA, R.array.KOMUNIKACJA, R.array.CZAS_WOLNY};
                int list5 = rand.nextInt(wordLists5.length);
                category = res.getResourceEntryName(wordLists5[list5]);
                return wordLists5[list5];
        }
        Integer[] wordLists0 = {R.array.KOLORY, R.array.ZWERZĘTA, R.array.RODZINA, R.array.OWOCE, R.array.WARZYWA};
        int list0 = rand.nextInt(wordLists0.length);
        category = String.valueOf(list0);
        return wordLists0[list0];
    }

    private void playGame() {
        String newWord = words[rand.nextInt(words.length)];
        while(newWord.equals(currWord)) newWord = words[rand.nextInt(words.length)];
        currWord = newWord;
        charViews = new TextView[currWord.length()];
        wordLayout.removeAllViews();
        for (int c = 0; c < currWord.length(); c++) {

            charViews[c] = new TextView(this);
            charViews[c].setText(""+currWord.charAt(c));

            charViews[c].setLayoutParams(new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
            charViews[c].setGravity(Gravity.CENTER);
            charViews[c].setTextColor(Color.WHITE);
            charViews[c].setTextSize(30);
            charViews[c].setBackgroundResource(R.drawable.letter_bg);
            //add to layout
            wordLayout.addView(charViews[c]);
        }
        ltrAdapt=new LetterAdapter(this);
        letters.setAdapter(ltrAdapt);
        currPart=0;
        numChars=currWord.length();
        numCorr=0;
        for(int p = 0; p < numParts; p++) {
            bodyParts[p].setVisibility(View.INVISIBLE);
        }
    }

    public void letterPressed(View view) {
        String ltr=((TextView)view).getText().toString();
        char letterChar = ltr.charAt(0);
        view.setEnabled(false);
        view.setBackgroundResource(R.drawable.letter_down);
        view.setVisibility(View.INVISIBLE);
        boolean correct = false;
        for(int k = 0; k < currWord.length(); k++) {
            if(currWord.charAt(k)==letterChar){
                correct = true;
                numCorr++;
                charViews[k].setTextColor(Color.BLACK);
            }
        }
        if (correct) {
            if (numCorr == numChars) {
                disableBtns();
                setEndTime();
                float reactionTimeInSeconds = (float) (endTime - startTime) / 1000;
                sendResultToDatabase(StatusGame.SUCCESSFUL, reactionTimeInSeconds);
                // Display Alert Dialog
                AlertDialog.Builder winBuild = new AlertDialog.Builder(this);
                winBuild.setTitle("JEJ");
                winBuild.setMessage("Wygrałeś!\n\nOdpowiedź to:\n\n"+currWord);
                winBuild.setPositiveButton("Zagraj ponownie",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HangManActivity.this.playGame();
                            }});

                winBuild.setNegativeButton("Zakończ",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                HangManActivity.this.finish();
                            }});

                winBuild.show();
            }
        } else if (currPart < numParts-1) {
            //some guesses left
            bodyParts[currPart].setVisibility(View.VISIBLE);
            currPart++;
        } else{
            //user has lost
            bodyParts[currPart].setVisibility(View.VISIBLE);
            disableBtns();
            setEndTime();
            float reactionTimeInSeconds = (float) (endTime - startTime) / 1000;
            sendResultToDatabase(StatusGame.FAILED, reactionTimeInSeconds);
            // Display Alert Dialog
            AlertDialog.Builder loseBuild = new AlertDialog.Builder(this);
            loseBuild.setTitle("UPS");
            loseBuild.setMessage("Przegrałeś!\n\nPoprawna odpowiedź to:\n\n"+currWord);
            loseBuild.setPositiveButton("Zagraj ponownie",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HangManActivity.this.playGame();
                        }});

            loseBuild.setNegativeButton("Zakończ",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int id) {
                            HangManActivity.this.finish();
                        }});

            loseBuild.show();
        }
    }

    public void disableBtns() {
        int numLetters = letters.getChildCount();
        for (int l = 0; l < numLetters; l++) {
            letters.getChildAt(l).setEnabled(false);
        }
    }

    private void setEndGameButton() {
        getEndGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HangManActivity.this, GameSelectorActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setSkipGameButton() {
        getSkipGameButton().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setEndTime();
                float reactionTimeInSeconds = (float) (endTime - startTime) / 1000;
                sendResultToDatabase(StatusGame.FAILED, reactionTimeInSeconds);

                Intent intent = new Intent(HangManActivity.this, HangManActivity.class);
                startActivity(intent);
            }
        });
    }

    public FloatingActionButton getEndGameButton() {
        return findViewById(R.id.end_game_floatig_buton);
    }

    public Button getSkipGameButton() {
        return findViewById(R.id.skip_game_floatig_buton);
    }

    private void setStartTime() {
        startTime = getTime();
    }

    private void setEndTime() {
        endTime = getTime();
    }

    private long getTime() {
        return System.currentTimeMillis();
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
                NameGame.HANGMAN));
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