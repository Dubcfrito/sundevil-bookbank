package groupone.sundevilbookbank.utils;

import java.io.IOException;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class PageLoader {
    private static Stage primaryStage;

    // Initialize the loader with the primary stage
    public static void init(Stage stage) {
        primaryStage = stage;
    }

    // Load a new page into the primary stage
    public static void loadPage(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(PageLoader.class.getResource("/groupone/sundevilbookbank/views/" + fxmlFile));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
