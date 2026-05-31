package com.example.russian_language_training;

public class StatsClass {
    // variables to store statistics
    public static int correctAns = 0;
    public static int uncorrectAns = 0;
    public static float ratio = 0f;
    public static int allAns = 0;

    // a method to change all stats
    public static void changeStats(int correctAns, int uncorrectAns, float ratio, int allAns) {
        StatsClass.correctAns = correctAns;
        StatsClass.uncorrectAns = uncorrectAns;
        StatsClass.ratio = ratio;
        StatsClass.allAns = allAns;
    }

    // a method to change correct answers, incorrect answers, or total answers
    public static void changeStats(int value, String place) {
        if (place.equals("correctAns")) {
            StatsClass.correctAns += value;
        } else if (place.equals("uncorrectAns")) {
            StatsClass.uncorrectAns += value;
        } else if (place.equals("allAns")) {
            StatsClass.allAns += value;
        }
    }

    // a method to set the ratio
    public void changeStats(float ratio) {
        StatsClass.ratio = ratio;
    }

    // a method to calculate and round up to tenth of a percent of correct answers
    public void updateStats() {
        float total = correctAns + uncorrectAns;
        if (total > 0) {
            ratio = (correctAns / total) * 100;
        } else {
            ratio = 0;
        }

        ratio = Math.round(ratio * 10) / 10.0f;
    }
}