package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.models.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class TransactionItemController {
    boolean opened = false;

    @FXML
    private ImageView orderImage;

    @FXML
    private Text orderIDText;

    @FXML
    private Text orderStatusText;

    @FXML
    private Text orderTotalText;

    @FXML
    private Text bookCountText;

    @FXML
    private Button viewButton;

    @FXML
    private VBox orderDetails;

    private Order order;

    @FXML
    public void initialize() {
        viewButton.setOnAction(event -> handleViewButtonClick());
    }

    public void setOrderDetails(Order order) {
        this.order = order;

        // Populate the fields with order details
        orderIDText.setText("Order ID: " + order.getOrderNumber());
        orderStatusText.setText("Status: " + order.getOrderStatus());
        orderTotalText.setText("Total: $" + order.getOrderTotal());
        bookCountText.setText("Books: " + order.getOrderContent().size());

        // Set a default image for orders
        //orderImage.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/default_order.png")));
    }

    private void handleViewButtonClick() {
        orderDetails.getChildren().clear();
        if (opened) {
            opened = false;
            viewButton.setText("View Details");
        } else {
            for (Book book : order.getOrderContent()) {
                addBook(book);
            }
            opened = true;
            viewButton.setText("Close Details");
        }
        System.out.println("Order ID: " + order.getOrderNumber() + " opened: " + opened);
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
        bookInfo.setStyle("-fx-border-color: #568196; -fx-border-style: solid; -fx-border-width: 1px;");
        bookInfo.setPadding(new javafx.geometry.Insets(10, 10, 10, 10));
        // Add the HBox to the details VBox
        orderDetails.getChildren().add(bookInfo);
    }
}

