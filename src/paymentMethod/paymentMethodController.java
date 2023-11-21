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
import javafx.stage.Stage;
import model.Order_details;

public class paymentMethodController implements Initializable {

    @FXML
    private Label amountLabel2;

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
    private TextField amountTextfield;

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

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) {
        //select all text when click
        amountTextfield.setOnMouseClicked(event -> {
            amountTextfield.selectAll();
        });

        // allow only numbers and dot
        amountTextfield.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            char inputChar = event.getCharacter().charAt(0);
            System.out.println(inputChar);
            if (!(Character.isDigit(inputChar) || (inputChar == '.' && !amountTextfield.getText().contains(".")))) {
                event.consume();
            }
        });

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
        if (amountTextfield.getLength() > 0) {
            amountTextfield.setText(amountTextfield.getText(0, amountTextfield.getLength() - 1));
            setOrderDetails();
        }
    }

    @FXML
    void pressClear(ActionEvent event) {
        amountTextfield.clear();
        setOrderDetails();
    }

    @FXML
    void pressDot(ActionEvent event) {
        if (amountTextfield.getText().contains(".")) {
            event.consume();
        }else{
            amountTextfield.appendText(".");
            setOrderDetails();
        }
    }

    @FXML
    void pressDoubleZero(ActionEvent event) {
        amountTextfield.appendText("00");
        setOrderDetails();
    }

    @FXML
    void pressZero(ActionEvent event) {
        amountTextfield.appendText("0");
        setOrderDetails();
    }

    @FXML
    void pressNine(ActionEvent event) {
        amountTextfield.appendText("9");
        setOrderDetails();
    }

    @FXML
    void pressEight(ActionEvent event) {
        amountTextfield.appendText("8");
        setOrderDetails();
    }

    @FXML
    void pressSeven(ActionEvent event) {
        amountTextfield.appendText("7");
        setOrderDetails();
    }

    @FXML
    void pressSix(ActionEvent event) {
        amountTextfield.appendText("6");
        setOrderDetails();
    }

    @FXML
    void pressFive(ActionEvent event) {
        amountTextfield.appendText("5");
        setOrderDetails();
    }

    @FXML
    void pressFour(ActionEvent event) {
        amountTextfield.appendText("4");
        setOrderDetails();
    }

    @FXML
    void pressThree(ActionEvent event) {
        amountTextfield.appendText("3");
        setOrderDetails();
    }

    @FXML
    void pressTwo(ActionEvent event) {
        amountTextfield.appendText("2");
        setOrderDetails();
    }

    @FXML
    void pressOne(ActionEvent event) {
        amountTextfield.appendText("1");
        setOrderDetails();
    }

    private void setOrderDetails() {
        cusIdLabel.setText(orderDetails.getCustomerID());
        
        tableNumbersLabel.setText(orderDetails.getTables().toString());

        if (amountTextfield.getLength() > 0) {
            orderDetails.setPayableAmount(amountTextfield.getText());
            orderDetails.setBalance(String.valueOf(Float.parseFloat(orderDetails.getPayableAmount()) - Float.parseFloat(orderDetails.getGrandTotal())));
        } else{   
            orderDetails.setPayableAmount("0.00");
            orderDetails.setBalance("-"+orderDetails.getGrandTotal());
        }
        
        subTotalLabel.setText("Rs. "+ orderDetails.getSubTotal());

        serviceChargeLabel.setText("Rs. "+ orderDetails.getServiceCharge());

        if (orderDetails.getDiscountMethod()) {
            discountLabel.setText("("+orderDetails.getDiscount() +"%)  Rs. " + new BigDecimal((Float.parseFloat(orderDetails.getSubTotal()) * Float.parseFloat(orderDetails.getDiscount()) / 100)).setScale(2, RoundingMode.HALF_UP));
        }else{
            discountLabel.setText("Rs. "+ new BigDecimal(orderDetails.getDiscount()).setScale(2, RoundingMode.HALF_UP));
        }

        couponLabel.setText("#"+orderDetails.getCoupnCode());

        grandTotalLabel.setText("Rs. "+ orderDetails.getGrandTotal());

        amountLabel.setText("Rs. "+orderDetails.getPayableAmount());
        amountLabel2.setText("Rs. "+ orderDetails.getPayableAmount());
            
        balanceLabel.setText("Rs. "+ orderDetails.getBalance());

    }  
}
