package PlaceOrder;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import model.Cart_list;

public class CartItemController {
    private int ID;

    @FXML
    private AnchorPane anchorPane;

    @FXML
    private Label name;

    @FXML
    private Label price;

    @FXML
    private Label qnt;

    @FXML
    private Label qntLabel;

    @FXML
    private TextField qntTextField;

    @FXML
    private Label disLabel;

    @FXML
    private TextField disTextField;

    @FXML
    private Button narrowDownBtn;

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
        this.anchorPane.setPrefHeight(100);
        this.qntLabel.setVisible(true);
        this.qntTextField.setVisible(true);
        this.disLabel.setVisible(true);
        this.disTextField.setVisible(true);
        this.expandBtn.setVisible(false);
        this.narrowDownBtn.setVisible(true);
        this.anchorPane.setStyle("-fx-border-color: linear-gradient(to right, #09aa29 0%, transparent 2%);");
    }

    @FXML
    void NarrowDownAnchorpane(ActionEvent event) {
        this.anchorPane.setPrefHeight(40);
        this.qntLabel.setVisible(false);
        this.qntTextField.setVisible(false);
        this.disLabel.setVisible(false);
        this.disTextField.setVisible(false);
        this.expandBtn.setVisible(true);
        this.narrowDownBtn.setVisible(false);
        this.anchorPane.setStyle("-fx-border-color: transparent;");
    }

    @FXML
    void removeItem(ActionEvent event) {
        PlacOrderController yy = new PlacOrderController();
        yy.deleteItem(this.ID, rightSceneVBox);
    }

    public void setData(Cart_list Cart_list_item){
        ID = Cart_list_item.getID();
        name.setText(Cart_list_item.getName());
        price.setText(Cart_list_item.getPrice());
        qnt.setText(Cart_list_item.getQnt());
    }

}
