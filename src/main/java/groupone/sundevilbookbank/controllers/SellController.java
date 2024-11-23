package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Callback;
import javafx.scene.control.ListView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import groupone.sundevilbookbank.MainApp;
import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.services.Base;
import groupone.sundevilbookbank.utils.GlobalData;

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
    private TextField isbnField;

    @FXML
    private VBox conditionContainer;

    @FXML
    private TextArea descriptionField;

    @FXML
    private Button submitButton;

    @FXML
    private ComboBox<String> priorityComboBox;

    @FXML
    public void initialize() {
        priorityComboBox = new ComboBox<>();
        priorityComboBox.getItems().addAll(
            "Like New",
            "Very Good",
            "Acceptable",
            "Poor",
            "Bad",
            "Unspecified"
        );
        priorityComboBox.setValue("Unspecified");
        priorityComboBox.setStyle("-fx-font-family: 'Inter'; -fx-text-fill: white; -fx-background-color: #568196; -fx-font-size: 18px;");
        priorityComboBox.setCellFactory(
            new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    final ListCell<String> cell = new ListCell<String>() {
                        {
                            super.setPrefWidth(200);
                        }
                        @Override
                        public void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item);
                                setTextFill(Color.WHITE);
                                setStyle("-fx-background-color: #568196;");
                                setOnMouseEntered(event -> setStyle("-fx-font-family: 'Inter'; -fx-text-fill: #568196; -fx-background-color: white; -fx-font-size: 18px;"));
                                setOnMouseExited(event -> setStyle("-fx-font-family: 'Inter'; -fx-text-fill: white; -fx-background-color: #568196; -fx-font-size: 18px;"));

                            } else {
                                setText(null);
                            }
                        }
                    };
                    return cell;
                }
            }
        );

        // Style the button cell (selected value area)
        priorityComboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                } else {
                    setText(item);
                    setStyle("-fx-font-family: 'Inter'; -fx-text-fill: white; -fx-background-color: #568196; -fx-font-size: 18px;");
                }
            }
        });

        conditionContainer.getChildren().add(priorityComboBox);
    }

    @FXML
    public void handleSubmit() {
        if(titleField.getText() == "" || priceField.getText() == "" || subjectField.getText() == "" || authorField.getText() == "" || genreField.getText() == "") {
            showAlert("Invalid Listing", "Please fill out all fields marked with *");
        }
        else if(priceField.getText().matches("^\\d+(\\.\\d{1,2})?$") == false) {
            showAlert("Invalid Listing", "Please put a valid price (ex. 10.00).\nEnsure that there is no whitespace.");
        }
        else if(isbnField.getText().matches("[0-9]+") == false) {
            showAlert("Invalid Listing", "Please put a valid ISBN (ex. for 978-3-16-148410-0, just put 9783161484100).\nEnsure that there is no whitespace or any symbol other than characters 1-9.");
        }
        else {
            System.out.println("Form Submitted:");
            System.out.println("Title: " + titleField.getText());
            System.out.println("Selling Price: " + Double.parseDouble(priceField.getText()));
            System.out.println("Subject: " + subjectField.getText());
            System.out.println("Author: " + authorField.getText());
            System.out.println("Genre: " + genreField.getText());
            System.out.println("Condition: " + priorityComboBox.getValue());
            System.out.println("Description: " + descriptionField.getText());
            System.out.println("ISBN: " + isbnField.getText());
            // pricefield string into a double

            Base.insertBook(GlobalData.getCurrentAccount().getAccountID(), titleField.getText(), authorField.getText(), genreField.getText(), subjectField.getText(), isbnField.getText(), priorityComboBox.getValue(), descriptionField.getText(), Double.parseDouble(priceField.getText()), "Listed", "\\src\\main\\resources\\groupone\\sundevilbookbank\\images\\default_book.png");
            showAlert("Listing Successful", "Your book has been listed for sale!");
            MainApp.goToPage(3);
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
