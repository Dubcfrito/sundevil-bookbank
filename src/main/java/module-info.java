module groupone.sundevilbookbank {
    requires javafx.controls;
    requires javafx.fxml;


    opens groupone.sundevilbookbank to javafx.fxml;
    exports groupone.sundevilbookbank.ui;
}