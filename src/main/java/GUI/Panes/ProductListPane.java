package GUI.Panes;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import GUI.Cards.ProductCard;
import GUI.Components.PrimaryButton;
import Lists.PaginatedQueryList;
import Website.Website;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class ProductListPane extends VBox {


    private Button previousPage;
    private Button nextPage;
    private HBox buttonContainer;
    private VBox productContiner;
    private PaginatedQueryList<Product> products;

    public ProductListPane(Website website) {

        previousPage = new PrimaryButton("Previous Page");
        nextPage = new PrimaryButton("Next Page");

        buttonContainer = new HBox(previousPage, nextPage);
        productContiner = new VBox();

        this.getChildren().add(buttonContainer);
        this.getChildren().add(productContiner);

        long start = System.nanoTime();

        try{
            this.products = website.getProducts();
        } catch (FetchException e) {
            System.out.println(e.getMessage());
        }

        System.out.println((System.nanoTime() - start)/1000000);

        renderProducts();
        setOnClick();
    }

    private void setOnClick() {
        previousPage.setOnMouseClicked(e -> {
            long start = System.nanoTime();
            try {
                products.getPreviousPage();
                renderProducts();
            } catch (FirstPageException ex) {//TODO Exceptions
                throw new RuntimeException(ex);
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println((System.nanoTime() - start)/1000000);
        });

        nextPage.setOnMouseClicked(e -> {
            long start = System.nanoTime();
            try {
                products.getNextPage();
                renderProducts();
            } catch (LastPageException ex) { //TODO Exception
                throw new RuntimeException(ex);
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println((System.nanoTime() - start)/1000000);
        });
    }

    private void renderProducts() {

        productContiner.getChildren().clear();

        for(Product product : products) {
            productContiner.getChildren().add(new ProductCard(product));
        }
    }
}
