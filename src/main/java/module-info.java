module groupone.sundevilbookbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens groupone.sundevilbookbank to javafx.fxml;
    exports groupone.sundevilbookbank.ui;
}