package groupone.sundevilbookbank.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.net.URISyntaxException;

public class Base {
    private static final String DATABASE_URL;

    static {
        DATABASE_URL = "jdbc:sqlite:" + getDatabasePath();
    }

    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE_URL);
            System.out.println("SQLite Connection Successful.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
    
    public Base() {
        createAccountsTable();
        createOrderTable();
        createBookTable();
    }

    // Method to get the path of the database file
    private static String getDatabasePath() {
        try {
            // Fetches the path of the database file in resources
            return Base.class.getResource("/groupone/sundevilbookbank/database/base.db").toURI().getPath();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to locate database file", e);
        }
    }

    public void createAccountsTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS accounts ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL, "
                + "password TEXT NOT NULL);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Accounts table created in base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createOrderTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS orders ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "bookID INTEGER NOT NULL, "
                + "username TEXT NOT NULL, "
                + "price TEXT NOT NULL, "
                + "status TEXT NOT NULL);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Orders table created in base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void createBookTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS books ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "title TEXT NOT NULL, "
                + "author TEXT NOT NULL, "
                + "genre TEXT NOT NULL, "
                + "subject TEXT NOT NULL, "
                + "ISBN TEXT NOT NULL, "
                + "condition TEXT NOT NULL, "
                + "description TEXT NULL, "
                + "price TEXT NOT NULL, "
                + "status TEXT NOT NULL, "
                + "images TEXT NULL);";

        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(createTableSQL);
            System.out.println("Books table created in base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertAccount(String username, String password) {
        String insertSQL = "INSERT INTO accounts (username, password) VALUES (?, ?)";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.executeUpdate();
            System.out.println("Account inserted into base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertOrder(int bookID, String username, String price, String status) {
        String insertSQL = "INSERT INTO orders (bookID, username, price, status) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            pstmt.setInt(1, bookID);
            pstmt.setString(2, username);
            pstmt.setString(3, price);
            pstmt.setString(4, status);
            pstmt.executeUpdate();
            System.out.println("Order inserted into base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
