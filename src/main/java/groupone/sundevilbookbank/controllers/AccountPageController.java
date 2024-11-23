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
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.text.Text;

import java.io.IOException;

public class AccountPageController {

	private Account currentAccount; // Account object to hold current user data

	private boolean isBodyOrders = true; 

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

		// Load Orders
		loadOrders();
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
	
	@FXML
	private void handleBodyToggle() {
		isBodyOrders = !isBodyOrders;
		if (isBodyOrders) {
			loadOrders();
		} else {
			// Load book listings
			loadListings();
		}
		System.out.println("Body toggle clicked");
	}

	private void loadOrders() {
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

	private void loadListings() {
		transactionList.getChildren().clear();
		// Load books
		ArrayList<Book> books = Base.getSellerListings(GlobalData.getCurrentAccount().getAccountID());
		if (books != null && !books.isEmpty()) {
			books.forEach(book -> {
				addBook(book);
			});
		} else {
			System.out.println("No listings found for ID: " + currentID);
		}
	}

	public void addBook(Book book) {
        // Create a HBox to hold the book information
        HBox bookInfo = new HBox();
        bookInfo.setSpacing(10);

        // 1st element, book image (uses image placeholder for now)
        Image image = new Image(getClass().getResource("/groupone/sundevilbookbank/images/logo.png").toExternalForm());
        javafx.scene.image.ImageView imageView = new javafx.scene.image.ImageView(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        bookInfo.getChildren().add(imageView);

        // 2nd element, book details arranged in a gridpane
        GridPane details = new GridPane();
        details.setHgap(10);
        details.setVgap(0);

        // Add book information as labels
        Label title = new Label(book.getTitle());
        Label author = new Label("Author: " + book.getAuthor());
        Label subject = new Label("Subject: " + book.getSubject());
        Label genre = new Label("Genre: " + book.getGenre());
        Label condition = new Label("Condition: " + book.getCondition());
        Label price = new Label("Price: $" + book.getPrice());
        Label isbn = new Label("ISBN: " + book.getISBN());
        Label description = new Label("Description: " + book.getDescription());

        title.getStyleClass().add("book-title");

        // Add labels to gridpane
        details.add(title, 0, 0);
        details.add(genre, 0, 1);
        details.add(subject, 0, 2);
        details.add(condition, 0, 3);
        details.add(author, 1, 1);
        details.add(isbn, 1, 2);
        details.add(price, 0, 4);
        details.add(description, 2, 1);

        //make each component of the gridpane take up the same amount of space
        ColumnConstraints col1 = new ColumnConstraints();
        col1.setPercentWidth(25);

        ColumnConstraints col2 = new ColumnConstraints();
        col2.setPercentWidth(25);

        ColumnConstraints col3 = new ColumnConstraints();
        col3.setPercentWidth(50);

        details.getColumnConstraints().addAll(col1, col2, col3);

        // Add gridpane to HBox
        bookInfo.getChildren().add(details);

        bookInfo.getStyleClass().add("book-info");
        bookInfo.setHgrow(details, Priority.ALWAYS);
        // set border color and weight
        bookInfo.setStyle("-fx-background-color: #F8F2DC; -fx-border-color: #568196; -fx-border-style: solid; -fx-border-width: 1px;");
        bookInfo.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        // Add the HBox to the details VBox
        transactionList.getChildren().add(bookInfo);
    }
}
