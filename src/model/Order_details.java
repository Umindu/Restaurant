package model;

public class Order_details {
    private String subTotal = "0";
    private String serviceCharge;
    private String discount = "0";
    private String coupnCode;
    private String amount = "0";
    private boolean dicountMethod = true;
    private String customerID;
    private String tableId;

    public String getCustomerID() {
        return customerID;
    }
    public String getTableId() {
        return tableId;
    }
    public String getSubTotal() {
        return subTotal;
    }
    public String getServiceCharge() {
        return serviceCharge;
    }
    public String getDiscount() {
        return discount;
    }
    public boolean getDiscountMethod() {
        return dicountMethod;
    }
    public String getCoupnCode() {
        return coupnCode;
    }
    public String getAmount() {
        return amount;
    }


    public void setSubTotal(String subTotal) {
        this.subTotal = subTotal;
    }
    public void setServiceCharge(String serviceCharge) {
        this.serviceCharge = serviceCharge;
    }
    public void setDiscount( String discount) {
        this.discount = discount;
    }
    public void setDiscountMethod(boolean discountPercentage) {
        this.dicountMethod = discountPercentage;
    }
    public void setCoupnCode(String coupnCode) {
        this.coupnCode = coupnCode;
    }
    public void setAmount(String amount) {
        this.amount = amount ;
    }
    public void setCustomerID(String id) {
        this.customerID = id;
    }
    public void setTableID(String id) {
        this.tableId = id;
    }
}
