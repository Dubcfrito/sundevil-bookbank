package groupone.sundevilbookbank.utils;
import groupone.sundevilbookbank.models.Account;
import groupone.sundevilbookbank.models.Order;

public class GlobalData { 
    private static Account currentAccount;
    private static Order currentOrder;
    public static Account getCurrentAccount() {
        return currentAccount;
    }
    public static void setCurrentAccount(Account currentAccount) {
        GlobalData.currentAccount = currentAccount;
    }
    public static Order getCurrentOrder() {
        return currentOrder;
    }
    public static void setCurrentOrder(Order currentOrder) {
        GlobalData.currentOrder = currentOrder;
    }
}
