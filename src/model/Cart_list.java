package model;

public class Cart_list {
    private String itemID;
    private String itemName;
    private String itemPrice;
    private String itemQnt;
    private String itemDiscount;
    private String itemeDiscoutntAddPrice;
    private String productTotalPrice;

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
    public String getProductTotalPrice() {
        return productTotalPrice;
    }

    public void setID(String itemID) {
        this.itemID = itemID;
        System.out.println(this.itemID);
    }
    public void setName(String itemName) {
        this.itemName = itemName;
        System.out.println(this.itemName);
    }
    public void setPrice(String itemPrice) {
        this.itemPrice = itemPrice;
        System.out.println(this.itemPrice);
    }
    public void setQnt(String itemQnt) {
        this.itemQnt = itemQnt;
        System.out.println(this.itemQnt);
    }
    public void setDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
        System.out.println(this.itemDiscount);
    }
    public void setItemeDiscoutntAddPrice(String itemDiscount, String itemPrice) {
        this.itemeDiscoutntAddPrice = String.valueOf(Float.parseFloat(itemPrice) - (Float.parseFloat(itemPrice) * Float.parseFloat(itemDiscount) / 100));
        System.out.println(this.itemeDiscoutntAddPrice);
    }
    public void setProductTotalPrice(String itemDiscount, String itemPrice, String itemQnt) {
        this.productTotalPrice = String.valueOf((Float.parseFloat(itemPrice) - (Float.parseFloat(itemPrice) * Float.parseFloat(itemDiscount) / 100)) * Float.parseFloat(itemQnt));
        System.out.println(this.productTotalPrice);
    }
}
