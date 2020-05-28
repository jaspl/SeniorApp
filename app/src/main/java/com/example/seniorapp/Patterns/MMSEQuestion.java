package com.example.seniorapp.Patterns;

public class MMSEQuestion {
    int count;
    int maxPointAmount;
    String exerciseContent;

    public MMSEQuestion(int count, int maxPointAmount, String exerciseContent) {
        this.count = count;
        this.maxPointAmount = maxPointAmount;
        this.exerciseContent = exerciseContent;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getMaxPointAmount() {
        return maxPointAmount;
    }

    public void setMaxPointAmount(int maxPointAmount) {
        this.maxPointAmount = maxPointAmount;
    }

    public String getExerciseContent() {
        return exerciseContent;
    }

    public void setExerciseContent(String exerciseContent) {
        this.exerciseContent = exerciseContent;
    }
}
