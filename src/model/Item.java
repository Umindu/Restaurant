package model;

public class Item {
    private String ID;
    private String img;
    private String name;
    private String cost;
    private String price;
    private String discount;
    private String category;

    
    public String getID() {
        return ID;
    }
    public String getImg() {
        return img;
    }
    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getCost() {
        return cost;
    }
    public String getPrice() {
        return price;
    }
    public String getDiscount() {
        return discount;
    }

    
    public void setID(String iD) {
        ID = iD;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setCategory(String category) {
        this.category = category;
    }
    public void setCost(String cost) {
        this.cost = cost;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setDiscount(String discount) {
        this.discount = discount;
    }
    
    
    
}
