package model;

public class Table_list {
    private int ID;
    private int tableNum;
    private int numOfGuest;
    private String img;

    public int getID() {
        return ID;
    }
    public void setID(int iD) {
        ID = iD;
    }
    public int getTableNum() {
        return tableNum;
    }
    public void setTableNum(int tableNum) {
        this.tableNum = tableNum;
    }
    public int getNumOfGuest() {
        return numOfGuest;
    }
    public void setNumOfGuest(int numOfGuest) {
        this.numOfGuest = numOfGuest;
    }
    public String getImg() {
        return img;
    }
    public void setImg(String img) {
        this.img = img;
    }  
}
