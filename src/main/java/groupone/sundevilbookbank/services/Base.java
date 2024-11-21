package groupone.sundevilbookbank.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.ResultSet;

import groupone.sundevilbookbank.models.Book;
import groupone.sundevilbookbank.models.Account;
import groupone.sundevilbookbank.models.Order;

import org.json.JSONArray;
import java.net.URISyntaxException;

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

    public static void createTables() {
        String accountsTable = 
            "CREATE TABLE IF NOT EXISTS accounts ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "username TEXT NOT NULL, "
            + "password TEXT NOT NULL, "
            + "email TEXT NOT NULL, "
            + "listings TEXT DEFAULT '[]', "
            + "orders TEXT DEFAULT '[]');";

        String ordersTable = 
            "CREATE TABLE IF NOT EXISTS orders ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "accountID INTEGER NOT NULL, "
            + "bookIDs TEXT DEFAULT '[]', "
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
            + "price REAL NOT NULL, "
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

    public static int getAccountID(String username, String password) {
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
    
    public static Account getAccount(String username, String Password) {
        String selectSQL = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        Account account = null;
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, Password);
    
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                //json array of book ids into arraylist of books
                JSONArray listings = new JSONArray(rs.getString("listings"));
                ArrayList<Book> books = new ArrayList<Book>();
                for (int i = 0; i < listings.length(); i++) {
                    books.add(getBook(listings.getInt(i)));
                }

                //json array of order ids into arraylist of orders
                JSONArray orders = new JSONArray(rs.getString("orders"));
                ArrayList<Order> orderList = new ArrayList<Order>();
                for (int i = 0; i < orders.length(); i++) {
                    orderList.add(getOrder(orders.getInt(i)));
                }

                account = new Account(
                    rs.getInt("id"),
                    rs.getString("username"),
                    rs.getString("password"),
                    rs.getString("email"),
                    books,
                    orderList

                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return account;
    }

    public static int insertAccount(String username, String password, String email) {
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
    
    public static void insertOrder(int bookID, String username, String price, String status) {
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

    public static void insertBook(int accountID, String title, String author, String genre, String subject, String ISBN, String condition, String description, double price, String status, String images) {
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
            insertBookStmt.setDouble(9, price);
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

    public static Book getBook(int ID) {
        String selectSQL = "SELECT * FROM books WHERE id = ?";
        Book book = null;
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setInt(1, ID);
    
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                book = new Book(
                    rs.getInt("id"),
                    rs.getInt("accountID"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("genre"),
                    rs.getString("subject"),
                    rs.getString("ISBN"),
                    rs.getString("condition"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("images")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return book;
    }

    public static Order getOrder(int ID) {
        String selectSQL = "SELECT * FROM orders WHERE id = ?";
        Order order = null;
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setInt(1, ID);
    
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                // json array of book ids into arraylist of books
                JSONArray bookIDs = new JSONArray(rs.getString("bookIDs"));
                ArrayList<Book> books = new ArrayList<Book>();
                for (int i = 0; i < bookIDs.length(); i++) {
                    books.add(getBook(bookIDs.getInt(i)));
                }
                order = new Order(
                    rs.getInt("id"),
                    rs.getInt("accountID"),
                    books,
                    rs.getString("price"),
                    rs.getString("status")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return order;
    }
    
    // get all subjects in sorted alphabetical list
    public static ArrayList<String> getAllSubjects() {
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
    public static ArrayList<String> getAllGenres() {
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
    public static ArrayList<Book> getAllBooks() {
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
                    rs.getDouble("price"),
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

    public static ArrayList<Book> searchBooks(
        String title,
        ArrayList<String> genres,
        ArrayList<String> subjects,
        ArrayList<String> authors,
        ArrayList<String> conditions,
        Double minPrice,
        Double maxPrice,
        ArrayList<String> isbns) {

        ArrayList<Book> books = new ArrayList<>();
        StringBuilder sql = new StringBuilder("SELECT * FROM books WHERE 1=1");

        // Append filters dynamically
        if (title != null && !title.isEmpty()) sql.append(" AND title LIKE ?");
        if (genres != null && !genres.isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < genres.size(); i++) {
                sql.append("genre LIKE ?");
                if (i < genres.size() - 1) sql.append(" OR ");
            }
            sql.append(")");
        }
        if (subjects != null && !subjects.isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < subjects.size(); i++) {
                sql.append("subject LIKE ?");
                if (i < subjects.size() - 1) sql.append(" OR ");
            }
            sql.append(")");
        }
        if (authors != null && !authors.isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < authors.size(); i++) {
                sql.append("author LIKE ?");
                if (i < authors.size() - 1) sql.append(" OR ");
            }
            sql.append(")");
        }
        if (conditions != null && !conditions.isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < conditions.size(); i++) {
                sql.append("condition LIKE ?");
                if (i < conditions.size() - 1) sql.append(" OR ");
            }
            sql.append(")");
        }
        if (minPrice != null) sql.append(" AND price >= ?");
        if (maxPrice != null) sql.append(" AND price <= ?");

        if (isbns != null && !isbns.isEmpty()) {
            sql.append(" AND (");
            for (int i = 0; i < isbns.size(); i++) {
                sql.append("ISBN LIKE ?");
                if (i < isbns.size() - 1) sql.append(" OR ");
            }
            sql.append(")");
        }

        try (Connection conn = connect();
            PreparedStatement stmt =  conn.prepareStatement(sql.toString())) {

            int paramIndex = 1;

            // Set title
            if (title != null && !title.isEmpty()) stmt.setString(paramIndex++, "%" + title + "%");

            // Set genres
            if (genres != null && !genres.isEmpty()) {
                for (String genre : genres) {
                    stmt.setString(paramIndex++, "%" + genre + "%");
                }
            }

            // Set subjects
            if (subjects != null && !subjects.isEmpty()) {
                for (String subject : subjects) {
                    stmt.setString(paramIndex++, "%" + subject + "%");
                }
            }

            // Set authors
            if (authors != null && !authors.isEmpty()) {
                for (String author : authors) {
                    stmt.setString(paramIndex++, "%" + author + "%");
                }
            }

            // Set conditions
            if (conditions != null && !conditions.isEmpty()) {
                for (String condition : conditions) {
                    stmt.setString(paramIndex++, "%" + condition + "%");
                }
            }

            // Set price range
            if (minPrice != null) stmt.setDouble(paramIndex++, minPrice);
            if (maxPrice != null) stmt.setDouble(paramIndex++, maxPrice);

            // Set ISBNs
            if (isbns != null && !isbns.isEmpty()) {
                for (String isbn : isbns) {
                    stmt.setString(paramIndex++, "%" + isbn + "%");
                }
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getInt("accountID"),
                    rs.getString("title"),
                    rs.getString("author"),
                    rs.getString("genre"),
                    rs.getString("subject"),
                    rs.getString("ISBN"),
                    rs.getString("condition"),
                    rs.getString("description"),
                    rs.getDouble("price"),
                    rs.getString("status"),
                    rs.getString("images")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return books;
    }
    
    public static void updateBook(int bookID, String title, String author, String genre, String subject, String ISBN, String condition, String description, String price, String status, String images) {
        String updateSQL = "UPDATE books SET title = ?, author = ?, genre = ?, subject = ?, ISBN = ?, condition = ?, description = ?, price = ?, status = ?, images = ? WHERE id = ?";
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(updateSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setString(3, genre);
            pstmt.setString(4, subject);
            pstmt.setString(5, ISBN);
            pstmt.setString(6, condition);
            pstmt.setString(7, description);
            pstmt.setString(8, price);
            pstmt.setString(9, status);
            pstmt.setString(10, images);
            pstmt.setInt(11, bookID);
    
            // Execute the query
            pstmt.executeUpdate();
            System.out.println("Book updated in base.db.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean validationLogin(String username, String password) {
        String selectSQL = "SELECT * FROM accounts WHERE username = ? AND password = ?";
        boolean isValid = false;
    
        try (Connection conn = connect();
            PreparedStatement pstmt = conn.prepareStatement(selectSQL)) {
            // Set parameters for the PreparedStatement
            pstmt.setString(1, username);
            pstmt.setString(2, password);
    
            // Execute the query
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                isValid = true;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isValid;

    }
}