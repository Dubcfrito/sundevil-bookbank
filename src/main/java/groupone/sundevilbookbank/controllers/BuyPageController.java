package groupone.sundevilbookbank.controllers;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import groupone.sundevilbookbank.services.Base;

import javafx.fxml.FXML;
import javafx.scene.control.Accordion;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.VBox;
import javafx.scene.layout.HBox;

public class BuyPageController {
    private Base base = new Base();


    List<String> subjectOptions = new ArrayList<>();
    List<String> authorOptions = new ArrayList<>();
    List<String> conditionOptions = new ArrayList<>();
    List<String> priceOptions = new ArrayList<>();

    // FXML elements linked by fx:id
    @FXML
    private VBox bookList; // The VBox for displaying book listings

    @FXML
    private TextField searchField; // The search bar

    @FXML
    private TextField authorTextField; // The author filter text field

    @FXML
    private VBox checkBoxContainer; // The VBox for the condition filter checkboxes

    @FXML
    private CheckBox condition1, condition2, condition3, condition4, condition5, condition6; // The checkboxes for condition filter

    @FXML
    private Button subjectButton; // The subject filter button
    @FXML
    private Button authorButton; // The author filter button
    @FXML
    private Button conditionButton; // The condition filter button
    @FXML
    private Button priceButton; // The price filter button
    @FXML
    private VBox subjectButtonContainer; // The VBox for the subject filter buttons

    @FXML
    private VBox authorButtonContainer; // The VBox for the author filter buttons


    // This method is automatically called after the FXML elements are loaded
    @FXML
    public void initialize() {
        // Set up any initial configurations if needed
        System.out.println("Bookstore page loaded.");

        // use getallsubjects() to get all subjects from the database and add each subject as a checkbox to subjectButtonContainer
        ArrayList<String> subjects = base.getAllSubjects();
        for (String subject : subjects) {
            addCheckBox(subjectButtonContainer, subject, false);
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
        System.out.println("Price filter clicked");
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
}
