package groupone.sundevilbookbank;

public class Admin extends Account{
    private int adminID;
    private String adminLevel;
    private String log; //log of admin actions but will be stored in a database

    public Admin(int accountID, String username, String password, String email, int adminID, String adminLevel) {
        super(accountID, username, password, email);
        this.adminID = adminID;
        this.adminLevel = adminLevel;
    }
    public void upgradeAccount(Account account) {
        //create a new admin account with the same accountID, username, password, email, but add adminID and adminLevel
        
        //delete the old account

    }
    public void downgradeAccount(Account account) {
        //create a new user account with the same accountID, username, password, email, but remove adminID and adminLevel

        //delete the old account

    }
    public void deleteAccount(Account account) {
        //delete the account from the database
    }
    public void deleteBook(Book book) {
        //delete the book from the database
    }
    public void deleteOrder(Order order) {
        //delete the order from the database
    }
    public void deleteLog() {
        //delete the log from the database
    }
    public void printLog(int adminID) {
        //print the log, not if admin is not the same or above level of other admin
    }
    //getters and setters
    public int getAdminID() {
        return adminID;
    }
    public void setAdminID(int adminID) {
        this.adminID = adminID;
    }
    public String getAdminLevel() {
        return adminLevel;
    }
    public void setAdminLevel(String adminLevel) {
        this.adminLevel = adminLevel;
    }
    public String getLog() {
        return log;
    }
    public void addLog(String log) {
        this.log += log;
    }
}
