package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

public class SellController {

    @FXML
    private AnchorPane rootPane;

    @FXML
    private TextField titleField;

    @FXML
    private TextField priceField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private TextField yearField;

    @FXML
    private TextField authorField;

    @FXML
    private ComboBox<String> genreBox;

    @FXML
    private ComboBox<String> conditionBox;

    @FXML
    private TextField estimatedPriceField;

    @FXML
    private Button listBookButton;

    @FXML
    private Button closeButton;

    @FXML
    public void initialize() {
        // Populate the Genre ComboBox
        genreBox.getItems().addAll("Fiction", "Non-Fiction", "Science", "History", "Other");

        // Populate the Condition ComboBox
        conditionBox.getItems().addAll("New", "Like New", "Used - Good", "Used - Acceptable");
    }

    @FXML
    private void handleClose() {
        // Logic to handle closing the form
        System.out.println("Close button clicked.");
        rootPane.getScene().getWindow().hide();
    }

    @FXML
    private void handleListBook() {
        // Validate inputs
        if (titleField.getText().isEmpty() || priceField.getText().isEmpty() || genreBox.getValue() == null
                || conditionBox.getValue() == null || estimatedPriceField.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, "Validation Error", "Please fill in all required fields.");
            return;
        }

        // Example logic to handle listing the book
        String title = titleField.getText();
        String price = priceField.getText();
        String description = descriptionField.getText();
        String year = yearField.getText();
        String author = authorField.getText();
        String genre = genreBox.getValue();
        String condition = conditionBox.getValue();
        String estimatedPrice = estimatedPriceField.getText();

        System.out.println("Listing Book Details:");
        System.out.println("Title: " + title);
        System.out.println("Original Price: " + price);
        System.out.println("Description: " + description);
        System.out.println("Year: " + year);
        System.out.println("Author: " + author);
        System.out.println("Genre: " + genre);
        System.out.println("Condition: " + condition);
        System.out.println("Estimated Price: " + estimatedPrice);

        showAlert(Alert.AlertType.INFORMATION, "Success", "Your book has been listed!");
        clearForm();
    }

    private void clearForm() {
        // Clear all form inputs
        titleField.clear();
        priceField.clear();
        descriptionField.clear();
        yearField.clear();
        authorField.clear();
        genreBox.setValue(null);
        conditionBox.setValue(null);
        estimatedPriceField.clear();
    }

    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
