package groupone.sundevilbookbank.models;

import java.util.ArrayList;

public class Order {
    private int orderNumber;
    private ArrayList<Book> orderContent;
    private int buyerID;
    private String orderStatus;
    private String orderTotal;

    //constructors
    public Order(int orderNumber, int buyerID, ArrayList<Book> orderContent, String orderTotal, String orderStatus) {
        this.orderNumber = orderNumber;
        this.buyerID = buyerID;
        this.orderContent = orderContent;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }

    public Order() {
        this.orderNumber = -1;
        this.orderContent = new ArrayList<Book>();
        this.buyerID = -1;
        this.orderStatus = "";
        this.orderTotal = "";
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
    public int getBuyerID() {
        return buyerID;
    }
    public void setBuyerID(int buyerID) {
        this.buyerID = buyerID;
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
    public void addBook(Book book) {
        this.orderContent.add(book);
    }
    public void removeBook(Book book) {
        this.orderContent.remove(book);
    }
}