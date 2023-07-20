package GUI.Cards;

import Components.Order.Order;
import javafx.beans.property.DoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
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

        setContent();
        setStyling();
    }

    public void setContent() {
        orderNumber = new Label("#" + order.getNumber());
        customerName = new Label(order.getBilling().getFirstName() + " " + order.getBilling().getLastName());
        dateCreated = new Label(order.getDateCreated());
        statusContainer = new VBox(new Text(order.getStatus()));
        orderAmount = new Label(order.getTotal() + " " + order.getCurrency());

        this.getChildren().addAll(orderNumber, customerName, dateCreated, statusContainer, orderAmount);
    }

    public void setStyling() {

        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgap(10.0);

        GridPane.setConstraints(orderNumber, 0, 0);
        GridPane.setConstraints(customerName, 1, 0);
        GridPane.setConstraints(dateCreated, 2, 0);
        GridPane.setConstraints(statusContainer, 3, 0);
        GridPane.setConstraints(orderAmount, 4, 0);

        this.getColumnConstraints().add(new ColumnConstraints(50));
        this.getColumnConstraints().add(new ColumnConstraints(150));

        statusContainer.setPadding(new Insets(10));

        if(order.getStatus().equals("processing")) {
            statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(198, 225, 198, 1), new CornerRadii(5), new Insets(0))));
            statusContainer.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.1), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        } else {
            statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(200, 215, 225, 1), new CornerRadii(5), new Insets(0))));
            statusContainer.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.1), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
        }
    }
}
