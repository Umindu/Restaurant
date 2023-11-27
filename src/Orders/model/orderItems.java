package Orders.model;

public class orderItems {
    private String itemName;
    private String itemPrice;
    private String itemQuantity;
    private String itemTotalPrice;
    private String itemDiscount;

    // public orderItems(String itemName, String itemPrice, String itemQuantity, String itemTotalPrice, String itemDiscount) {
    //     this.itemName = itemName;
    //     this.itemPrice = itemPrice;
    //     this.itemQuantity = itemQuantity;
    //     this.itemTotalPrice = itemTotalPrice;
    //     this.itemDiscount = itemDiscount;
    // }

    public String getItemName() {
        return itemName;
    }
    public String getItemPrice() {
        return itemPrice;
    }
    public String getItemQuantity() {
        return itemQuantity;
    }
    public String getItemTotalPrice() {
        return itemTotalPrice;
    }
    public String getItemDiscount() {
        return itemDiscount;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public void setItemPrice(String itemPrice) {
        this.itemPrice = itemPrice;
    }
    public void setItemQuantity(String itemQuantity) {
        this.itemQuantity = itemQuantity;
    }
    public void setItemTotalPrice(String itemTotalPrice) {
        this.itemTotalPrice = itemTotalPrice;
    }
    public void setItemDiscount(String itemDiscount) {
        this.itemDiscount = itemDiscount;
    }
}
