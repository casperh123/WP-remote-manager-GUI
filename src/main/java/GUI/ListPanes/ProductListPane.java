package GUI.ListPanes;

import Components.Product.Product;
import GUI.Cards.ProductCard;
import Website.Website;
import Lists.QueryList;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class ProductListPane extends VBox {

    private QueryList<Product> products;

    public ProductListPane(Website website) {
        this.products = website.getProducts();
        renderProducts();
    }

    private void renderProducts() {

        this.getChildren().clear();

        for(Product product : products) {
            this.getChildren().add(new ProductCard(product));
        }
    }
}
