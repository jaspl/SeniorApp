package com.example.seniorapp.Games.HangManGame;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;

import com.example.seniorapp.R;

public class LetterAdapter extends BaseAdapter {
    private String[] letters;
    private LayoutInflater letterInf;

    public LetterAdapter(Context c) {
        letters = new String[35];
        for (int a = 0; a < letters.length; a++) {
            letters[a] = "" + (char) (a + 'A');
        }
        letters[26] = "" + (char) ('Ą');
        letters[27] = "" + (char) ('Ć');
        letters[28] = "" + (char) ('Ę');
        letters[29] = "" + (char) ('Ł');
        letters[30] = "" + (char) ('Ń');
        letters[31] = "" + (char) ('Ó');
        letters[32] = "" + (char) ('Ś');
        letters[33] = "" + (char) ('Ź');
        letters[34] = "" + (char) ('Ż');

        letterInf = LayoutInflater.from(c);
    }

    @Override
    public int getCount() {
        return letters.length;
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //create a button for the letter at this position in the alphabet
        Button letterBtn;
        if (convertView == null) {
            //inflate the button layout
            letterBtn = (Button)letterInf.inflate(R.layout.letter, parent, false);
        } else {
            letterBtn = (Button) convertView;
        }
        //set the text to this letter
        letterBtn.setText(letters[position]);
        return letterBtn;
    }
}
