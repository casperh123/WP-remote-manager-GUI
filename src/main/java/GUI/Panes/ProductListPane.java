package GUI.Panes;

import Components.Interfaces.ID;
import Components.Product.Product;
import GUI.Cards.ProductCard;
import Lists.PaginatedQueryList;
import javafx.geometry.Insets;

public class ProductListPane extends ListPane {

    public ProductListPane(PaginatedQueryList<Product> productList) {
        super(productList);

        this.setSpacing(10.0);
        this.setPadding(new Insets(10));
    }

    protected void renderList() {

        if(componentList == null) {
            return;
        }

        listContainer.getChildren().clear();

        for(ID component : componentList) {

            Product product = (Product) component;

            listContainer.getChildren().add(new ProductCard(product));
        }
    }
}
