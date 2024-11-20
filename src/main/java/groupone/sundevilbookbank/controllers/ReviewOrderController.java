package groupone.sundevilbookbank.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Font;

public class ReviewOrderController {
    
    @FXML
    private Button placeOrder;


    @FXML
    public void initialize() {
        Font customFont = Font.loadFont(getClass().getResourceAsStream("/fonts/Inter_24pt-Bold.ttf"), 18);

        //Set fonts for the buttons
        placeOrder.setFont(customFont);
    }

}
