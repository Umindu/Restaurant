package Manage.Products.model;

public class Category {
    private String catId;
    private String catName;
    private String catAction;

    public Category(String cId, String cName, String action) {
        catId = cId;
        catName = cName;
        catAction = action;
    }

    public String getCategoryId() {
        return catId;
    }

    public String getCategoryName() {
        return catName;
    }

    public String getCategoryAction() {
        return catAction;
    }
}
