package Manage.model;

import javafx.scene.layout.HBox;

public class Table {
    private String imgUrl;
    private String tableId;
    private String tableName;
    private String tableStates;
    private HBox actionBtnContainer;

    public Table(String tImg, String tId, String tName, String tStates, HBox actionBtnContainer) {
        imgUrl = tImg;
        tableId = tId;
        tableName = tName;
        tableStates = tStates;
        this.actionBtnContainer = actionBtnContainer;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public String getTableId() {
        return tableId;
    }

    public String getTableName() {
        return tableName;
    }

    public String getTableStates() {
        return tableStates;
    }

    public HBox getTableAction() {
        return actionBtnContainer;
    }

    
}
