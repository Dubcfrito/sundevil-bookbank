package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class SellerConformationController {

    @FXML
    private Label messageLabel;

    @FXML
    private Button listAnotherBookButton;

    @FXML
    private Button returnToAccountButton;

    @FXML
    public void initialize() {
        // Initialize logic when FXML is loaded
        messageLabel.setText("Your listing has been posted!");
    }

    @FXML
    private void onListAnotherBookClick() {
        // Logic for "List Another Book" button click
        System.out.println("List Another Book button clicked!");
    }

    @FXML
    private void onReturnToAccountClick() {
        // Logic for "Return To Account" button click
        System.out.println("Return To Account button clicked!");
    }
}

