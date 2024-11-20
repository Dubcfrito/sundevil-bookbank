package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.utils.PageLoader;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class SignUpPageController {

    @FXML
    private ImageView logoView;

    @FXML
    private TextField usernameField;

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confirmPasswordField;

    @FXML
    private Button signUpButton;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        logoView.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/logo.png")));
    }

    @FXML
    private void handleSignUpAction() {
        String username = usernameField.getText();
        String email = emailField.getText();
        String password = passwordField.getText();
        String confirmPassword = confirmPasswordField.getText();

        if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
        } else if (!password.equals(confirmPassword)) {
            showAlert("Error", "Passwords do not match.");
        } else {
            // Implement logic to handle user registration
            showAlert("Success", "User registered successfully!");
            PageLoader.loadPage("LoginPage.fxml");
        }
    }

    @FXML
    private void handleBackAction() {
        PageLoader.loadPage("LoginPage.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
