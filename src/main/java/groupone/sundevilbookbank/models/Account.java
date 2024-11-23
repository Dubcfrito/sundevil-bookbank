package groupone.sundevilbookbank.models;

import java.util.ArrayList;
import java.util.List;

public class Account {
    private int accountID;
    private String username;
    private String password;
    private String email;


    public Account(int accountID, String username, String password, String email) {
        this.accountID = accountID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    //default constructor
    public Account() {
        this.accountID = -1;
        this.username = "";
        this.password = "";
        this.email = "";
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
}
