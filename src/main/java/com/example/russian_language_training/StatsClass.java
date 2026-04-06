package com.example.russian_language_training;

public class StatsClass {
    public static int correctAns = 0; //     --
    public static int uncorrectAns = 0;//    переменные для хранения
    public static float ratio = 0f;//        внутриигровой статистики
    public static int allAns = 0;//          --

    public static void changeStats(int correctAns, int uncorrectAns, float ratio, int allAns) {
        StatsClass.correctAns = correctAns;
        StatsClass.uncorrectAns = uncorrectAns;
        StatsClass.ratio = ratio;
        StatsClass.allAns = allAns;
    }

    public static void changeStats(int value, String place) {
        if (place.equals("correctAns")) {
            StatsClass.correctAns += value;
        } else if (place.equals("uncorrectAns")) {
            StatsClass.uncorrectAns += value;
        } else if (place.equals("allAns")) {
            StatsClass.allAns += value;
        }
    }

    public void changeStats(float ratio) {
        StatsClass.ratio = ratio;
    }

    // метод для высчитывания процента правильных ответов и его округления до десятых
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