package GUI.ListPanes;

import Components.Product.Product;
import Exceptions.FetchException;
import GUI.Cards.ProductCard;
import Website.Website;
import Lists.QueryList;
import javafx.scene.layout.*;

public class ProductListPane extends VBox {

    private QueryList<Product> products;

    public ProductListPane(Website website) {
        try{
            this.products = website.getProducts();
        } catch (FetchException e) {
            System.out.println(e.getMessage());
        }
        renderProducts();
    }

    private void renderProducts() {

        this.getChildren().clear();

        for(Product product : products) {
            this.getChildren().add(new ProductCard(product));
        }
    }
}
