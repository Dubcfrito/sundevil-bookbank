package groupone.sundevilbookbank.models;

import java.util.ArrayList;

public class Seller extends Account{
    private ArrayList<Book> listings;

    public ArrayList<Book> getListings() {
        return listings;
    }
    public void setListings(ArrayList<Book> listings) {
        this.listings = listings;
    }
}
