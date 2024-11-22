package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.models.Order;
import groupone.sundevilbookbank.utils.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;

public class ShoppingCartController {

    // Order object to store the current order
    Order order;

    @FXML
    private VBox searchResults; // The VBox for displaying search results

    @FXML
    private VBox pricingSummary; // The VBox for displaying subtotal, tax, and final total of order

    // This method is automatically called after the FXML elements are loaded
    @FXML
    public void initialize() {
        
        order = GlobalData.getCurrentOrder();

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

        // Add the HBox to the bookList VBox
        searchResults.getChildren().add(bookInfo);
    }

    //This function calculates the subtotal, tax, and final total of the order.
    public void calculatePrice() {

        if(order.getOrderContent() == null) {
            //Print some message that says the shopping cart is empty.
            Label emptyCart = new Label("Your shopping cart is empty.");
            emptyCart.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 30;");
            pricingSummary.getChildren().add(emptyCart);
        }

        else {
            double subTotal = 0;
            double taxRate = 0.081; // Sales tax rate of Tempe.

            for (Book book : order.getOrderContent()) {
                subTotal += book.getPrice(); //Adding all the prices of the books in the order together.
            }

            double tax = subTotal * taxRate; //Calculating the amount of tax placed onto the order.
            double total = subTotal + tax; //Adding the tax to the subtotal to get our final price.

            Label title = new Label("Summary");
            title.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 35; -fx-padding: 20;");

            Label preTax = new Label("Subtotal: " + subTotal);
            preTax.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 30; -fx-padding: 10;");

            Label orderTax = new Label("Tax: " + tax);
            orderTax.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 30; -fx-padding: 10;");

            Label finalTotal = new Label("Total: " + total);
            finalTotal.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 30; -fx-padding: 10;");
            
            Button placeOrder = new Button("Place Order");
            placeOrder.maxWidth(360);
            placeOrder.prefHeight(70);
            placeOrder.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 18; -fx-background-color: #8A0D37; -fx-text-fill: #FFA500; -fx-background-radius: 19;");

            //Adding all the above info into our VBox on the right side of the window.
            pricingSummary.getChildren().addAll(title, preTax, orderTax, finalTotal, placeOrder);

        }
        
    }
}
