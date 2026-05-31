package com.example.russian_language_training;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

// this class is necessary to start the program
public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        try {

            final Parent root = FXMLLoader.load(getClass().getResource("mainScene.fxml"));
            final Scene scene = new Scene(root);
            stage.setTitle("Russian Language Training");
            stage.setScene(scene);
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Images/icon.png")));
            stage.show();

        } catch(Exception e) {
            e.printStackTrace();
        }
    }



    public static void main(String[] args) {
        launch();
    }
}