package groupone.sundevilbookbank.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountID;
    private String username;
    private String password;
    private String email;
    private ArrayList<Book> listings;
    private List<Order> orders;


    public Account(int accountID, String username, String password, String email, ArrayList<Book> listings, ArrayList<Order> orders) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.listings = listings;
        this.orders = orders;
    }

    //default constructor
    public Account() {
        this.accountID = -1;
        this.username = "";
        this.password = "";
        this.email = "";
        this.listings = new ArrayList<Book>();
    }
    
    //getters and setters
    public int getAccountID() {
        return accountID;
    }
    public void setAccountID(int accountID) {
        this.accountID = accountID;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public ArrayList<Book> getListings() {
        return listings;
    }
    public void setListings(ArrayList<Book> listings) {
        this.listings = listings;
    }
    public void addListing(Book book) {
        this.listings.add(book);
    }
    public void removeListing(Book book) {
        this.listings.remove(book);
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
    public void addOrder(Order order) {
        this.orders.add(order);
    }
}
