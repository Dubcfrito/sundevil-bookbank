package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.models.Account;
import groupone.sundevilbookbank.models.Order;
import groupone.sundevilbookbank.utils.GlobalData;
import groupone.sundevilbookbank.models.Book;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.Node;

import java.io.IOException;

public class AccountPageController {

    private Account currentAccount; // Account object to hold current user data

    @FXML
    private ImageView profilePicture;

    @FXML
    private VBox transactionList;

    @FXML
    private Button logoutButton;

    @FXML
    public void initialize() {
        // Set default profile picture
        currentAccount = GlobalData.getCurrentAccount();

        profilePicture.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/default_profile.png")));

        System.out.println("Initialization complete. Transaction List and Logout button ready.");

        logoutButton.setOnAction(event -> handleLogout());
    }

    @FXML
    private void handleLogout() {
        System.out.println("Logout button clicked");
        // Add logout navigation logic here
    }

    public void setCurrentAccount(Account account) {
        this.currentAccount = account;
        if (transactionList != null) {
            loadTransactions();
        }
    }

    private void loadTransactions() {
        transactionList.getChildren().clear(); // Clear any previous transactions
        if (currentAccount != null) {
            for (Order order : currentAccount.getOrders()) {
                for (Book book : order.getOrderContent()) {
                    try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupone/sundevilbookbank/views/TransactionItem.fxml"));
                        Node transactionNode = loader.load();

                        TransactionItemController controller = loader.getController();
                        controller.setTransactionDetails(book, order.getOrderStatus());

                        transactionList.getChildren().add(transactionNode);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        } else {
            System.out.println("No account data found.");
        }
    }
}
