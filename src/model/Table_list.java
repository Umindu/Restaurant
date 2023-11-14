package model;

public class Table_list {
    private String tableID;
    private String tableName;
    private String tableimg;
    private String tablestates;
    private String tableSheetsCount;

    public Table_list(String timg, String tID, String tName, String tstates, String tSheetsCount){
        this.tableID = tID;
        this.tableName = tName;
        this.tableimg = timg;
        this.tablestates = tstates;
        this.tableSheetsCount = tSheetsCount;
    }

    public String getTableID() {
        return tableID;
    }
    public String getTableName() {
        return tableName;
    }
    public String getTableimg() {
        return tableimg;
    }
    public String getTablestates() {
        return tablestates;
    }
    public String getTableSheetsCount() {
        return tableSheetsCount;
    }

    
}
