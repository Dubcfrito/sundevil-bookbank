package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class LoginPageController {

    @FXML
    private Button loginButton;

    @FXML
    private Button signUpButton;

    @FXML
    private Button forgotPasswordButton;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ImageView logoView;

    @FXML
    public void initialize() {
        // Load custom font, assuming "Inter_24pt-Bold" font file is in src/main/resources/fonts
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_24pt-Bold.ttf"), 18);

        // Set the font for buttons and text fields
        loginButton.setFont(customFont);
        signUpButton.setFont(customFont);
        forgotPasswordButton.setFont(customFont);
        usernameField.setFont(customFont);
        passwordField.setFont(customFont);

        // Add any additional initialization logic here if necessary
        logoView.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/logo.png")));

    }

    // Optional action handlers for buttons
    @FXML
    private void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Add login validation or navigation logic here
    }

    @FXML
    private void handleSignUpAction() {
        // Logic for sign-up action
    }

    @FXML
    private void handleForgotPasswordAction() {
        // Logic for forgot password action
    }
}
