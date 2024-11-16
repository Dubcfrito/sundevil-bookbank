package groupone.sundevilbookbank.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.services.Base;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.control.Label;

public class BuyPageController {
    private Base base = new Base();

    // Filter options
    List<String> genreOptions = new ArrayList<>();
    List<String> subjectOptions = new ArrayList<>();
    List<String> authorOptions = new ArrayList<>();
    List<String> conditionOptions = new ArrayList<>();
    List<String> priceOptions = new ArrayList<>();

    // List of books to be displayed
    ArrayList<Book> displayedBooks = new ArrayList<Book>();

    // FXML elements linked by fx:id
    @FXML
    private TextField searchField; // The search bar

    @FXML
    private TextField authorTextField; // The author filter text field

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
        System.out.println(subjectButtonContainer);

        // use getallsubjects() to get all subjects from the database and add each subject as a checkbox to subjectButtonContainer
        ArrayList<String> subjects = base.getAllSubjects();
        for (String subject : subjects) {
            addCheckBox(subjectButtonContainer, subject, false);
        }

        // use getallauthors() to get all authors from the database and add each author as a checkbox to authorButtonContainer
        ArrayList<String> genres = base.getAllGenres();
        for (String genre : genres) {
            addCheckBox(genreButtonContainer, genre, false);
        }

        // use getallbooks() to get all books from the database and add each book to displayedBooks
        displayedBooks = base.getAllBooks();
        for (Book book : displayedBooks) {
            addBook(book);
        }
    }

    // Event handler for search functionality
    @FXML
    private void handleSearch() {
        String searchText = searchField.getText();
        System.out.println("Searching for: " + searchText);
        // Implement search functionality to filter or update the bookList here
    }

    // Event handler for Genre filter
    @FXML
    private void handleGenreFilter() {
        System.out.println("Genre filter clicked");
        // Implement filtering functionality for Genre here
    }

    // Event handler for Subject filter
    @FXML
    private void handleSubjectFilter() {
        System.out.println("Subject filter clicked");
        // Implement filtering functionality for Subject here
    }

    // Event handler for Author filter
    @FXML
    private void handleAuthorFilter() {
        String searchText = authorTextField.getText();
        addCheckBox(authorButtonContainer, searchText, true);
        System.out.println("Searching for: " + searchText);
    }

    // Event handler for Condition filter
    @FXML
    private void handleConditionFilter() {
        conditionOptions.clear();
        for (var node : checkBoxContainer.getChildren()) {
            if (node instanceof CheckBox checkBox && checkBox.isSelected()) {
                conditionOptions.add(checkBox.getText());
            }
        }
        for (String condition : conditionOptions) {
            System.out.println("Condition filter clicked: " + condition);
        }
        // Implement filtering functionality for Condition here
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

    public void addCheckBox(VBox container, String buttonText, boolean selected) {
        CheckBox newCheckBox = new CheckBox(buttonText);
        newCheckBox.setOnAction(e -> handleButtonClick(buttonText)); // Handle click event
        newCheckBox.setSelected(selected);
        container.getChildren().add(newCheckBox); // Add the button to VBox
    }

    private void handleButtonClick(String buttonText) {
        System.out.println(buttonText + " button clicked!");
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
        details.setHgrow(title, javafx.scene.layout.Priority.ALWAYS);
        details.setHgrow(genre, javafx.scene.layout.Priority.ALWAYS);
        details.setHgrow(subject, javafx.scene.layout.Priority.ALWAYS);
        details.setHgrow(condition, javafx.scene.layout.Priority.ALWAYS);
        details.setHgrow(author, javafx.scene.layout.Priority.ALWAYS);
        details.setHgrow(isbn, javafx.scene.layout.Priority.ALWAYS);
        details.setHgrow(price, javafx.scene.layout.Priority.ALWAYS);
        details.setHgrow(description, javafx.scene.layout.Priority.ALWAYS);

        // Add gridpane to HBox
        bookInfo.getChildren().add(details);

        // 3rd element, buy button
        Button buyButton = new Button("+");
        buyButton.setOnAction(e -> System.out.println("Buy button clicked!"));
        bookInfo.getChildren().add(buyButton);

        bookInfo.getStyleClass().add("book-info");
        // Add the HBox to the bookList VBox
        searchResults.getChildren().add(bookInfo); 
    }
}