package com.example.russian_language_training;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;

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

// this class is controller of emphasis scene
public class EmpSceneController {
    @FXML private Button btnEmp1;
    @FXML private Button btnEmp2;
    @FXML private Label wordLabel;
    @FXML private Label allEmpLabel;
    @FXML private Label cEmpLabel;
    @FXML private Label empPercent;
    @FXML private Label uncEmpLabel;

    @FXML private ResourceBundle resources;
    @FXML private URL location;

    private Stage stage;
    private Scene scene;
    private Parent root;
    private final Random random = new Random();

    // an array is necessary to store words which already have encountered
    // we will use it to prevention its recurrence
    public static int[] last = new int[25];
    // this variable will store a position in last array
    // we will use it like last[lasts]
    public static int lastPosition = 0;

    // variable to store correct button
    int trueBtn;
    public static final StatsClass empStats = new StatsClass();

    @FXML void initialize() {
        System.out.println("Checking button load: " + (btnEmp1 != null ? "OK" : "NULL"));

        // setting statistics to labels
        if (cEmpLabel != null) {
            cEmpLabel.setText(String.valueOf(empStats.correctAns));
        }
        if (uncEmpLabel != null) {
            uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
        }
        if (empPercent != null) {
            empPercent.setText(String.valueOf(empStats.ratio));
            // setting the color of statistics by its value
            if (empStats.ratio > 50f && empStats.ratio < 60f || empStats.ratio == 50f) {
                empPercent.setTextFill(Color.web("#14c317"));
            } else if (empStats.ratio < 30f || empStats.ratio == 30f) {
                empPercent.setTextFill(Color.web("#ff0000"));
            } else if (empStats.ratio > 60f && empStats.ratio < 70f || empStats.ratio == 60f) {
                empPercent.setTextFill(Color.web("#00aaff"));
            } else if (empStats.ratio > 70f || empStats.ratio == 70f) {
                empPercent.setTextFill(Color.web("#8800ff"));
            } else if (empStats.ratio < 50f && empStats.ratio > 40f || empStats.ratio == 40f) {
                empPercent.setTextFill(Color.web("#ffffff"));
            }
        }
        if (allEmpLabel != null) {
            allEmpLabel.setText(String.valueOf(empStats.allAns));
        }
        // checking for labels are null
        if (btnEmp1 != null && btnEmp2 != null && wordLabel != null) {
            randomEpm();
        }
    }

    // method to check word's repetitions
    public boolean checkRepetitions(int resRandom) {
        // this variable will store the result
        boolean res = true;

        // if the word now was in the last 25 times
        for (int el : last) {
            // if the word now was repeated then we return false and break the cycle
            if (el == resRandom) {
                res = false;
                break;
            }
        }

        return res;
    }

    // method to get the random word
    public void randomEpm(){
        if (btnEmp1 == null || btnEmp2 == null || wordLabel == null) {
            System.err.println("Error: elements were not loaded.");
            return;
        }
        // generation the correct button (one of two)
        trueBtn = ThreadLocalRandom.current().nextInt(1, 3);

        // generation the random word
        boolean isAvailable = false;
        int resRandom = -1;
        // checking if a word is repeated
        while (!isAvailable) {
            resRandom = ThreadLocalRandom.current().nextInt(1, 100);
            isAvailable = checkRepetitions(resRandom);
            System.out.println("repeat");
        }
        System.out.println("Word element = " + resRandom);
        System.out.println("Correct button = " + trueBtn);

        // if our position at end of the array then we reset it
        if (lastPosition >= 25) {lastPosition = 0;}

        // setting text to labels and buttons
        wordLabel.setText(EmpWords.words[resRandom-1][0]);
        btnEmp1.setText(trueBtn == 1 ? EmpWords.words[resRandom-1][1] : EmpWords.words[resRandom-1][2]);
        btnEmp2.setText(trueBtn == 2 ? EmpWords.words[resRandom-1][1] : EmpWords.words[resRandom-1][2]);

        // we fill the word is now to the array by position
        last[lastPosition] = resRandom;
        // we increase position
        lastPosition += 1;
    }

    // method to quit the app
    @FXML void quit(ActionEvent event) {
        Platform.exit();
    }

    // method to enter the about the app scene
    @FXML void aboutApp(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showXScene(currentStage, "aboutApp.fxml");
    }

    // Pressing to two buttons in emp scene with checking correct answer, transition from emp scene to correctEmpScene or
    // incorrectEmpScene. Also there are changing and updating variables with statistics
    @FXML void btnEmp1Clck(ActionEvent event) throws IOException {
        // checking the correct answer and changing statistics depending on the result
        if (trueBtn == 1) {
            System.out.println("Correct");
            empStats.changeStats(1, "correctAns");
            empStats.changeStats(1, "allAns");
        } else {
            System.out.println("Uncorrect");
            empStats.changeStats(1, "uncorrectAns");
            empStats.changeStats(1, "allAns");
        }

        // updating ratio
        empStats.updateStats();

        // changing scene to correct/incorrect
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String resultScene = trueBtn == 1 ? "correctEmpScene.fxml" : "uncorrectEmpScene.fxml";
        showXScene(currentStage, resultScene);

        new Thread(() -> {
            try {
                // we wait while correct/incorrect scene is showing
                Thread.sleep(500);

                Platform.runLater(() -> {
                    try {
                        Parent exerciseRoot = FXMLLoader.load(getClass().getResource("EmpScene.fxml"));

                        // we update labels
                        cEmpLabel = (Label) exerciseRoot.lookup("#cEmpLabel");
                        uncEmpLabel = (Label) exerciseRoot.lookup("#uncEmpLabel");
                        empPercent = (Label) exerciseRoot.lookup("#empPercent");

                        // and we bring statistics to it
                        if (cEmpLabel != null) cEmpLabel.setText(String.valueOf(empStats.correctAns));
                        if (uncEmpLabel != null) uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
                        if (empPercent != null) empPercent.setText(String.valueOf(empStats.ratio));

                        // and we change scene back to the emphasis
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
        empStats.updateStats();
    }

    @FXML void btnEmp2Clck(ActionEvent event) throws IOException {
        // checking the correct answer and changing statistics depending on the result
        if (trueBtn == 2) {
            System.out.println("Correct");
            empStats.changeStats(1, "correctAns");
            empStats.changeStats(1, "allAns");
        } else {
            System.out.println("Uncorrect");
            empStats.changeStats(1, "uncorrectAns");
            empStats.changeStats(1, "allAns");
        }
        System.out.printf("correct : %d, uncorrect : %d, allAns : %d%n", empStats.correctAns, empStats.uncorrectAns, empStats.allAns);

        // we update stats
        empStats.updateStats();

        // changing scene to correct/incorrect
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        String resultScene = trueBtn == 2 ? "correctEmpScene.fxml" : "uncorrectEmpScene.fxml";
        showXScene(currentStage, resultScene);

        new Thread(() -> {
            try {
                // we wait while correct/incorrect scene is showing
                Thread.sleep(500);

                Platform.runLater(() -> {
                    try {
                        Parent exerciseRoot = FXMLLoader.load(getClass().getResource("EmpScene.fxml"));

                        // we update labels
                        cEmpLabel = (Label) exerciseRoot.lookup("#cEmpLabel");
                        uncEmpLabel = (Label) exerciseRoot.lookup("#uncEmpLabel");
                        empPercent = (Label) exerciseRoot.lookup("#empPercent");

                        // and we bring statistics to it
                        if (cEmpLabel != null) cEmpLabel.setText(String.valueOf(empStats.correctAns));
                        if (uncEmpLabel != null) uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
                        if (empPercent != null) empPercent.setText(String.valueOf(empStats.ratio));

                        // and we change scene back to the emphasis
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
        empStats.updateStats();
    }

    // method for return to the menu
    @FXML
    public void backToMenu(ActionEvent event) throws IOException {
        Stage nowStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showXScene(nowStage, "russianScene.fxml");
    }

    // method to transition to the emp scene and updating statistics
    @FXML public void empSceneRun(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("EmpScene.fxml")));
        currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        currentStage.setScene(scene);
        if (root != null) {
            // ищем Label по их fx:id в загруженной сцене
            cEmpLabel = (Label) root.lookup("#cEmpLabel");
            uncEmpLabel = (Label) root.lookup("#uncEmpLabel");
            empPercent = (Label) root.lookup("#empPercent");

            if (cEmpLabel != null && uncEmpLabel != null && empPercent != null) {
                cEmpLabel.setText(String.valueOf(empStats.correctAns));
                uncEmpLabel.setText(String.valueOf(empStats.uncorrectAns));
                empPercent.setText(String.valueOf(empStats.ratio));
            }
        }
        currentStage.show();
    }

    // method to transition to any scene
    public void showXScene(Stage currentStage, String fxmlName) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
}