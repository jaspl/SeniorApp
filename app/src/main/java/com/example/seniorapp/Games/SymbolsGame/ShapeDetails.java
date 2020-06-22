package com.example.seniorapp.Games.SymbolsGame;

import com.example.seniorapp.R;

public class ShapeDetails {
    int shape;
    int color;
    int column;
    int row;
    int toRemove;

    public ShapeDetails(int shape, int color, int column, int row, int toRemove) {
        this.shape = shape;
        this.color = color;
        this.column = column;
        this.row = row;
        this.toRemove = toRemove;
    }

    public int getShape() {
        return shape;
    }

    public void setShape(int shape) {
        this.shape = shape;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getToRemove() {
        return toRemove;
    }

    public void setToRemove(int toRemove) {
        this.toRemove = toRemove;
    }
}
