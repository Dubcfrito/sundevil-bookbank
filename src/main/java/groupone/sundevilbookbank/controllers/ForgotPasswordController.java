package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import groupone.sundevilbookbank.utils.PageLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.scene.image.Image; 
import javafx.scene.image.ImageView;

public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    @FXML 
    private ImageView logoView;

    @FXML public void initialize() { 
        logoView.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/logo.png"))); 
    }

    @FXML
    private void handleResetRequest() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            showAlert("Error", "Please enter your email or username.");
        } else {
            // Simulate sending a password reset link or further instructions.
            showAlert("Password Reset", "A password reset link has been sent to " + email);

            // Return to the login page after showing the alert
            PageLoader.loadPage("LoginPage.fxml");        
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
