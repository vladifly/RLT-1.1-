package com.example.russian_language_training;

public class StatsClass {
    // variables to store statistics
    public int correctAns = 0;
    public int incorrectAns = 0;
    public float ratio = 0f;
    public int allAns = 0;

    // a method to change correct answers, incorrect answers, or total answers
    public void changeStats(int value, String place) {
        if (place.equals("correctAns")) {
            correctAns += value;
        } else if (place.equals("incorrectAns")) {
            incorrectAns += value;
        } else if (place.equals("allAns")) {
            allAns += value;
        }
    }

    // a method to calculate and round up to tenth of a percent of correct answers
    public void updateRatio() {
        float total = correctAns + incorrectAns;
        if (total > 0) {
            ratio = (correctAns / total) * 100;
        } else {
            ratio = 0;
        }

        ratio = Math.round(ratio * 10) / 10.0f;
    }
}