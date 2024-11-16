package groupone.sundevilbookbank.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import groupone.sundevilbookbank.models.Book;

import org.json.JSONArray;
import java.net.URISyntaxException;
import java.sql.ResultSet;

public class Base {
    private final static String DATABASE_URL;

    static {
        DATABASE_URL = "jdbc:sqlite:" + getDatabasePath();
    }

    private static String getDatabasePath() {
        try {
            // Fetches the path of the database file in resources
            String path = Base.class.getResource("/groupone/database/base.db").toURI().getPath();
            if (path == null) {
                throw new IllegalArgumentException("Database file not found in classpath.");
            }
            return path;
        } catch (URISyntaxException e) {
            throw new RuntimeException("Failed to locate database file", e);
        }
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
        createTables();
    }

    public void createTables() {
        String accountsTable = 
            "CREATE TABLE IF NOT EXISTS accounts ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "username TEXT NOT NULL, "
            + "password TEXT NOT NULL, "
            + "email TEXT NOT NULL, "
            + "listings TEXT DEFAULT '[]');"; // JSON string of book IDs

        String ordersTable = 
            "CREATE TABLE IF NOT EXISTS orders ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "bookIDs INTEGER NOT NULL, "
            + "username TEXT NOT NULL, "
            + "price REAL NOT NULL, "
            + "status TEXT NOT NULL);";
            
        String booksTable = 
            "CREATE TABLE IF NOT EXISTS books ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "accountID INTEGER NOT NULL, "
            + "title TEXT NOT NULL, "
            + "author TEXT NOT NULL, "
            + "genre TEXT, "
            + "subject TEXT, "
            + "ISBN TEXT NOT NULL, "
            + "condition TEXT NOT NULL, "
            + "description TEXT, "
            + "price TEXT NOT NULL, "
            + "status TEXT NOT NULL, "
            + "images TEXT);";
        
        try (Connection conn = connect(); Statement stmt = conn.createStatement()) {
            stmt.execute(accountsTable);
            stmt.execute(ordersTable);
            stmt.execute(booksTable);

            System.out.println("tables created in base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public int getAccountID(String username, String password) {
        String selectSQL = "SELECT id FROM accounts WHERE username = ? AND password = ?";
        int accountID = -1;
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, password);
    
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                accountID = rs.getInt("id");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accountID;
    }

    public int insertAccount(String username, String password, String email) {
        String insertSQL = "INSERT INTO accounts (username, password, email) VALUES (?, ?, ?)";
        int accountId = -1;
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            pstmt.setString(3, email);
    
            // Execute the query
            int affectedRows = pstmt.executeUpdate();
            // Check if the insert was successful
            if (affectedRows > 0) {
                // Retrieve the generated account ID
                try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        accountId = generatedKeys.getInt(1);
                    }
                }
            }

            System.out.println("Account inserted into base.db with id: " + accountId + ".");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return accountId;
    }
    
    public void insertOrder(int bookID, String username, String price, String status) {
        String insertSQL = "INSERT INTO orders (bookID, username, price, status) VALUES (?, ?, ?, ?)";
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(insertSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setInt(1, bookID);
            pstmt.setString(2, username);
            pstmt.setString(3, price);
            pstmt.setString(4, status);
    
            // Execute the query
            pstmt.executeUpdate();
            System.out.println("Order inserted into base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void insertBook(int accountID, String title, String author, String genre, String subject, String ISBN, String condition, String description, String price, String status, String images) {
        String insertSQL = "INSERT INTO books (accountID, title, author, genre, subject, ISBN, condition, description, price, status, images) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String getBookIdSQL = "SELECT last_insert_rowid() AS id";
        String getListingsSQL = "SELECT listings FROM accounts WHERE id = ?";
        String updateListingsSQL = "UPDATE accounts SET listings = ? WHERE id = ?";


        try (Connection conn = connect();
            // Prepare the insertion statement
            PreparedStatement insertBookStmt = conn.prepareStatement(insertSQL);
            PreparedStatement getListingsStmt = conn.prepareStatement(getListingsSQL);
            PreparedStatement updateListingsStmt = conn.prepareStatement(updateListingsSQL)) {
            
            // insert new book into books table
            insertBookStmt.setInt(1, accountID);
            insertBookStmt.setString(2, title);
            insertBookStmt.setString(3, author);
            insertBookStmt.setString(4, genre);
            insertBookStmt.setString(5, subject);
            insertBookStmt.setString(6, ISBN);
            insertBookStmt.setString(7, condition);
            insertBookStmt.setString(8, description);
            insertBookStmt.setString(9, price);
            insertBookStmt.setString(10, status);
            insertBookStmt.setString(11, images);
            insertBookStmt.executeUpdate();
            System.out.println("Book inserted into base.db.");
            
            try (Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery(getBookIdSQL)) {
                if (rs.next()) {
                    int bookid = rs.getInt("id");
                    getListingsStmt.setInt(1, accountID);
                    ResultSet listingsResult = getListingsStmt.executeQuery();
                    String listingsJson = "[]";
                    if (listingsResult.next() && listingsResult.getString("listings") != null) {
                        listingsJson = listingsResult.getString("listings");
                    }
                    JSONArray listings = new JSONArray(listingsJson);
                    listings.put(bookid);

                    updateListingsStmt.setString(1, listings.toString());
                    updateListingsStmt.setInt(2, accountID);
                    updateListingsStmt.executeUpdate();
                    System.out.println("Listings updated in base.db for userID: " + accountID + ".");
                }
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // get all subjects in sorted alphabetical list
    public ArrayList<String> getAllSubjects() {
        String selectSQL = "SELECT DISTINCT subject FROM books ORDER BY subject ASC";
        ArrayList<String> subjects = new ArrayList<String>();
    
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                subjects.add(rs.getString("subject"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return subjects;
    }

    // get all genres in sorted alphabetical list
    public ArrayList<String> getAllGenres() {
        String selectSQL = "SELECT DISTINCT genre FROM books ORDER BY genre ASC";
        ArrayList<String> genres = new ArrayList<String>();
    
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                genres.add(rs.getString("genre"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return genres;
    }

    // get all books in the database, return as a list of Book objects
    public ArrayList<Book> getAllBooks() {
        String selectSQL = "SELECT * FROM books";
        ArrayList<Book> books = new ArrayList<Book>();
    
        try (Connection conn = connect();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(selectSQL)) {
            while (rs.next()) {
                Book book = new Book(
                    rs.getInt("id"),
                    rs.getInt("accountID"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("genre"),
                    rs.getString("subject"),
                    rs.getString("ISBN"),
                    rs.getString("condition"),
                    rs.getString("description"),
                    rs.getString("price"),
                    rs.getString("status"),
                    rs.getString("images")
                );
                books.add(book);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return books;
    }
}
