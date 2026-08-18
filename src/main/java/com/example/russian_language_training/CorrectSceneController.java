package com.example.russian_language_training;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;

public class CorrectSceneController {
    @FXML
    private Label textLabel;

    @FXML
    void initialize() {
        // 500 ms timer
        PauseTransition pause = new PauseTransition(Duration.millis(500));
        pause.setOnFinished(e -> {
            try {
                // Returning to the stressScene
                Stage stage = (Stage) textLabel.getScene().getWindow();
                showScene(stage, "/com/example/russian_language_training/stressScene.fxml");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        });
        pause.play();
    }

    // a method to transition to any scene
    public void showScene(Stage currentStage, String fxmlName) throws IOException {
        URL resource = getClass().getResource(fxmlName);

        if (resource == null) {
            System.err.println("Файл не найден: " + fxmlName);
            return;
        }

        System.out.println("showing scene");

        Parent root = FXMLLoader.load(resource);
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
}