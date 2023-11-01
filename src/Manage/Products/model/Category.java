package Manage.Products.model;

import javafx.scene.layout.HBox;

public class Category {
    private String catId;
    private String catName;
    private HBox actionBtnContainer;

    public Category(String cId, String cName, HBox actionBtnContainer) {
        catId = cId;
        catName = cName;
        this.actionBtnContainer = actionBtnContainer;
    }

    

    public String getCategoryId() {
        return catId;
    }

    public String getCategoryName() {
        return catName;
    }

    public HBox getCategoryAction() {
        return this.actionBtnContainer;
    }
}
