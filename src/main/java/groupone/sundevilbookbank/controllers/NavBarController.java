package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class NavBarController {

    @FXML
    private Button homeButton;

    @FXML
    private Button accountButton;

    @FXML
    public void initialize() {
        homeButton.setOnAction(event -> {
            System.out.println("Home button clicked");
        });
        accountButton.setOnAction(event -> {
            System.out.println("Account button clicked");
        });
    }
}
