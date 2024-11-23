package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class SellController {

    @FXML
    private TextField titleField;

    @FXML
    private TextField priceField;

    @FXML
    private TextField subjectField;

    @FXML
    private TextField authorField;

    @FXML
    private TextField genreField;

    @FXML
    private TextField conditionField;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button submitButton;

    @FXML
    public void initialize() {
        // Event handlers to log input to the console
        titleField.setOnKeyReleased(event -> System.out.println("Title: " + titleField.getText()));
        priceField.setOnKeyReleased(event -> System.out.println("Selling Price: " + priceField.getText()));
        subjectField.setOnKeyReleased(event -> System.out.println("Subject: " + subjectField.getText()));
        authorField.setOnKeyReleased(event -> System.out.println("Author: " + authorField.getText()));
        genreField.setOnKeyReleased(event -> System.out.println("Genre: " + genreField.getText()));
        conditionField.setOnKeyReleased(event -> System.out.println("Condition: " + conditionField.getText()));
        descriptionField.setOnKeyReleased(event -> System.out.println("Description: " + descriptionField.getText()));

        // Submit button action
        submitButton.setOnAction(event -> {
            System.out.println("Form Submitted:");
            System.out.println("Title: " + titleField.getText());
            System.out.println("Selling Price: " + priceField.getText());
            System.out.println("Subject: " + subjectField.getText());
            System.out.println("Author: " + authorField.getText());
            System.out.println("Genre: " + genreField.getText());
            System.out.println("Condition: " + conditionField.getText());
            System.out.println("Description: " + descriptionField.getText());
        });
    }
}
