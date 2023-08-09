package GUI.Panes;

import Components.Interfaces.ID;
import Components.Order.Order;
import GUI.Cards.OrderCard;
import Lists.QueryList;

public class OrderListPane extends ListPane {

    public OrderListPane(QueryList<Order> orderList) {
        super(orderList);

        renderList();
    }

    @Override
    void renderList() {
        if(componentList == null) {
            return;
        }

        listContainer.getChildren().clear();

        for(ID component : componentList) {

            Order order = (Order) component;

            listContainer.getChildren().add(new OrderCard(order));
        }
    }
}
