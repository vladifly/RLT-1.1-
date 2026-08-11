package com.example.russian_language_training;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

import com.example.russian_language_training.StatsClass;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

// this class is the controller of the stress scene
public class StressSceneController {
    @FXML
    private Button btnEmp1;
    @FXML
    private Button btnEmp2;
    @FXML
    private Label wordLabel;
    @FXML
    private Label allAnswersLabel;
    @FXML
    private Label correctAnswersLabel;
    @FXML
    private Label percentLabel;
    @FXML
    private Label incorrectAnswersLabel;

    @FXML
    private ResourceBundle resources;
    @FXML
    private URL location;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Random random = new Random();

    // an array is needed to store words that have already appeared
    // we will use it to prevent their recurrence
    public static int[] last = new int[25];
    // this variable will store a position in the last array
    // we will use it like last[lastPosition]
    public static int lastPosition = 0;

    // variable to store the correct button
    int trueBtn;
    public static final StatsClass stressStats = new StatsClass();

    @FXML
    void initialize() {
        System.out.println("Checking button load: " + (btnEmp1 != null ? "OK" : "NULL"));

        // setting statistics to labels
        if (correctAnswersLabel != null) {
            correctAnswersLabel.setText(String.valueOf(stressStats.correctAns));
        }
        if (incorrectAnswersLabel != null) {
            incorrectAnswersLabel.setText(String.valueOf(stressStats.incorrectAns));
        }
        if (percentLabel != null) {
            percentLabel.setText(String.valueOf(stressStats.ratio));
            // setting the color of statistics based on its value
            if (stressStats.ratio > 50f && stressStats.ratio < 60f || stressStats.ratio == 50f) {
                percentLabel.setTextFill(Color.web("#14c317"));
            } else if (stressStats.ratio < 30f || stressStats.ratio == 30f) {
                percentLabel.setTextFill(Color.web("#ff0000"));
            } else if (stressStats.ratio > 60f && stressStats.ratio < 70f || stressStats.ratio == 60f) {
                percentLabel.setTextFill(Color.web("#00aaff"));
            } else if (stressStats.ratio > 70f || stressStats.ratio == 70f) {
                percentLabel.setTextFill(Color.web("#8800ff"));
            } else if (stressStats.ratio < 50f && stressStats.ratio > 40f || stressStats.ratio == 40f) {
                percentLabel.setTextFill(Color.web("#ffffff"));
            }
        }
        if (allAnswersLabel != null) {
            allAnswersLabel.setText(String.valueOf(stressStats.allAns));
        }
        // checking if labels are null
        if (btnEmp1 != null && btnEmp2 != null && wordLabel != null) {
            randomEpm();
        }
    }

    // method to check word repetitions
    public boolean checkRepetitions(int resRandom) {
        // this variable will store the result
        boolean res = true;

        // if the current word was in the last 25 times
        for (int el : last) {
            // if the current word was repeated then we return false and break the loop
            if (el == resRandom) {
                res = false;
                break;
            }
        }

        return res;
    }

    // method to get a random word
    public void randomEpm() {
        if (btnEmp1 == null || btnEmp2 == null || wordLabel == null) {
            System.err.println("Error: elements were not loaded.");
            return;
        }
        // generate the correct button (one of two)
        trueBtn = ThreadLocalRandom.current().nextInt(1, 3);

        // generate a random word
        boolean isAvailable = false;
        int resRandom = -1;
        // check if the word is repeated
        while (!isAvailable) {
            resRandom = ThreadLocalRandom.current().nextInt(1, 100);
            isAvailable = checkRepetitions(resRandom);
            System.out.println("repeat");
        }
        System.out.println("Word element = " + resRandom);
        System.out.println("Correct button = " + trueBtn);

        // if our position is at the end of the array then we reset it
        if (lastPosition >= 25) {
            lastPosition = 0;
        }

        // set text to labels and buttons
        wordLabel.setText(StressWords.words[resRandom - 1][0]);
        btnEmp1.setText(trueBtn == 1 ? StressWords.words[resRandom - 1][1] : StressWords.words[resRandom - 1][2]);
        btnEmp2.setText(trueBtn == 2 ? StressWords.words[resRandom - 1][1] : StressWords.words[resRandom - 1][2]);

        // we fill the current word into the array at the current position
        last[lastPosition] = resRandom;
        // we increase the position
        lastPosition += 1;
    }

    // Pressing one of two buttons in the emp scene with checking the correct answer, transition from emp scene to correctScene or
    // incorrectScene. There are also changes and updates to variables with statistics
    @FXML
    void btnStress1Click(ActionEvent event) throws IOException {
        // checking the correct answer and changing statistics depending on the result
        if (trueBtn == 1) {
            System.out.println("Correct");
            stressStats.changeStats(1, "correctAns");
            stressStats.changeStats(1, "allAns");
        } else {
            System.out.println("Incorrect");
            stressStats.changeStats(1, "incorrectAns");
            stressStats.changeStats(1, "allAns");
        }

        // updating ratio
        stressStats.updateStats();

        // changing scene to correct/incorrect
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String resultScene = trueBtn == 1 ? "correctScene.fxml" : "incorrectScene.fxml";
        showScene(currentStage, resultScene);

        new Thread(() -> {
            try {
                // we wait while the correct/incorrect scene is showing
                Thread.sleep(500);

                Platform.runLater(() -> {
                    try {
                        Parent exerciseRoot = FXMLLoader.load(getClass().getResource("stressScene.fxml"));

                        // we update labels
                        correctAnswersLabel = (Label) exerciseRoot.lookup("#correctAnswersLabel");
                        incorrectAnswersLabel = (Label) exerciseRoot.lookup("#incorrectAnswersLabel");
                        percentLabel = (Label) exerciseRoot.lookup("#percentLabel");

                        // and we update statistics in them
                        if (correctAnswersLabel != null) correctAnswersLabel.setText(String.valueOf(stressStats.correctAns));
                        if (incorrectAnswersLabel != null) incorrectAnswersLabel.setText(String.valueOf(stressStats.incorrectAns));
                        if (percentLabel != null) percentLabel.setText(String.valueOf(stressStats.ratio));

                        // and we change the scene back to the stress scene
                        Scene exerciseScene = new Scene(exerciseRoot);
                        currentStage.setScene(exerciseScene);
                        currentStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        // we update stats again
        stressStats.updateStats();
    }

    @FXML
    void btnStress2Click(ActionEvent event) throws IOException {
        // checking the correct answer and changing statistics depending on the result
        if (trueBtn == 2) {
            System.out.println("Correct");
            stressStats.changeStats(1, "correctAns");
            stressStats.changeStats(1, "allAns");
        } else {
            System.out.println("Incorrect");
            stressStats.changeStats(1, "incorrectAns");
            stressStats.changeStats(1, "allAns");
        }
        System.out.printf("correct : %d, incorrect : %d, total : %d%n", stressStats.correctAns, stressStats.incorrectAns, stressStats.allAns);

        // we update stats
        stressStats.updateStats();

        // changing scene to correct/incorrect
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String resultScene = trueBtn == 2 ? "correctScene.fxml" : "incorrectScene.fxml";
        showScene(currentStage, resultScene);

        new Thread(() -> {
            try {
                // we wait while the correct/incorrect scene is showing
                Thread.sleep(500);

                Platform.runLater(() -> {
                    try {
                        Parent exerciseRoot = FXMLLoader.load(getClass().getResource("stressScene.fxml"));

                        // we update labels
                        correctAnswersLabel = (Label) exerciseRoot.lookup("#correctAnswersLabel");
                        incorrectAnswersLabel = (Label) exerciseRoot.lookup("#incorrectAnswersLabel");
                        percentLabel = (Label) exerciseRoot.lookup("#percentLabel");

                        // and we update statistics in them
                        if (correctAnswersLabel != null) correctAnswersLabel.setText(String.valueOf(stressStats.correctAns));
                        if (incorrectAnswersLabel != null) incorrectAnswersLabel.setText(String.valueOf(stressStats.incorrectAns));
                        if (percentLabel != null) percentLabel.setText(String.valueOf(stressStats.ratio));

                        // and we change the scene back to the stress scene
                        Scene exerciseScene = new Scene(exerciseRoot);
                        currentStage.setScene(exerciseScene);
                        currentStage.show();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }).start();
        // we update stats again
        stressStats.updateStats();
    }

    // method to return to the menu
    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        Stage nowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showScene(nowStage, "mainScene.fxml");
    }

    // method to transition to any scene
    public void showScene(Stage currentStage, String fxmlName) throws IOException {
        String path = "/com/example/russian_language_training/" + fxmlName;
        URL resource = getClass().getResource(path);

        if (resource == null) {
            System.err.println("Файл не найден: " + path);
            return;
        }

        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
}