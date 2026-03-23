module com.example.russian_language_training {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.russian_language_training to javafx.fxml;
    exports com.example.russian_language_training;
}