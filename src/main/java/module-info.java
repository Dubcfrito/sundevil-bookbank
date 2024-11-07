module groupone.sundevilbookbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.json;

    // Export the main package where MainApp is located
    exports groupone.sundevilbookbank;

    // Open the controllers package to JavaFX, allowing reflective access for FXML loading
    opens groupone.sundevilbookbank.controllers to javafx.fxml;
}