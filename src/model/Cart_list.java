package model;

public class Cart_list {
    private String itemID;
    private String itemName;
    private String itemPrice;
    private String itemQnt;
    private String itemDiscount;
    private String itemeDiscoutntAddPrice;

    public String getID() {
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
    public String getDiscount() {
        return itemDiscount;
    }
    public String getItemeDiscoutntAddPrice() {
        return itemeDiscoutntAddPrice;
    }

    public void setID(String itemID) {
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
    public void setDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }
    public void setItemeDiscoutntAddPrice(String itemDiscount, String itemPrice) {
        this.itemeDiscoutntAddPrice = String.valueOf(Float.parseFloat(itemPrice) - (Float.parseFloat(itemPrice) * Float.parseFloat(itemDiscount) / 100));
    }
}
