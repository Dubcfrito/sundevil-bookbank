package com.example.demo1;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SellConformation extends Application {
    @Override
    public void start(Stage primaryStage) {
        try {
            // Load the FXML file
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/com/example/demo1/hello-view.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 800, 600);

            // Set the stage properties
            primaryStage.setTitle("Sun Devil Book Bank");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("FXML file not found. Check the path and file name.");
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}



