package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.MainApp;
import groupone.sundevilbookbank.services.Base;
import groupone.sundevilbookbank.utils.GlobalData;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;
import javafx.scene.control.Label;

public class LandingPageController {
    
    @FXML
    private Button purchaseABook;

    @FXML
    private Button sellABook;

	@FXML
	private Label title;

    @FXML
    public void initialize() {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_24pt-Bold.ttf"), 18);

		String name = (GlobalData.getCurrentAccount() != null && GlobalData.getCurrentAccount().getUsername() != null) 
			? GlobalData.getCurrentAccount().getUsername() : "user";
		title.setText("Welcome " + name + "!");

        //Set fonts for the buttons
        purchaseABook.setFont(customFont);
        sellABook.setFont(customFont);

		purchaseABook.setOnAction(e -> handlePurchaseButton());
		sellABook.setOnAction(e -> handleSellButton());
    }

	private void handlePurchaseButton() {
		MainApp.goToPage(4);
	}

	private void handleSellButton() {
		MainApp.goToPage(5);
	}
}
