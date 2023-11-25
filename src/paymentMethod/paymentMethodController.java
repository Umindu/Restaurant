package paymentMethod;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Order_details;

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

    //other payment method
    @FXML
    private TextField cardAmountTextfield;

    @FXML
    private TextField cardBillNumberTextfield;

    @FXML
    private TextField qrAmountTextfield;

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
            orderDetails.setCashPayAmount(cashAmountTextfield.getText());
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

    public void setOrderDetailsObject(Order_details orderDetails) {
        this.orderDetails = orderDetails;
        
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
        cusIdLabel.setText(orderDetails.getCustomerID());
        
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
    void confirmPayment(ActionEvent event) {
        System.out.println("Payment confirmed");
        System.out.println("Total pay amount: "+orderDetails.getTotalPayAmount());
        System.out.println("Balance: "+orderDetails.getBalance());
        System.out.println("Cash pay amount: "+orderDetails.getCashPayAmount());
        System.out.println("Card pay amount: "+orderDetails.getCardPayAmount());
        System.out.println("Card bill number: "+orderDetails.getCardBillNumber());
        System.out.println("QR pay amount: "+orderDetails.getQrPayAmount());
    }
}
