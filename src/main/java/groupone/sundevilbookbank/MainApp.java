package groupone.sundevilbookbank;

import groupone.sundevilbookbank.controllers.AccountPageController;
import groupone.sundevilbookbank.models.Account;
import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.models.Order;
import groupone.sundevilbookbank.services.Base;
import groupone.sundevilbookbank.utils.GlobalData;
import groupone.sundevilbookbank.utils.PageLoader;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class MainApp extends Application {

    private static final ArrayList<String> pages = new ArrayList<>();

    private static int currentPageIndex = 8;

    @Override
    public void start(Stage primaryStage) {
        // Test Account
        GlobalData.setCurrentAccount(Base.getAccount("Jack", "Klebonis"));
        GlobalData.setCurrentOrder(new Order(Base.getAccountID("Jack", "Password")));
        // add books to test order
        GlobalData.addBookToOrder(new Book(-1, -1, "Book Title", "Jack", "Book", "Book", "1234", "Like New", "Book", 22, "Status", "Images"));
        GlobalData.addBookToOrder(new Book(-1, -1, "Book Title", "Jack", "Book", "Book", "1234", "Like New", "Book", 22, "Status", "Images"));
        GlobalData.addBookToOrder(new Book(-1, -1, "Book Title", "Jack", "Book", "Book", "1234", "Like New", "Book", 22, "Status", "Images"));
        GlobalData.addBookToOrder(new Book(-1, -1, "Book Title", "Jack", "Book", "Book", "1234", "Like New", "Book", 22, "Status", "Images"));

        // Add pages to the list with index          \/
        pages.add("LoginPage.fxml");            // 0
        pages.add("SignUpPage.fxml");           // 1
        pages.add("ForgotPasswordPage.fxml");   // 2
        pages.add("LandingPage.fxml");          // 3
        pages.add("BuyPage.fxml");              // 4
        pages.add("AccountPage.fxml");          // 5
        pages.add("ReviewOrder.fxml");          // 6
        pages.add("PurchaseConfirmation.fxml"); // 7
        pages.add("ShoppingCart.fxml");         // 8
        

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
            System.out.println(
                "\n==========================================================================\n" +
                "|| PAGE LOADED --- INDEX = " + pageIndex + " --- WITH DATA:\n" +
                "|| ACCOUNT INFO  = ID:" + (GlobalData.getCurrentAccount() != null ? GlobalData.getCurrentAccount().getAccountID() : "null") + ", NAME: " + (GlobalData.getCurrentAccount() != null ? GlobalData.getCurrentAccount().getUsername() : "null") + "\n" +
                "|| ORDER INFO    = ID:" + (GlobalData.getCurrentOrder() != null ? GlobalData.getCurrentOrder().getOrderNumber() : "null") + ", CONTENTSIZE: " + (GlobalData.getCurrentOrder() != null ? GlobalData.getCurrentOrder().getOrderContent().size() : "null") + " BOOKS\n" +
                "==========================================================================\n"
            );
        } else {
            System.out.println("Invalid page index" + pageIndex);
        }   
    }

    public static void main(String[] args) {
        launch(args);
    }
}
