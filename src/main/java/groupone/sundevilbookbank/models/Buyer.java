package groupone.sundevilbookbank.models;

import java.util.List;

public class Buyer extends Account{
    private List<Order> orders;

    public List<Order> getOrders() {
        return orders;
    }
    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }
}
