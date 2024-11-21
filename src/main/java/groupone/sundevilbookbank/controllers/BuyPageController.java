package groupone.sundevilbookbank.controllers;

import java.util.ArrayList;

import groupone.sundevilbookbank.MainApp;
import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.models.Order;
import groupone.sundevilbookbank.services.Base;
import groupone.sundevilbookbank.utils.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.Priority;

public class BuyPageController {
    private Base base = new Base();

    // Order object to store the current order
    Order order;

    // Filter options
    String title = null;
    ArrayList<String> genreOptions = new ArrayList<>();
    ArrayList<String> subjectOptions = new ArrayList<>();
    ArrayList<String> authorOptions = new ArrayList<>();
    ArrayList<String> ISBNOptions = new ArrayList<>();
    ArrayList<String> conditionOptions = new ArrayList<>();
    ArrayList<String> priceOptions = new ArrayList<>();

    // List of books to be displayed
    ArrayList<Book> displayedBooks = new ArrayList<Book>();

    // FXML elements linked by fx:id
    @FXML
    private TextField searchField; // The search bar

    @FXML
    private TextField authorTextField; // The author filter text field

    @FXML
    private TextField ISBNTextField; // The ISBN filter text field

    @FXML
    private VBox checkBoxContainer; // The VBox for the condition filter checkboxes

    @FXML
    private CheckBox condition1, condition2, condition3, condition4, condition5, condition6; // The checkboxes for condition filter

    @FXML
    private VBox genreButtonContainer; // The VBox for the genre filter buttons

    @FXML
    private VBox subjectButtonContainer; // The VBox for the subject filter buttons

    @FXML
    private VBox authorButtonContainer; // The VBox for the author filter buttons

    @FXML
    private VBox ISBNButtonContainer; // The VBox for the ISBN filter buttons

    @FXML
    private VBox conditionButtonContainer; // The VBox for the price filter buttons

    @FXML
    private VBox priceButtonContainer; // The VBox for the price filter buttons

    @FXML
    private CheckBox price1, price2, price3, price4, price5; // The checkboxes for price filter

    @FXML
    private VBox searchResults; // The VBox for displaying search results

    // This method is automatically called after the FXML elements are loaded
    @FXML
    public void initialize() {
        // Set up any initial configurations if needed
        System.out.println("Bookstore page loaded.");

        // Create a new order if one does not exist
        if (GlobalData.getCurrentOrder() == null) {
            GlobalData.setCurrentOrder(new Order());
    
        }
        
        order = GlobalData.getCurrentOrder();

        // use getallsubjects() to get all subjects from the database and add each subject as a checkbox to subjectButtonContainer
        ArrayList<String> subjects = base.getAllSubjects();
        for (String subject : subjects) {
            addSubjectBox(subjectButtonContainer, subject, false);
        }

        // use getallauthors() to get all authors from the database and add each author as a checkbox to authorButtonContainer
        ArrayList<String> genres = base.getAllGenres();
        for (String genre : genres) {
            addGenreBox(genreButtonContainer, genre, false);
        }

        // use getallbooks() to get all books from the database and add each book to displayedBooks
        displayedBooks = base.getAllBooks();
        for (Book book : displayedBooks) {
            addBook(book);
        }
    }

    // handle cart button
    @FXML
    private void handleCart() {
        GlobalData.setCurrentOrder(order);
        MainApp.goToPage(4);
        System.out.println("Cart button clicked!");
    }
    
    // Event handler for search functionality
    @FXML
    private void handleSearch() {
        title = searchField.getText();
        System.out.println("Searching for: " + title);
        // Implement search functionality to filter or update the bookList here
        updateDisplayedBooks();
    }

    // Event handler for Genre filter
    @FXML
    private void handleGenreFilter() {
        genreOptions.clear();
        for (var node : genreButtonContainer.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                genreOptions.add(checkBox.getText());
                System.out.println("Genre: " + checkBox.getText() + ", Selected: " + checkBox.isSelected());
            }
        }
        updateDisplayedBooks();
    }

    // Event handler for Subject filter
    @FXML
    private void handleSubjectFilter() {
        subjectOptions.clear();
        for (var node : subjectButtonContainer.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                subjectOptions.add(checkBox.getText());
                System.out.println("Subject: " + checkBox.getText() + ", Selected: " + checkBox.isSelected());
            }
        }
        updateDisplayedBooks();
    }

    // Event handler for Author filter
    @FXML
    private void handleAuthorSearch() {
        String searchText = authorTextField.getText();
        addAuthorBox(authorButtonContainer, searchText, true);
        System.out.println("Searching for: " + searchText);
        updateDisplayedBooks();
    }

    @FXML
    private void handleAuthorFilter() {
        authorOptions.clear();
        for (var node : authorButtonContainer.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                authorOptions.add(checkBox.getText());
                System.out.println("Author: " + checkBox.getText() + ", Selected: " + checkBox.isSelected());
            }
        }
        updateDisplayedBooks();
    }

    // Event handler for ISBN Searchbar
    @FXML
    private void handleISBNSearch() {
        String searchText = ISBNTextField.getText();
        addISBNBox(ISBNButtonContainer, searchText, true);
        System.out.println("Searching for: " + searchText);
        updateDisplayedBooks();
    }

    @FXML
    private void handleISBNFilter() {
        ISBNOptions.clear();
        for (var node : ISBNButtonContainer.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                ISBNOptions.add(checkBox.getText());
                System.out.println("ISBN: " + checkBox.getText() + ", Selected: " + checkBox.isSelected());
            }
        }
        updateDisplayedBooks();
    }

    // Event handler for Condition filter
    @FXML
    private void handleConditionFilter() {
        conditionOptions.clear();
        for (var node : conditionButtonContainer.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                conditionOptions.add(checkBox.getText());
                System.out.println("Condition: " + checkBox.getText() + ", Selected: " + checkBox.isSelected());
            }
        }
        updateDisplayedBooks();
    }

    // Event handler for Price filter
    @FXML
    private void handlePriceFilter() {
        priceOptions.clear();
        for (var node : priceButtonContainer.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                priceOptions.add(checkBox.getText());
            }
        }
        for (String price : priceOptions) {
            System.out.println("Price filter clicked: " + price);
        }
        // Implement filtering functionality for Price here
    }

    public void addGenreBox(VBox container, String buttonText, boolean selected) {
        CheckBox newCheckBox = new CheckBox(buttonText);
        newCheckBox.setOnAction(e -> handleGenreFilter()); // Handle click event
        newCheckBox.setSelected(selected);
        container.getChildren().add(newCheckBox); // Add the button to VBox
    }

    public void addSubjectBox(VBox container, String buttonText, boolean selected) {
        CheckBox newCheckBox = new CheckBox(buttonText);
        newCheckBox.setOnAction(e -> handleSubjectFilter()); // Handle click event
        newCheckBox.setSelected(selected);
        container.getChildren().add(newCheckBox); // Add the button to VBox
    }

    public void addAuthorBox(VBox container, String buttonText, boolean selected) {
        CheckBox newCheckBox = new CheckBox(buttonText);
        newCheckBox.setOnAction(e -> handleAuthorFilter()); // Handle click event
        newCheckBox.setSelected(selected);
        container.getChildren().add(newCheckBox); // Add the button to VBox
        authorOptions.add(newCheckBox.getText());
    }

    public void addISBNBox(VBox container, String buttonText, boolean selected) {
        CheckBox newCheckBox = new CheckBox(buttonText);
        newCheckBox.setOnAction(e -> handleISBNFilter()); // Handle click event
        newCheckBox.setSelected(selected);
        container.getChildren().add(newCheckBox); // Add the button to VBox
        ISBNOptions.add(newCheckBox.getText());
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

        // 3rd element, buy button
        Button buyButton = new Button("+");
        buyButton.setOnAction(e -> addBookToCart(book));
        bookInfo.getChildren().add(buyButton);

        buyButton.getStyleClass().add("add-button");
        bookInfo.getStyleClass().add("book-info");
        bookInfo.setHgrow(details, Priority.ALWAYS);
        // Add the HBox to the bookList VBox
        searchResults.getChildren().add(bookInfo);
    }

    public void addBookToCart(Book book) {
        // Add book to cart
        order.addBook(book);
        System.out.println("Book added to cart: " + book.getTitle());
    }
    // update the displayedBooks list with the books that match the filters
    public void updateDisplayedBooks() {
        displayedBooks.clear();
        searchResults.getChildren().clear();
        // Implement filtering functionality here
        displayedBooks = base.searchBooks(title, genreOptions, subjectOptions, authorOptions, conditionOptions, null, null, ISBNOptions);
        for (Book book : displayedBooks) {
            addBook(book);
        }
        System.out.println("Books found: " + displayedBooks.size());
    }
}