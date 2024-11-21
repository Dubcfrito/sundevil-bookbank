package groupone.sundevilbookbank;

import groupone.sundevilbookbank.controllers.AccountPageController;
import groupone.sundevilbookbank.models.Account;
import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.models.Order;
import groupone.sundevilbookbank.utils.PageLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp extends Application {

    private static final ArrayList<String> pages = new ArrayList<>();

    private static int currentPageIndex = 3;

    @Override
    public void start(Stage primaryStage) {
        // Add pages to the list
        pages.add("LoginPage.fxml");
        pages.add("SignUpPage.fxml");
        pages.add("ForgotPasswordPage.fxml");
        pages.add("LandingPage.fxml");
        pages.add("BuyPage.fxml");
        pages.add("AccountPage.fxml");
        pages.add("ReviewOrder.fxml");
        pages.add("PurchaseConfirmation.fxml");
        

        // Initialize PageLoader
        PageLoader.init(primaryStage);

        // Load AccountPage with Account data
        //PageLoader.loadPageWithData("AccountPage.fxml", AccountPageController::setCurrentAccount, testAccount);
        PageLoader.loadPage(pages.get(currentPageIndex));
    }

    public static void goToPage(int pageIndex) {
        if (pageIndex >= 0 && pageIndex < pages.size()) {
            currentPageIndex = pageIndex;
            PageLoader.loadPage(pages.get(currentPageIndex));

        } else {
            System.out.println("Invalid page index" + pageIndex);
        }   
    }

    public static void main(String[] args) {
        launch(args);
    }
}
