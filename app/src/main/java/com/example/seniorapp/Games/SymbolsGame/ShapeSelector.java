package com.example.seniorapp.Games.SymbolsGame;

import com.example.seniorapp.R;

public class ShapeSelector {
    public int shapeSelector(int color, int shape) {
        if (color == 0 && shape == 0) {
            return R.drawable.red_rectangle;
        } else if (color == 1 && shape == 0) {
            return R.drawable.green_rectangle;
        } else if (color == 2 && shape == 0) {
            return R.drawable.blue_rectangle;
        } else if (color == 3 && shape == 0) {
            return R.drawable.black_rectangle;
        } else if (color == 0 && shape == 1) {
            return R.drawable.red_circle;
        } else if (color == 1 && shape == 1) {
            return R.drawable.green_circle;
        } else if (color == 2 && shape == 1) {
            return R.drawable.blue_circle;
        } else if (color == 3 && shape == 1) {
            return R.drawable.black_circle;
        } else if (color == 0 && shape == 2) {
            return R.drawable.red_triangle;
        } else if (color == 1 && shape == 2) {
            return R.drawable.green_triangle;
        } else if (color == 2 && shape == 2) {
            return R.drawable.blue_triangle;
        } else if (color == 3 && shape == 2) {
            return R.drawable.black_triangle;
        } else if (color == 0 && shape == 3) {
            return R.drawable.red_pentagon;
        } else if (color == 1 && shape == 3) {
            return R.drawable.green_pentagon;
        } else if (color == 2 && shape == 3) {
            return R.drawable.blue_pentagon;
        } else
            return R.drawable.black_pentagon;
    }
}
