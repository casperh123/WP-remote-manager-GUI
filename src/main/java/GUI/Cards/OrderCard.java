package GUI.Cards;

import Components.Order.Order;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.awt.*;

public class OrderCard extends ListCard {
    public OrderCard(Order order) {
        super();
        this.getChildren().add(new Text(order.getCartHash()));
    }
}
