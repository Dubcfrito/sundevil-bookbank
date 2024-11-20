package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.MainApp;
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
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_24pt-Bold.ttf"), 18);

        loginButton.setFont(customFont);
        signUpButton.setFont(customFont);
        forgotPasswordButton.setFont(customFont);
        usernameField.setFont(customFont);
        passwordField.setFont(customFont);

        // Add any additional initialization logic here if necessary
        logoView.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/logo.png")));

    }

    @FXML
    private void handleLoginAction() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Add login validation or navigation logic here
        
    }

    @FXML
    private void handleSignUpAction() {
        // Logic for sign-up action
        MainApp.goToPage(1, null, null);
    }

    @FXML
    private void handleForgotPasswordAction() {
        // Logic for forgot password action
        MainApp.goToPage(1, null, null);
    }
}
