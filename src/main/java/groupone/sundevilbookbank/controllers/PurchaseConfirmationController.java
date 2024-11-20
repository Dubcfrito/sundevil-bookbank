package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class PurchaseConfirmationController {
    
    @FXML
    private Button buyAgain;

    @FXML
    private Button account;

    @FXML
    public void initialize() {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_24pt-Bold.ttf"), 18);

        //Set fonts for the buttons
        buyAgain.setFont(customFont);
        account.setFont(customFont);
    }

}
