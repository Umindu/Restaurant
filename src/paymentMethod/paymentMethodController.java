package paymentMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import java.util.ResourceBundle;
import DataBase.DBConnect;
import PlaceOrder.PlaceOrderController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Cart_list;
import model.Order_details;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class paymentMethodController implements Initializable {
    @FXML
    private Button cashTabBtn;

    @FXML
    private Button otherMethodBtn;
    
    @FXML
    private VBox cachVbox;

    @FXML
    private VBox otherMethodVbox;

    @FXML
    private Label payableAmountLabel;

    @FXML
    private Label cusNameLabel2;

    @FXML
    private Label cusIdLabel;

    @FXML
    private Label cusNameLabel;

    @FXML
    private Label invoiceIdLabel;

    @FXML
    private Label tableNumbersLabel;

    @FXML
    private TextField cashAmountTextfield;

    @FXML
    private Label subTotalLabel;

    @FXML
    private Label serviceChargeLabel;

    @FXML
    private Label discountLabel;

    @FXML
    private Label couponLabel;

    @FXML
    private Label grandTotalLabel;

    @FXML
    private Label amountLabel;

    @FXML
    private Label balanceLabel;

    private Order_details orderDetails;
    private List<Cart_list> cartItemList;

    //other payment method
    @FXML
    private TextField cardAmountTextfield;

    @FXML
    private TextField cardBillNumberTextfield;

    @FXML
    private TextField qrAmountTextfield;

    //place order controller
    private static PlaceOrderController placeOrderController;

    public static void setPlacOrderController(PlaceOrderController placeOrderController2) {
        placeOrderController = placeOrderController2;
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //select all text when click
        cashAmountTextfield.setOnMouseClicked(event -> {
            cashAmountTextfield.selectAll();
        });
        cardAmountTextfield.setOnMouseClicked(event -> {
            cardAmountTextfield.selectAll();
        });
        qrAmountTextfield.setOnMouseClicked(event -> {
            qrAmountTextfield.selectAll();
        });

        // allow only numbers and dot
        cashAmountTextfield.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            char inputChar = event.getCharacter().charAt(0);
            if (!(Character.isDigit(inputChar) || (inputChar == '.' && !cashAmountTextfield.getText().contains(".")))) {
                event.consume();
            }
        });
        cardAmountTextfield.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            char inputChar = event.getCharacter().charAt(0);
            if (!(Character.isDigit(inputChar) || (inputChar == '.' && !cardAmountTextfield.getText().contains(".")))) {
                event.consume();
            }
        });
        qrAmountTextfield.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            char inputChar = event.getCharacter().charAt(0);
            if (!(Character.isDigit(inputChar) || (inputChar == '.' && !qrAmountTextfield.getText().contains(".")))) {
                event.consume();
            }
        });

        //set order details
        cashAmountTextfield.setOnKeyReleased(event -> {
            setOrderDetails();
        });

        qrAmountTextfield.setOnKeyReleased(event -> {
            orderDetails.setQrPayAmount(qrAmountTextfield.getText());
            setOrderDetails();
        });

        cardAmountTextfield.setOnKeyReleased(event -> {
            orderDetails.setCardPayAmount(cardAmountTextfield.getText());
            setOrderDetails();
        });

        cardBillNumberTextfield.setOnKeyReleased(event -> {
            orderDetails.setCardBillNumber(cardBillNumberTextfield.getText());
        });

        qrAmountTextfield.setOnKeyReleased(event -> {
            orderDetails.setQrPayAmount(qrAmountTextfield.getText());
            setOrderDetails();
        });

        cashTabBtn.setStyle("-fx-border-color: #fc8019; -fx-text-fill: #fc8019;");
    }

    public void setOrderDetailsObject(Order_details orderDetails, List<Cart_list> cartItemList) {
        this.orderDetails = orderDetails;
        this.cartItemList = cartItemList;
        setOrderDetails();
    }

    @FXML
    void popupClose(ActionEvent event) {
        Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
        stage.close();
    }

    @FXML
    void pressBackspace(ActionEvent event) {
        if (cashAmountTextfield.getLength() > 0) {
            cashAmountTextfield.setText(cashAmountTextfield.getText(0, cashAmountTextfield.getLength() - 1));
            setOrderDetails();
        }
    }

    @FXML
    void pressClear(ActionEvent event) {
        cashAmountTextfield.clear();
        setOrderDetails();
    }

    @FXML
    void pressDot(ActionEvent event) {
        if (cashAmountTextfield.getText().contains(".")) {
            event.consume();
        }else{
            cashAmountTextfield.appendText(".");
            setOrderDetails();
        }
    }

    @FXML
    void pressDoubleZero(ActionEvent event) {
        cashAmountTextfield.appendText("00");
        setOrderDetails();
    }

    @FXML
    void pressZero(ActionEvent event) {
        cashAmountTextfield.appendText("0");
        setOrderDetails();
    }

    @FXML
    void pressNine(ActionEvent event) {
        cashAmountTextfield.appendText("9");
        setOrderDetails();
    }

    @FXML
    void pressEight(ActionEvent event) {
        cashAmountTextfield.appendText("8");
        setOrderDetails();
    }

    @FXML
    void pressSeven(ActionEvent event) {
        cashAmountTextfield.appendText("7");
        setOrderDetails();
    }

    @FXML
    void pressSix(ActionEvent event) {
        cashAmountTextfield.appendText("6");
        setOrderDetails();
    }

    @FXML
    void pressFive(ActionEvent event) {
        cashAmountTextfield.appendText("5");
        setOrderDetails();
    }

    @FXML
    void pressFour(ActionEvent event) {
        cashAmountTextfield.appendText("4");
        setOrderDetails();
    }

    @FXML
    void pressThree(ActionEvent event) {
        cashAmountTextfield.appendText("3");
        setOrderDetails();
    }

    @FXML
    void pressTwo(ActionEvent event) {
        cashAmountTextfield.appendText("2");
        setOrderDetails();
    }

    @FXML
    void pressOne(ActionEvent event) {
        cashAmountTextfield.appendText("1");
        setOrderDetails();
    }

    private void setOrderDetails() {
        invoiceIdLabel.setText("Order ID #"+orderDetails.getInvoiceID());

        if (orderDetails.getCustomerName().isEmpty()) {
            cusNameLabel.setText("My Customer");
            cusNameLabel2.setText("My Customer");
            cusIdLabel.setText("#000");
        }else{
            cusNameLabel.setText(orderDetails.getCustomerName());
            cusNameLabel2.setText(orderDetails.getCustomerName());
            cusIdLabel.setText("#"+orderDetails.getCustomerID());
        }

        orderDetails.setCashPayAmount(cashAmountTextfield.getText());
        
        
        if (orderDetails.getTables().isEmpty()) {
            tableNumbersLabel.setText("Takeaway");
        }else{
            tableNumbersLabel.setText(orderDetails.getTables().toString().substring(1, orderDetails.getTables().toString().length() - 1));
        }

        if (cashAmountTextfield.getLength() > 0 || cardAmountTextfield.getLength() > 0 || qrAmountTextfield.getLength() > 0) {
            orderDetails.setTotalPayAmount(String.valueOf(Float.parseFloat(cashAmountTextfield.getText().isEmpty() ? "0" : cashAmountTextfield.getText()) + 
            Float.parseFloat(cardAmountTextfield.getText().isEmpty() ? "0" : cardAmountTextfield.getText()) + 
            Float.parseFloat(qrAmountTextfield.getText().isEmpty() ? "0" : qrAmountTextfield.getText())));

            orderDetails.setBalance(String.valueOf(Float.parseFloat(orderDetails.getTotalPayAmount()) - Float.parseFloat(orderDetails.getGrandTotal())));
        } else{   
            orderDetails.setTotalPayAmount("0.00");
            orderDetails.setBalance("-"+orderDetails.getGrandTotal());
        }
        
        subTotalLabel.setText("Rs. "+ orderDetails.getSubTotal());

        serviceChargeLabel.setText("Rs. "+ orderDetails.getServiceCharge());

        if (orderDetails.getDiscountMethod()) {
            discountLabel.setText("("+orderDetails.getDiscount() +"%)  Rs. " + new BigDecimal((Float.parseFloat(orderDetails.getSubTotal()) * Float.parseFloat(orderDetails.getDiscount()) / 100)).setScale(2, RoundingMode.HALF_UP));
        }else{
            discountLabel.setText("Rs. "+ new BigDecimal(orderDetails.getDiscount()).setScale(2, RoundingMode.HALF_UP));
        }

        if (orderDetails.getCoupnCode() == "None") {
            couponLabel.setText("None");
        }else{
            couponLabel.setText("#"+orderDetails.getCoupnCode());
        }

        grandTotalLabel.setText("Rs. "+ orderDetails.getGrandTotal());

        amountLabel.setText("Rs. "+orderDetails.getTotalPayAmount());
        payableAmountLabel.setText("Rs. "+ orderDetails.getGrandTotal());
            
        balanceLabel.setText("Rs. "+ new BigDecimal(orderDetails.getBalance()).setScale(2, RoundingMode.HALF_UP));

    }  

    @FXML
    void showCashTab(ActionEvent event) {
        otherMethodBtn.setStyle("-fx-border-color: #7e7e7e; -fx-text-fill: #000;");
        cashTabBtn.setStyle("-fx-border-color: #fc8019; -fx-text-fill: #fc8019;");
        otherMethodVbox.setVisible(false);
        otherMethodVbox.setPrefHeight(0);
        cachVbox.setVisible(true);
        cachVbox.setPrefHeight(517.6);
    }

    @FXML
    void showOtherMethodTab(ActionEvent event) {
        cashTabBtn.setStyle("-fx-border-color: #7e7e7e; -fx-text-fill: #000;");
        otherMethodBtn.setStyle("-fx-border-color: #fc8019; -fx-text-fill: #fc8019;");
        cachVbox.setVisible(false);
        cachVbox.setPrefHeight(0);
        otherMethodVbox.setVisible(true);
        otherMethodVbox.setPrefHeight(517.6);
    }

    @FXML
    void confirmPayment(ActionEvent event) throws SQLException {
        Statement statement = DBConnect.connectToDB().createStatement();
        for (Cart_list cart_item : cartItemList) {
            statement.execute("insert into Order_Product values ('" + orderDetails.getInvoiceID() + "', '" + cart_item.getID() + "', '" + cart_item.getQnt()
            + "', '" + cart_item.getCost() + "', '" + cart_item.getPrice() + "', '" + cart_item.getDiscount() + "', '" + cart_item.getProductTotalPrice() + "')");
        }
        System.out.println("Order_Product table updated");
        
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String formattedDate = dateFormat.format(currentDate);

        statement.execute("insert into Order_Invoice values ('" + orderDetails.getInvoiceID() + "', '" + formattedDate + "', '" + orderDetails.getCustomerID() + "', '" + orderDetails.getTables().toString().substring(1, orderDetails.getTables().toString().length() - 1) + "', '" + orderDetails.getSubTotal() + "', '" + orderDetails.getServiceCharge() + "', '" + orderDetails.getDiscount() + "', '" + orderDetails.getDiscountMethod() + "', '" + orderDetails.getCoupnCode() + "', '" + orderDetails.getGrandTotal() + "', '" + orderDetails.getTotalPayAmount() + "', '" + orderDetails.getCashPayAmount() + "', '" + orderDetails.getCardPayAmount() + "', '" + orderDetails.getCardBillNumber() + "', '" + orderDetails.getQrPayAmount() + "', '" + orderDetails.getBalance() + "')");
        System.out.println("Order_Invoice table updated");

        popupClose(event);

        //clear cart
        orderDetails.setInvoiceID("");
        orderDetails.setCustomerID("");
        orderDetails.setSubTotal("0.00");
        orderDetails.setServiceCharge("0.00");
        orderDetails.setDiscount("0.00");
        orderDetails.setDiscountMethod(true);
        orderDetails.setCoupnCode("None");
        orderDetails.setGrandTotal("0.00");
        orderDetails.setTotalPayAmount("0.00");
        orderDetails.setCashPayAmount("0.00");
        orderDetails.setCardPayAmount("0.00");
        orderDetails.setCardBillNumber("");
        orderDetails.setQrPayAmount("0.00");
        orderDetails.setBalance("0.00");
        orderDetails.setTables(new ArrayList<String>());

        //refresh plase order page
        placeOrderController.resetPlaceOrder();

    }
}
