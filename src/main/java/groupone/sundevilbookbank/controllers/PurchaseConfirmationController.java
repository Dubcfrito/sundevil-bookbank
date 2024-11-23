package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.MainApp;
import groupone.sundevilbookbank.utils.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class PurchaseConfirmationController {
    
    @FXML
    private Button buyAgain;

    @FXML
    private Button account;

    @FXML
    private Label secondary;

    @FXML
    public void initialize() {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_24pt-Bold.ttf"), 18);

        //Set fonts for the buttons
        buyAgain.setFont(customFont);
        account.setFont(customFont);

        secondary.setText("" + GlobalData.getCurrentOrder().getOrderNumber());
    }

    @FXML
    public void handleBuyAgain() {
        GlobalData.setCurrentOrder(null);
        MainApp.goToPage(4);
    }
}
