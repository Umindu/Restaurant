package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

public class Order_details {
    private String customerID = "";
    private ArrayList<String> tables = new ArrayList<>();
    private String subTotal = "0.00";
    private String serviceCharge = "0.00";
    private boolean dicountMethod = true;
    private String discount = "0";
    private String coupnCode = "None";
    private String grandTotal = "0.00";
    private String payAmount = "0.00";
    private String balance = "0.00";

    
    public String getCustomerID() {
        return customerID;
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
    public String getGrandTotal() {
        return grandTotal;
    }
    public ArrayList<String> getTables() {
        return tables;
    }
    public String getPayAmount() {
        return payAmount;
    }
    public String getBalance() {
        return balance;
    }


    public void setSubTotal(String subTotal) {
        this.subTotal = String.valueOf(new BigDecimal(subTotal).setScale(2, RoundingMode.HALF_UP));
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
    public void setGrandTotal(String grandTotal) {
        this.grandTotal = String.valueOf(new BigDecimal(grandTotal).setScale(2, RoundingMode.HALF_UP));
    }
    public void setCustomerID(String id) {
        this.customerID = id;
    }
    public void setTables(ArrayList<String> tables) {
        this.tables = tables;
    }
    public void setBalance(String balance) {
        this.balance = balance;
    }
    public void setPayAmount(String payableAmount) {
        this.payAmount = String.valueOf(new BigDecimal(payableAmount).setScale(2, RoundingMode.HALF_UP));
    }
}
