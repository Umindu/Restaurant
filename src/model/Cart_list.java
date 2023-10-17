package model;

public class Cart_list {
    private int itemID;
    private String itemName;
    private String itemPrice;
    private String itemQnt;
    // private String itemDiscount;

    public int getID() {
        return itemID;
    }
    public String getName() {
        return itemName;
    }
    public String getPrice() {
        return itemPrice;
    }
    public String getQnt() {
        return itemQnt;
    }
    // public String getDiscount() {
    //     return itemDiscount;
    // }

    public void setID(int itemID) {
        this.itemID = itemID;
    }
    public void setName(String itemName) {
        this.itemName = itemName;
    }
    public void setPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void setQnt(String itemQnt) {
        this.itemQnt = itemQnt;
    }
    // public void setDiscount(String itemDiscount) {
    //     this.itemDiscount = itemDiscount;
    // }
}
