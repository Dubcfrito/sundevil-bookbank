package groupone.sundevilbookbank.controllers;


import groupone.sundevilbookbank.MainApp;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;

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
    private HBox navBar;  

    @FXML
    public void initialize() {

		System.out.print("Navbar initialized");

        logoButton.prefWidthProperty().bind(miniLogoView.fitWidthProperty());
		logoButton.prefHeightProperty().bind(miniLogoView.fitHeightProperty()); 


        miniLogoView.setImage(new Image(getClass().getResource("/groupone/sundevilbookbank/images/mini_logo.png").toExternalForm()));
        accountButtonView.setImage(new Image(getClass().getResource("/groupone/sundevilbookbank/images/account_button.png").toExternalForm()));

        logoButton.setOnAction(event -> handleLogoClick());
        accountButton.setOnAction(event -> handleAccountClick());
    }

    private void handleLogoClick() {
        System.out.println("Logo button clicked!");
		//try {
		//	FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupone/sundevilbookbank/views/LandingPage.fxml"));
		//	Parent accountPage = loader.load();
		//	Stage currentStage = (Stage) accountButton.getScene().getWindow();
		//	currentStage.setScene(new Scene(accountPage));
		//	currentStage.show();
		//} catch (IOException e) {
		//	e.printStackTrace();
		//	System.err.println("Error loading accountPage");
		//}
        
		MainApp.goToPage(3);
    }

    private void handleAccountClick() {
        System.out.println("Account button clicked!");
		//try {
		//	FXMLLoader loader = new FXMLLoader(getClass().getResource("/groupone/sundevilbookbank/views/AccountPage.fxml"));
		//	Parent accountPage = loader.load();
		//	Stage currentStage = (Stage) accountButton.getScene().getWindow();
		//	currentStage.setScene(new Scene(accountPage));
		//	currentStage.show();
		//} catch (IOException e) {
		//	e.printStackTrace();
		//	System.err.println("Error loading accountPage");
		//}
		MainApp.goToPage(5);
    }
}
