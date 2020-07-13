package com.example.seniorapp.Models;

public class PairModel {
    private int numberOne;
    private int numberTwo;

    public PairModel() {
    }

    public PairModel(Integer numberOne, Integer numberTwo) {
        this.numberOne = numberOne;
        this.numberTwo = numberTwo;
    }

    public int getNumberTwo() {
        return numberTwo;
    }

    public void setNumberTwo(int numberTwo) {
        this.numberTwo = numberTwo;
    }

    public int getNumberOne() {
        return numberOne;
    }

    public void setNumberOne(int numberOne) {
        this.numberOne = numberOne;
    }

    @Override
    public boolean equals(Object object) {
        if (object == null) {
            return false;
        }

        if (!PairModel.class.isAssignableFrom(object.getClass())) {
            return false;
        }

        PairModel other = (PairModel) object;
        if (this.numberOne == other.numberOne && this.numberTwo == other.numberTwo) {
            return true;
        } else if (this.numberOne == other.numberTwo && this.numberTwo == other.numberOne) {
            return true;
        }
        return false;
    }
}