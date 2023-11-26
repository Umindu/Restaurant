package Orders.model;

public class orders {
    private String date;
    private String orderID;
    private String totalSales;

    public orders(String orderID, String date, String totalSales) {
        this.orderID = orderID;
        this.date = date;
        this.totalSales = totalSales;
    }

    public String getDate() {
        return date;
    }

    public String getOrderID() {
        return orderID;
    }

    public String getTotalSales() {
        return totalSales;
    }


    public void setDate(String date) {
        this.date = date;
    }

    public void setOrderID(String orderID) {
        this.orderID = orderID;
    }

    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }
}
