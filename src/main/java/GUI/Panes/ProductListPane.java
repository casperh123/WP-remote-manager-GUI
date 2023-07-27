package GUI.Panes;

import Components.Currency;
import Components.Interfaces.ID;
import Components.Product.Product;
import GUI.Cards.ProductCard;
import GUI.ComponentPages.ProductPage;
import GUI.GlobalState.GlobalState;
import Lists.QueryList;
import javafx.geometry.Insets;

public class ProductListPane extends ListPane {

    private Currency currency;

    public ProductListPane(QueryList<Product> productList, Currency currency) {
        super(productList);

        this.currency = currency;

        this.setSpacing(10.0);
        this.setPadding(new Insets(10));

        renderList();
    }

    protected void renderList() {

        if(componentList == null) {
            return;
        }

        listContainer.getChildren().clear();

        for(ID component : componentList) {

            Product product = (Product) component;

            ProductCard productCard = new ProductCard(product, currency.getCode());

            productCard.setOnMouseClicked(e -> {
                GlobalState.setMainContent(new ProductPage(product));
            });

            listContainer.getChildren().add(productCard);
        }
    }
}
