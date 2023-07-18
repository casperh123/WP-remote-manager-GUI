package GUI.Cards;

import Components.Order.Order;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

public class OrderCard extends ListCard {

    private Order order;
    private Label orderNumber;
    private Label customerName;
    private Label dateCreated;
    private Label orderAmount;
    private VBox statusContainer;

    public OrderCard(Order order) {
        super();

        this.order = order;

        orderNumber = new Label("#" + order.getNumber());
        customerName = new Label(order.getBilling().getFirstName() + " " + order.getBilling().getLastName());
        dateCreated = new Label(order.getDateCreated());
        statusContainer = new VBox(new Text(order.getStatus()));
        orderAmount = new Label(order.getTotal() + " " + order.getCurrency());

        this.getChildren().addAll(orderNumber, customerName, dateCreated, statusContainer, orderAmount);

        setStyling();
    }

    public void setStyling() {

        this.setSpacing(20);
        this.setAlignment(Pos.CENTER_LEFT);


        statusContainer.setPadding(new Insets(10));

        if(order.getStatus().equals("processing")) {
            statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(198, 225, 198, 1), new CornerRadii(5), new Insets(0))));
        } else {
            statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(200, 215, 225, 1), new CornerRadii(5), new Insets(0))));
        }
    }
}
