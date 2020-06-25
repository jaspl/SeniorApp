package com.example.seniorapp.Games.MemoryGame;

import android.graphics.drawable.Drawable;

import com.example.seniorapp.R;

public class MemoryGamePictureSeloctor {
    public int selector(int pictureId) {
        if (pictureId == 0) {
            return R.drawable.black_pentagon;
        } else if (pictureId == 1) {
            return R.drawable.red_triangle;
        }else if (pictureId == 2) {
            return R.drawable.green_rectangle;
        }else if (pictureId == 3) {
            return R.drawable.blue_circle;
        }else if (pictureId == 4) {
            return R.drawable.red_pentagon;
        }else if (pictureId == 5) {
            return R.drawable.blue_triangle;
        }else if (pictureId == 6) {
            return R.drawable.black_rectangle;
        }else {
            return R.drawable.green_circle;
        }
    }
}
