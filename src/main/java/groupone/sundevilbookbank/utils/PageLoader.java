package groupone.sundevilbookbank.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class PageLoader {

    private static Stage primaryStage;

    public static void init(Stage stage) {
        primaryStage = stage;
    }

    public static void loadPage(String fxml) {
        try {
            FXMLLoader loader = new FXMLLoader(PageLoader.class.getResource("/groupone/sundevilbookbank/views/" + fxml));
            Parent root = loader.load();
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Updated to accept two different types for controller and data
    public static <C, D> void loadPageWithData(String fxml, DataInjector<C, D> injector, D data) {
        try {
            FXMLLoader loader = new FXMLLoader(PageLoader.class.getResource("/groupone/sundevilbookbank/views/" + fxml));
            Parent root = loader.load();
            
            C controller = loader.getController(); // Correctly typed controller
            injector.injectData(controller, data);
            
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public interface DataInjector<C, D> {
        void injectData(C controller, D data);
    }
}
