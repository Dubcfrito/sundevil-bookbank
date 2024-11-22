package groupone.sundevilbookbank.models;

import java.util.ArrayList;

import groupone.sundevilbookbank.services.Base;

public class Order {
    private int orderNumber;
    private ArrayList<Book> orderContent;
    private int buyerID;
    private String orderStatus;
    private double orderTotal;

    //constructors
    public Order(int orderNumber, int buyerID, ArrayList<Book> orderContent, double orderTotal, String orderStatus) {
        this.orderNumber = orderNumber;
        this.buyerID = buyerID;
        this.orderContent = orderContent;
        this.orderTotal = orderTotal;
        this.orderStatus = orderStatus;
    }

    public Order(int buyerID) {
        this.orderNumber = -1;
        this.orderContent = new ArrayList<Book>();
        this.buyerID = buyerID;
        this.orderStatus = "";
        this.orderTotal = 0;
    }

    public void updateOrderTotal() {
        this.orderTotal = 0;
        for (Book book : orderContent) {
            orderTotal += book.getPrice();
        }
    }

    public void placeOrder() {
        this.orderStatus = "Placed";
        // grab book ids from orderContent and put into arraylist<int>
        ArrayList<Integer> bookIDs = new ArrayList<>();
        for (Book book : orderContent) {
            bookIDs.add(book.getBookID());
        }
        Base.insertOrder(this.buyerID, bookIDs, this.orderTotal, this.orderStatus);
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
    public double getOrderTotal() {
        return orderTotal;
    }
    public void setOrderTotal(double orderTotal) {
        this.orderTotal = orderTotal;
    }
    public void addBook(Book book) {
        this.orderContent.add(book);
    }
    public void removeBook(Book book) {
        this.orderContent.remove(book);
    }
}