package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.models.Account;
import groupone.sundevilbookbank.models.Order;
import groupone.sundevilbookbank.utils.GlobalData;
import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.services.Base;
import groupone.sundevilbookbank.models.Order;
import java.util.ArrayList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;

public class AccountPageController {

	private Account currentAccount; // Account object to hold current user data

	private int currentID;

	@FXML
	private ImageView profilePicture;

	@FXML
	private VBox transactionList;

	@FXML
	private Button logoutButton;

	@FXML
	private Text usernameText;

	@FXML
	public void initialize() {
		// Retrieve the current account from GlobalData or Base
		currentAccount = GlobalData.getCurrentAccount();
		if (currentAccount == null) {
			currentAccount = Base.getAccount("Jack", "Klebonis");
		}
		currentID = currentAccount.getAccountID();

		profilePicture.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/default_profile.png")));
		// Dynamically set the username
		usernameText.setText("Name: " + currentAccount.getUsername());

		// Set logout button action
		logoutButton.setOnAction(event -> handleLogout());

		// Load transactions
		loadTransactions();
	}
	@FXML
	private void handleLogout() {
		try {
			System.out.println("Logout button clicked");
			GlobalData.setCurrentAccount(null);
			FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupone/sundevilbookbank/views/LoginPage.fxml"));
			Parent loginPage = loader.load();

			Stage currentStage = (Stage) logoutButton.getScene().getWindow();
			currentStage.setScene(new Scene(loginPage));
			currentStage.show();
		} catch (IOException e) {
			e.printStackTrace();
			System.err.println("Error loading loginpage");
		}
	}

	private void loadTransactions() {
		transactionList.getChildren().clear(); // Clear existing transactions

		ArrayList<Order> orders = Base.getOrders(currentID);
		if (orders != null && !orders.isEmpty()) {
			orders.forEach(order -> {
				try {
					FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupone/sundevilbookbank/views/TransactionItem.fxml"));
					Node transactionNode = loader.load();

					TransactionItemController controller = loader.getController();
					controller.setOrderDetails(order); // Pass the Order to the controller

					transactionList.getChildren().add(transactionNode);
				} catch (IOException e) {
					System.err.println("Error loading transaction item: " + e.getMessage());
					e.printStackTrace();
				}
			});
		} else {
			System.out.println("No orders found for ID: " + currentID);
		}
	}
}
