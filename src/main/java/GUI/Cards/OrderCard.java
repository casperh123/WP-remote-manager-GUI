package GUI.Cards;

import Components.Order.Order;
import GUI.Components.CopyableText;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class OrderCard extends ListCard {

    private Order order;
    private CopyableText orderNumber;
    private CopyableText customerName;
    private Label dateCreated;
    private Label orderAmount;
    private VBox statusContainer;

    public OrderCard(Order order) {
        super();

        this.order = order;

        setContent();
        setStyle();
        setEventListeners();
    }

    @Override
    public void setContent() {
        orderNumber = new CopyableText("#" + order.getNumber());
        customerName = new CopyableText(order.getBilling().getFirstName() + " " + order.getBilling().getLastName());
        dateCreated = new Label(order.getFormattedDateCreated());
        statusContainer = new VBox(new Text(order.getStatus()));
        orderAmount = new Label(order.getTotal() + " " + order.getCurrency());

        this.getChildren().addAll(orderNumber, customerName, dateCreated, statusContainer, orderAmount);
    }

    @Override
    protected void setEventListeners() {

    }

    @Override
    public void setStyle() {

        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgap(10.0);

        GridPane.setConstraints(orderNumber, 0, 0);
        GridPane.setConstraints(customerName, 1, 0);
        GridPane.setConstraints(dateCreated, 2, 0);
        GridPane.setConstraints(statusContainer, 3, 0);
        GridPane.setConstraints(orderAmount, 4, 0);

        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(150));

        statusContainer.setPadding(new Insets(10));


        switch (order.getStatus()) {
            case "processing" -> {
                statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(198, 225, 198, 1), new CornerRadii(5), new Insets(0))));
                statusContainer.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.1), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
            }
            case "completed" -> {
                statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(200, 215, 225, 1), new CornerRadii(5), new Insets(0))));
                statusContainer.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.1), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
            }
            case "on-hold" -> {
                statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(248, 220, 166, 1), new CornerRadii(5), new Insets(0))));
                statusContainer.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.1), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
            }
            default -> {
                statusContainer.setBackground(new Background(new BackgroundFill(Color.rgb(229, 228, 228, 1), new CornerRadii(5), new Insets(0))));
                statusContainer.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.1), BorderStrokeStyle.SOLID, new CornerRadii(5), BorderWidths.DEFAULT)));
            }
        }
    }
}
