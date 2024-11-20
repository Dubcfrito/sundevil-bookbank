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

    private static int currentPageIndex = 0;

    @Override
    public void start(Stage primaryStage) {
        // Create dummy Account
        Account testAccount = new Account(1, "john_doe", "password123", "john@example.com");

        // Create dummy Orders with Books
        Order order1 = new Order(1001, "seller1", "buyer1", "2024-11-15", "Completed", "$15", "2024-11-14");
        order1.addBook(new Book(1, 2, "Introduction to Java", "John Smith", "Programming", "Java Basics", "123456789", "Good", "Learn Java", "$15", "Sold", "/groupone/sundevilbookbank/images/default_book.png"));

        Order order2 = new Order(1002, "seller2", "buyer2", "2024-11-10", "Pending", "$20", "2024-11-09");
        order2.addBook(new Book(2, 2, "Data Structures and Algorithms", "Jane Doe", "Computer Science", "Data Structures", "987654321", "Like New", "Advanced topics in data structures", "$20", "Pending", "/groupone/sundevilbookbank/images/default_book.png"));

        // Add orders to account
        testAccount.addOrder(order1);
        testAccount.addOrder(order2);

        // Add pages to the list
        pages.add("LoginPage.fxml");

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

    public static void nextPage() {
        currentPageIndex = (currentPageIndex + 1) % pages.size();
        PageLoader.loadPage(pages.get(currentPageIndex));
    }

    public static void main(String[] args) {
        launch(args);
    }
}
