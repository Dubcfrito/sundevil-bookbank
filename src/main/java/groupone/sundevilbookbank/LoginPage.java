package groupone.sundevilbookbank;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginPage extends Application {

    @Override
    public void start(Stage primaryStage) {
        DropShadow dropShadow = new DropShadow();
        dropShadow.setColor(Color.color(0, 0, 0, 0.25));
        dropShadow.setRadius(5);

        Image logoImage = new Image(getClass().getResource("/groupone/sundevilbookbank/logo.png").toExternalForm());
        ImageView logoView = new ImageView(logoImage);
        logoView.setFitWidth(400); // adjust size proportionally for larger window
        logoView.setPreserveRatio(true);
        logoView.setEffect(dropShadow);

        StackPane leftPane = new StackPane();
        leftPane.getChildren().add(logoView);
        leftPane.setStyle("-fx-background-color: #F8F2DC;");
        leftPane.setEffect(dropShadow);

        Label title = new Label(" Sun Devil\nBook Bank");
        title.setTextFill(Color.web("#F8F2DC"));
        title.setFont(Font.font("Inter 28pt", 72));
        title.setEffect(dropShadow);
        title.setAlignment(Pos.CENTER);
        title.setMaxWidth(Double.MAX_VALUE);

        Rectangle underline = new Rectangle(400, 10, Color.web("#F8F2DC"));
        underline.setEffect(dropShadow);

        TextField usernameField = new TextField();
        usernameField.setPromptText("Username");
        usernameField.setStyle("-fx-background-color: #568196; -fx-prompt-text-fill: white; -fx-text-fill: white; -fx-background-radius: 19; -fx-padding: 0 0 0 15;");
        usernameField.setMaxWidth(360);
        usernameField.setPrefHeight(70);
        usernameField.setFont(Font.font("Inter 28pt", 18));
        usernameField.setEffect(dropShadow);

        PasswordField passwordField = new PasswordField();
        passwordField.setPromptText("Password");
        passwordField.setStyle("-fx-background-color: #568196; -fx-prompt-text-fill: white; -fx-text-fill: white; -fx-background-radius: 19; -fx-padding: 0 0 0 15;");
        passwordField.setMaxWidth(360);
        passwordField.setPrefHeight(70);
        passwordField.setFont(Font.font("Inter 28pt", 18));
        passwordField.setEffect(dropShadow);

        Button loginButton = new Button("Login");
        Button signUpButton = new Button("Sign up");
        Button forgotPasswordButton = new Button("Forgot Password");

        String buttonStyle = "-fx-background-color: #FFA500; -fx-text-fill: black; -fx-background-radius: 19;";
        loginButton.setStyle(buttonStyle);
        signUpButton.setStyle(buttonStyle);
        forgotPasswordButton.setStyle(buttonStyle);

        loginButton.setMaxWidth(360);
        signUpButton.setMaxWidth(360);
        forgotPasswordButton.setMaxWidth(360);
        loginButton.setPrefHeight(70);
        signUpButton.setPrefHeight(70);
        forgotPasswordButton.setPrefHeight(70);
        loginButton.setFont(Font.font("Inter 28pt", 18));
        signUpButton.setFont(Font.font("Inter 28pt", 18));
        forgotPasswordButton.setFont(Font.font("Inter 28pt", 18));

        loginButton.setEffect(dropShadow);
        signUpButton.setEffect(dropShadow);
        forgotPasswordButton.setEffect(dropShadow);

        String buttonHoverStyle = "-fx-background-color: #E59400; -fx-text-fill: black; -fx-background-radius: 19;";
        loginButton.setOnMouseEntered(e -> loginButton.setStyle(buttonHoverStyle));
        loginButton.setOnMouseExited(e -> loginButton.setStyle(buttonStyle));
        signUpButton.setOnMouseEntered(e -> signUpButton.setStyle(buttonHoverStyle));
        signUpButton.setOnMouseExited(e -> signUpButton.setStyle(buttonStyle));
        forgotPasswordButton.setOnMouseEntered(e -> forgotPasswordButton.setStyle(buttonHoverStyle));
        forgotPasswordButton.setOnMouseExited(e -> forgotPasswordButton.setStyle(buttonStyle));

        String buttonClickStyle = "-fx-background-color: #CC8400; -fx-text-fill: black; -fx-background-radius: 19;";
        loginButton.setOnMousePressed(e -> loginButton.setStyle(buttonClickStyle));
        loginButton.setOnMouseReleased(e -> loginButton.setStyle(buttonStyle));
        signUpButton.setOnMousePressed(e -> signUpButton.setStyle(buttonClickStyle));
        signUpButton.setOnMouseReleased(e -> signUpButton.setStyle(buttonStyle));
        forgotPasswordButton.setOnMousePressed(e -> forgotPasswordButton.setStyle(buttonClickStyle));
        forgotPasswordButton.setOnMouseReleased(e -> forgotPasswordButton.setStyle(buttonStyle));

        VBox rightPane = new VBox(20);
        rightPane.setPadding(new Insets(20));
        rightPane.setAlignment(Pos.CENTER);
        rightPane.setStyle("-fx-background-color: #8A0D37;");
        rightPane.getChildren().addAll(title, underline, usernameField, passwordField, loginButton, signUpButton, forgotPasswordButton);
        rightPane.setEffect(dropShadow);

        HBox root = new HBox();
        root.getChildren().addAll(leftPane, rightPane);

        HBox.setHgrow(leftPane, Priority.ALWAYS);
        HBox.setHgrow(rightPane, Priority.ALWAYS);
        leftPane.setMaxWidth(Double.MAX_VALUE);
        rightPane.setMaxWidth(Double.MAX_VALUE);

        Scene scene = new Scene(root, 1280, 720);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Sun Devil Book Bank Login");
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
