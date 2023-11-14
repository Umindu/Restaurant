package Manage.model;

import javafx.scene.layout.HBox;

public class Product {
    private String imgUrl;
    private String proId;
    private String proName;
    private String proCategory;
    private String proCost;
    private String proDiscount;
    private String proPrice;
    private String proDescription;
    private HBox actionBtnContainer;

    public Product(String imgUrl, String proId, String proName, String proCategory, String proCost, String proDiscount, String proPrice, String proDescription, HBox actionBtnContainer) {
        this.imgUrl = imgUrl;
        this.proId = proId;
        this.proName = proName;
        this.proCategory = proCategory;
        this.proCost = proCost;
        this.proDiscount = proDiscount;
        this.proPrice = proPrice;
        this.proDescription = proDescription;
        this.actionBtnContainer = actionBtnContainer;
    }

    
    public String getImgUrl() {
        return imgUrl;
    }
    public String getProductId() {
        return proId;
    }
    public String getProductName() {
        return proName;
    }
    public String getProductCategory() {
        return proCategory;
    }
    public String getProductCost() {
        return proCost;
    }
    public String getProductDiscount() {
        return proDiscount;
    }
    public String getProductPrice() {
        return proPrice;
    }
    public String getProductDescription() {
        return proDescription;
    }
    public HBox getProductAction() {
        return actionBtnContainer;
    }
}
