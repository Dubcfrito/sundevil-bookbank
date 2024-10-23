package groupone.sundevilbookbank;

public class Book {
    private int bookID;
    private String title;
    private String author;
    private String genre;
    private String subject;
    private String ISBN;
    private String condition;
    private String description;
    private String price;
    private String status;
    private String images; // tbd how to store images
    
    public Book(int bookID, String title, String author, String genre, String subject, String ISBN, String condition, String description, String price, String status, String images) {
        this.bookID = bookID;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.subject = subject;
        this.ISBN = ISBN;
        this.condition = condition;
        this.description = description;
        this.price = price;
        this.status = status;
        this.images = images;
    }
    
    //getters and setters
    public int getBookID() {
        return bookID;
    }
    public void setBookID(int bookID) {
        this.bookID = bookID;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }
    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String iSBN) {
        ISBN = iSBN;
    }
    public String getCondition() {
        return condition;
    }
    public void setCondition(String condition) {
        this.condition = condition;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public String getPrice() {
        return price;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public String getStatus() {
        return status;
    }
    public void setStatus(String status) {
        this.status = status;
    }
    public String getImages() {
        return images;
    }
    public void setImages(String images) {
        this.images = images;
    }
}
