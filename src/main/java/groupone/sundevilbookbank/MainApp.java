package groupone.sundevilbookbank;

import java.util.ArrayList;

import groupone.sundevilbookbank.utils.PageLoader;
import javafx.application.Application;
import javafx.stage.Stage;

public class MainApp extends Application {

    private static final ArrayList<String> pages = new ArrayList<>();
    private static int currentPageIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        // Initialize the pages list
        pages.add("AccountPage.fxml");
        pages.add("LoginPage.fxml");
        
        // Set up PageLoader
        PageLoader.init(primaryStage);
        PageLoader.loadPage(pages.get(currentPageIndex));
    }

    public static void nextPage() {
        currentPageIndex = (currentPageIndex + 1) % pages.size();
        PageLoader.loadPage(pages.get(currentPageIndex));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
