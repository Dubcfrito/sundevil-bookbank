package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.geometry.Insets;

public class NavBarController {

    @FXML
    private ImageView miniLogoView;

    @FXML
    private ImageView accountButtonView;

    @FXML
    private Button logoButton;

    @FXML
    private Button accountButton;

    @FXML
    private HBox navBar;  // Make sure the HBox is accessible

    @FXML
    public void initialize() {

        logoButton.prefWidthProperty().bind(miniLogoView.fitWidthProperty());
        logoButton.prefHeightProperty().bind(miniLogoView.fitHeightProperty());

        // Load images programmatically
        miniLogoView.setImage(new Image(getClass().getResource("/groupone/sundevilbookbank/images/mini_logo.png").toExternalForm()));
        accountButtonView.setImage(new Image(getClass().getResource("/groupone/sundevilbookbank/images/account_button.png").toExternalForm()));

        // Add button click listeners
        logoButton.setOnAction(event -> handleLogoClick());
        accountButton.setOnAction(event -> handleAccountClick());
    }

    private void handleLogoClick() {
        System.out.println("Logo button clicked!");
        // Navigate to the home page or perform another action
    }

    private void handleAccountClick() {
        System.out.println("Account button clicked!");
        // Navigate to the account page or perform another action
    }
}
