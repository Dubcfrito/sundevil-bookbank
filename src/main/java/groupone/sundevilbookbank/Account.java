package groupone.sundevilbookbank;

import java.util.ArrayList;

public class Account {
    private int accountID;
    private String username;
    private String password;
    private String email;
    private ArrayList<Book> listings;


    public Account(int accountID, String username, String password, String email) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
        this.listings = new ArrayList<Book>();
    }

    //default constructor
    public Account() {
        this.accountID = 0;
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
}
