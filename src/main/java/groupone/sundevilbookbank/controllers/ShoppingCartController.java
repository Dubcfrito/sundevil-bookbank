package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.MainApp;
import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.models.Order;
import groupone.sundevilbookbank.services.Base;
import groupone.sundevilbookbank.utils.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;

public class ShoppingCartController {
    final static double taxRate = 0.081; // Sales tax rate of Tempe.
    double subTotal = 0; // The subtotal of the order.
    double total = 0; // The total of the order.
    double tax = 0; // The tax placed onto the order.

    @FXML
    private VBox cartSummary; // The VBox for displaying cart

    @FXML
    private VBox pricingSummary; // The VBox for displaying subtotal, tax, and final total of order

    // This method is automatically called after the FXML elements are loaded
    @FXML
    public void initialize() {
        cartSummary.getChildren().clear();
        if (GlobalData.getCurrentOrder().getOrderContent().size() == 0) {
            // Print some message that says the shopping cart is empty.
            Label message = new Label("Your shopping cart is empty!");
            message.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 40; -fx-padding: 200 20 20 185;");
            cartSummary.getChildren().add(message);
        } else {
            for (Book book : GlobalData.getCurrentOrder().getOrderContent()) {
                addBook(book);
            }
        }
        calculatePrice();
    }

    @FXML
    public void handleMinusButton() {
        // Remove a book from the order
        GlobalData.getCurrentOrder().removeBook(null);
        // Update the cart display
        cartSummary.getChildren().clear();
        for (Book book : GlobalData.getCurrentOrder().getOrderContent()) {
            addBook(book);
        }
        calculatePrice();
    }

    public void addBook(Book book) {
        // Create a HBox to hold the book information
        HBox bookInfo = new HBox();
        bookInfo.setSpacing(10);
        bookInfo.setStyle("-fx-border-color: #568196; -fx-border-style: solid; -fx-border-width: 2; -fx-padding: 5;");

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

        // 3rd element, remove button
        Button removeButton = new Button("-");
        removeButton.setOnAction(e -> removeBook(book));
        removeButton.getStyleClass().add("remove-button");
        bookInfo.getChildren().add(removeButton);

        // removeButton.setStyle("-fx-background-color: transparent; -fx-border-color: transparent; -fx-font-size: 20px;");
        bookInfo.setHgrow(details, Priority.ALWAYS);

        // Add the HBox to the bookList VBox
        cartSummary.getChildren().add(bookInfo);
    }

    //This function calculates the subtotal, tax, and final total of the order.
    public void calculatePrice() {
        pricingSummary.getChildren().clear();
        if(GlobalData.getCurrentOrder().getOrderContent() == null) {
            //Print some message that says the shopping cart is empty.
            Label emptyCart = new Label("Your shopping cart is empty.");
            emptyCart.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 30;");
            pricingSummary.getChildren().add(emptyCart);
        }

        else {
            
            GlobalData.getCurrentOrder().updateOrderTotal(); //Updating the total of the order.

            subTotal = GlobalData.getCurrentOrder().getOrderTotal(); //Getting the total of the order.

            tax = subTotal * taxRate; //Calculating the amount of tax placed onto the order.
            total = subTotal + tax; //Adding the tax to the subtotal to get our final price.

            Label title = new Label("Summary");
            title.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 25; -fx-padding: 20;");

            Label preTax = new Label("Subtotal: $" + String.format("%.2f", subTotal));
            preTax.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 25; -fx-padding: 10;");

            Label orderTax = new Label("Tax: $" + String.format("%.2f", tax));
            orderTax.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 25; -fx-padding: 10;");

            Label finalTotal = new Label("Total: $" + String.format("%.2f", total));
            finalTotal.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 25; -fx-padding: 10;");
            
            Button placeOrder = new Button("Place Order");
            placeOrder.setOnAction(e -> handlePlaceOrder());
            placeOrder.getStyleClass().add("place-order-button");
            //placeOrder.maxWidth(70);
            //placeOrder.maxHeight(360);
            // placeOrder.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 18; -fx-background-color: #8A0D37; -fx-text-fill: #FFA500; -fx-background-radius: 19;");

            //Adding all the above info into our VBox on the right side of the window.
            pricingSummary.getChildren().addAll(title, preTax, orderTax, finalTotal, placeOrder);
        }
    }

    public void removeBook(Book book) {
        //Keep track of how many books are left in the cart.
        int count = 0;

        // Remove a book from the order
        GlobalData.removeBookFromOrder(book);
        // Update the cart display
        cartSummary.getChildren().clear();
        for (Book book1 : GlobalData.getCurrentOrder().getOrderContent()) {
            addBook(book1);
            count++;
        }

        //If there are no more books left in the cart, it will display a message saying that the cart is empty.
        if(count == 0) {
            Label message = new Label("Your shopping cart is empty!");
            message.setStyle("-fx-font-family: 'Inter 28pt'; -fx-font-size: 40; -fx-padding: 200 20 20 185;");
            cartSummary.getChildren().add(message);
        }

        calculatePrice();
    }

    @FXML
    public void handlePlaceOrder() {
        if (GlobalData.getCurrentOrder().getOrderContent().size() == 0) {
            // do nothing if the cart is empty
        } else {
            // Place the order
            System.out.println("Total: " + total);
            GlobalData.getCurrentOrder().setOrderStatus("Placed");
            GlobalData.getCurrentOrder().setOrderTotal(total);
            GlobalData.getCurrentOrder().setOrderNumber(Base.insertOrder(GlobalData.getCurrentAccount().getAccountID(), GlobalData.getCurrentOrder()));
            System.out.println("Order placed with number: " + GlobalData.getCurrentOrder().getOrderNumber());
            // Go to the PurchaseConfirmation page
            MainApp.goToPage(7);
        }
    }

    @FXML
    public void handleBackButton() {
        // Go back to the BuyPage
        MainApp.goToPage(4);
    }
}
