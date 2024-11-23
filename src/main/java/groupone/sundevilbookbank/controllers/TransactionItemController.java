package groupone.sundevilbookbank.controllers;

import groupone.sundevilbookbank.models.Order;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

public class TransactionItemController {

    @FXML
    private ImageView orderImage;

    @FXML
    private Text orderIDText;

    @FXML
    private Text orderStatusText;

    @FXML
    private Text orderTotalText;

    @FXML
    private Text bookCountText;

    @FXML
    private Button viewButton;

    private Order order;

    @FXML
    public void initialize() {
        viewButton.setOnAction(event -> handleViewButtonClick());
    }

    public void setOrderDetails(Order order) {
        this.order = order;

        // Populate the fields with order details
        orderIDText.setText("Order ID: " + order.getOrderNumber());
        orderStatusText.setText("Status: " + order.getOrderStatus());
        orderTotalText.setText("Total: $" + order.getOrderTotal());
        bookCountText.setText("Books: " + order.getOrderContent().size());

        // Set a default image for orders
        //orderImage.setImage(new Image(getClass().getResourceAsStream("/groupone/sundevilbookbank/images/default_order.png")));
    }

    private void handleViewButtonClick() {
        System.out.println("View Details button clicked for Order ID: " + order.getOrderNumber());
        // Add logic to display detailed view or perform other actions
    }
}

