package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;

public class ForgotPasswordController {

    @FXML
    private TextField emailField;

    @FXML
    private void handleResetRequest() {
        String email = emailField.getText();

        if (email.isEmpty()) {
            showAlert("Error", "Please enter your email or username.");
        } else {
            // Simulate sending a password reset link or further instructions.
            showAlert("Password Reset", "A password reset link has been sent to " + email);
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
