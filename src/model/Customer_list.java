package model;

public class Customer_list {
    private String cusImgUrl;
    private String cusName;
    private String cusID;
    private String cusEmail;
    private String cusPhone;
    private String cusAddress;
    private String openBal;
    
    public Customer_list(String imgUrl, String id, String name, String email, String phone, String address, String openBal2) {
        this.cusImgUrl = imgUrl;
        this.cusID = id;
        this.cusName = name;
        this.cusEmail = email;
        this.cusPhone = phone;
        this.cusAddress = address;
        this.openBal = openBal2;
    }

    public String getCusImgUrl() {
        return cusImgUrl;
    }

    public String getCusName() {
        return cusName;
    }
    // public void setCusName(String cusName) {
    //     this.cusName = cusName;
    // }
    public String getCusID() {
        return cusID;
    }
    // public void setCusID(int cusID) {
    //     this.cusID = cusID;
    // }
    public String getCusEmail() {
        return cusEmail;
    }
    // public void setCusEmail(String cusEmail) {
    //     this.cusEmail = cusEmail;
    // }
    public String getCusPhone() {
        return cusPhone;
    }
    // public void setCusPhone(String cusPhone) {
    //     this.cusPhone = cusPhone;
    // }
    public String getCusAddress() {
        return cusAddress;
    }
    // public void setCusAddress(String cusAddress) {
    //     this.cusAddress = cusAddress;
    // }
    public String getOpenBal() {
        return openBal;
    }
    // public void setOpenBal(String openBal) {
    //     this.openBal = openBal;
    // }
}
