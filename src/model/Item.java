package model;

public class Item {
    private String name;
    private String price;
    private String img;
    private String ID;
    private String category;


    public String getName() {
        return name;
    }
    public String getCategory() {
        return category;
    }
    public String getPrice() {
        return price;
    }
    public String getID() {
        return ID;
    }
    public String getImg() {
        return img;
    }
    public void setName(String name) {
        this.name = name;
    }
    
    public void setCategory(String category) {
        this.category = category;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setID(String iD) {
        ID = iD;
    }
    
    
}
