package groupone.sundevilbookbank.models;

import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private ArrayList<Book> orderContent;
    private String sellerID;
    private String buyerID;
    private String orderDate;
    private String orderStatus;
    private String orderTotal;
    private String orderSellDate;

    public Order(int orderNumber, String sellerID, String buyerID, String orderDate, String orderStatus, String orderTotal, String orderSellDate) {
        this.orderNumber = orderNumber;
        this.orderContent = new ArrayList<Book>();
        this.sellerID = sellerID;
        this.buyerID = buyerID;
        this.orderDate = orderDate;
        this.orderStatus = orderStatus;
        this.orderTotal = orderTotal;
        this.orderSellDate = orderSellDate;
    }

    //getters and setters
    public int getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }
    public ArrayList<Book> getOrderContent() {
        return orderContent;
    }
    public void setOrderContent(ArrayList<Book> orderContent) {
        this.orderContent = orderContent;
    }
    public String getSellerID() {
        return sellerID;
    }
    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }
    public String getBuyerID() {
        return buyerID;
    }
    public void setBuyerID(String buyerID) {
        this.buyerID = buyerID;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getOrderStatus() {
        return orderStatus;
    }
    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }
    public String getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(String orderTotal) {
        this.orderTotal = orderTotal;
    }
    public String getOrderSellDate() {
        return orderSellDate;
    }
    public void setOrderSellDate(String orderSellDate) {
        this.orderSellDate = orderSellDate;
    }
    public void addBook(Book book) {
        this.orderContent.add(book);
    }
    public void removeBook(Book book) {
        this.orderContent.remove(book);
    }
}
