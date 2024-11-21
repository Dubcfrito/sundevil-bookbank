package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.MainApp;
import groupone.sundevilbookbank.services.Base;
import javafx.fxml.FXML;
import groupone.sundevilbookbank.utils.PageLoader;
import groupone.sundevilbookbank.utils.GlobalData;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Font;

public class LoginPageController {
    Base base = new Base();

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
        if (!base.validationLogin(username, password)) {
            showAlert("Invalid Login", "Username or password is incorrect.\nPress Sign up to create an account.\nPress Forgot Password to reset your password.");
        } else {
            GlobalData.setCurrentAccount(base.getAccount(username, password));
            MainApp.goToPage(3);
        }
    }

    @FXML
    private void handleSignUpAction() {
        // Logic for sign-up action
        MainApp.goToPage(1);
    }

    @FXML
    private void handleForgotPasswordAction() {
        // Logic for forgot password action
        MainApp.goToPage(2);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
