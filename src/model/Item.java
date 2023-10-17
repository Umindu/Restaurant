package model;

public class Item {
    private String name;
    private String price;
    private String img;
    private int ID;


    public String getName() {
        return name;
    }
    public String getPrice() {
        return price;
    }
    public int getID() {
        return ID;
    }
    public String getImg() {
        return img;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPrice(String price) {
        this.price = price;
    }
    public void setImg(String img) {
        this.img = img;
    }
    public void setID(int iD) {
        ID = iD;
    }
    
    
}
