package Cashier.model;

public class InvoiceDetails {
    private String date;
    private String invoiceID;
    private String cashPayment;
    private String otherPayment;
    private String totalSales;
    private String note;

    public InvoiceDetails(String date, String invoiceID, String cashPayment, String otherPayment, String totalSales, String note) {
        this.date = date;
        this.invoiceID = invoiceID;
        this.cashPayment = cashPayment;
        this.otherPayment = otherPayment;
        this.totalSales = totalSales;
        this.note = note;
    }

    public String getDate() {
        return date;
    }
    public String getInvoiceID() {
        return invoiceID;
    }
    public String getCashPayment() {
        return cashPayment;
    }
    public String getOtherPayment() {
        return otherPayment;
    }
    public String getTotalSales() {
        return totalSales;
    }
    public String getNote() {
        return note;
    }

    public void setDate(String date) {
        this.date = date;
    }
    public void setInvoiceID(String invoiceID) {
        this.invoiceID = invoiceID;
    }
    public void setCashPayment(String cashPayment) {
        this.cashPayment = cashPayment;
    }
    public void setOtherPayment(String otherPayment) {
        this.otherPayment = otherPayment;
    }
    public void setTotalSales(String totalSales) {
        this.totalSales = totalSales;
    }
    public void setNote(String note) {
        this.note = note;
    }
}
