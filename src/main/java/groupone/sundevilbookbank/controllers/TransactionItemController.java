package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.models.Book;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TransactionItemController {

    @FXML
    private ImageView itemImage;

    @FXML
    private Text itemTitle;

    @FXML
    private Text itemGenre;

    @FXML
    private Text itemSubject;

    @FXML
    private Text itemCondition;

    @FXML
    private Text itemPrice;

    @FXML
    private Text itemAuthor;

    @FXML
    private Text itemISBN;

    @FXML
    private Text itemStatus;

    @FXML
    private Button viewButton;

    private Book book;

    @FXML
    public void initialize() {
        viewButton.setOnAction(event -> handleViewButtonClick());
    }

    public void setTransactionDetails(Book book, String orderStatus) {
        this.book = book;

        itemTitle.setText(book.getTitle());
        itemGenre.setText("Genre: " + book.getGenre());
        itemSubject.setText("Subject: " + book.getSubject());
        itemCondition.setText("Condition: " + book.getCondition());
        itemPrice.setText("Price: " + book.getPrice());
        itemAuthor.setText("Author: " + book.getAuthor());
        itemISBN.setText("ISBN: " + book.getISBN());
        itemStatus.setText(orderStatus);

        if (book.getImages() != null && !book.getImages().isEmpty()) {
            itemImage.setImage(new Image(getClass().getResourceAsStream(book.getImages())));
        } else {
            itemImage.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/default_book.png")));
        }
    }

    private void handleViewButtonClick() {
        System.out.println("View Details button clicked for book: " + book.getTitle());
        // Add logic to display detailed view
    }
}
