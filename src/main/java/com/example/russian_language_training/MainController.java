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
import java.util.Objects;

// controller for main scene
public class MainController {
    // method to enter the about the app scene
    @FXML void aboutApp(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showXScene(currentStage, "aboutApp.fxml");
    }

    // method to transition to the emp scene and updating statistics
    @FXML public void empSceneRun(ActionEvent event) throws IOException {
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        showXScene(currentStage, "empScene.fxml");
    }

    // method to quit the app
    @FXML void quit(ActionEvent event) {
        Platform.exit();
    }

    // method to transition to any scene
    public void showXScene(Stage currentStage, String fxmlName) throws IOException {
        Parent root = FXMLLoader.load(Objects.requireNonNull(getClass().getResource(fxmlName)));
        Scene scene = new Scene(root);
        currentStage.setScene(scene);
        currentStage.show();
    }
}
