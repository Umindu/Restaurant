package PlaceOrder;

import java.math.BigDecimal;
import java.math.RoundingMode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Cart_list;

public class CartItemController {
    private String ID;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label addDiscountPrice;

    @FXML
    private Label qnt;

    @FXML
    private Label productTotalPrice;

    @FXML
    private Label qntLabel;

    @FXML
    private TextField qntTextField;

    @FXML
    private Label disLabel;

    @FXML
    private TextField disTextField;

    @FXML
    private Button expandBtn;

    @FXML
    private Button removeBtn;

    @FXML
    private static VBox rightSceneVBox ;

    public static void setRightSceneVBox(VBox VBox) {
        rightSceneVBox = VBox;
    }

    @FXML
    void ExpandAnchorpane(ActionEvent event) {
        if (this.anchorPane.getPrefHeight() == 126) {
            expandBtn.setRotate(0);
            this.anchorPane.setPrefHeight(57);
            this.qntLabel.setVisible(false);
            this.qntTextField.setVisible(false);
            this.disLabel.setVisible(false);
            this.disTextField.setVisible(false);
            this.anchorPane.setStyle("-fx-border-color: transparent;");
        }else{
            expandBtn.setRotate(90);
            this.anchorPane.setPrefHeight(126);
            this.qntLabel.setVisible(true);
            this.qntTextField.setVisible(true);
            this.disLabel.setVisible(true);
            this.disTextField.setVisible(true);
            this.anchorPane.setStyle("-fx-effect: dropshadow(three-pass-box, #09aa29, 0, 0, -4, 0);");
        }  
    }

    @FXML
    void removeItem(ActionEvent event) {
        PlacOrderController remove = new PlacOrderController();
        remove.deleteItem(this.ID, rightSceneVBox);
    }

    public void setData(Cart_list Cart_list_item){
        ID = Cart_list_item.getID();
        name.setText(Cart_list_item.getName());
        price.setText("Rs. "+ new BigDecimal(Cart_list_item.getPrice()).setScale(2, RoundingMode.HALF_UP));
        qnt.setText(Cart_list_item.getQnt());
        disTextField.setText(Cart_list_item.getDiscount());
        qntTextField.setText(Cart_list_item.getQnt());
        addDiscountPrice.setText("Rs. "+ new BigDecimal(Cart_list_item.getItemeDiscoutntAddPrice()).setScale(2, RoundingMode.HALF_UP));
        productTotalPrice.setText("Rs. "+ new BigDecimal(Cart_list_item.getProductTotalPrice()).setScale(2, RoundingMode.HALF_UP));
        
        //select all text when click
        qntTextField.setOnMouseClicked(event -> {
            qntTextField.selectAll();
        });
        disTextField.setOnMouseClicked(event -> {
            disTextField.selectAll();
        });

        //calculate total price and qnt
        qntTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            qnt.setText(qntTextField.getText());
            //change object qnt value
            Cart_list_item.setQnt(qntTextField.getText());

            if (qntTextField.getText().isEmpty() || qntTextField.getText().equals("0")) {
                Cart_list_item.setProductTotalPrice(Cart_list_item.getDiscount(), Cart_list_item.getPrice(), "1");
                productTotalPrice.setText("Rs." + Cart_list_item.getProductTotalPrice());
            }else{ 
                Cart_list_item.setProductTotalPrice(Cart_list_item.getDiscount(), Cart_list_item.getPrice(), Cart_list_item.getQnt());
                productTotalPrice.setText("Rs." + Cart_list_item.getProductTotalPrice());
            }
        });

        // allow only numbers
        qntTextField.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });

        //calculate total price
        disTextField.textProperty().addListener((observable, oldValue, newValue) -> {
            addDiscountPrice.setText("Rs. "+(Float.parseFloat(price.getText().substring(4)) - (Float.parseFloat(price.getText().substring(4)) * Float.parseFloat(disTextField.getText()) / 100)));
            //change object discount value
            Cart_list_item.setDiscount(disTextField.getText());
            if (qntTextField.getText().isEmpty() || qntTextField.getText().equals("0") || disTextField.getText().isEmpty() || disTextField.getText().equals("0")) {
                Cart_list_item.setProductTotalPrice("0", Cart_list_item.getPrice(), "1");
                productTotalPrice.setText("Rs." + Cart_list_item.getProductTotalPrice());
            }else{
                Cart_list_item.setProductTotalPrice(Cart_list_item.getDiscount(), Cart_list_item.getPrice(), Cart_list_item.getQnt());
                productTotalPrice.setText("Rs." + Cart_list_item.getProductTotalPrice());
            }
        });

        // allow only numbers
        disTextField.addEventFilter(javafx.scene.input.KeyEvent.KEY_TYPED, event -> {
            if (!event.getCharacter().matches("[0-9]")) {
                event.consume();
            }
        });
    }

}
