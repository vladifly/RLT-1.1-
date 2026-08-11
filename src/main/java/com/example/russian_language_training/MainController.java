package com.example.russian_language_training;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;

// controller for the main scene
public class MainController {
    // method to enter the "about the app" scene
    @FXML void aboutApp(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showScene(currentStage, "aboutApp.fxml");
    }

    // method to transition to the stress scene and update statistics
    @FXML public void stressSceneRun(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showScene(currentStage, "stressScene.fxml");
    }

    // method to quit the application
    @FXML void quit(ActionEvent event) {
        Platform.exit();
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
