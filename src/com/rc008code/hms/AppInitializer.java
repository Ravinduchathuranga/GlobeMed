package com.rc008code.hms;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppInitializer extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setScene(new Scene(FXMLLoader.load(getClass().getResource("view/forms/LogInForm.fxml"))));
        primaryStage.setTitle("Hospital Management System");
        primaryStage.centerOnScreen();
        primaryStage.setResizable(false);
        primaryStage.show();
    }
}
