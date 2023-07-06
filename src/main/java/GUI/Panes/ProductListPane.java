package GUI.Panes;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import GUI.Cards.ProductCard;
import GUI.Components.PrimaryButton;
import GUI.PaneHistory.StateButton.StateButton;
import Lists.PaginatedQueryList;
import Lists.QueryList;
import Website.Website;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class ProductListPane extends VBox {


    private StateButton previousPage;
    private StateButton  nextPage;
    private Button reloadButton;
    private HBox buttonContainer;
    private VBox productContiner;
    private QueryList<Product> products;

    public ProductListPane(Website website) {

        previousPage = new StateButton("Previous Page");
        nextPage = new StateButton("Next Page");
        reloadButton = new PrimaryButton("Reload");

        previousPage.setInactive();
        nextPage.setActive();

        buttonContainer = new HBox(previousPage, nextPage, reloadButton);
        productContiner = new VBox();

        this.getChildren().add(buttonContainer);
        this.getChildren().add(productContiner);

        long start = System.nanoTime();

        try{
            this.products = website.getProducts();
        } catch (FetchException | BadHTTPResponseException e) {
            System.out.println(e.getMessage());
        }

        System.out.println((System.nanoTime() - start)/1000000);

        renderProducts();
        setOnClick();
        setStyling();
    }

    private void setStyling() {
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setSpacing(10);
    }

    private void setOnClick() {
        previousPage.setOnMouseClicked(e -> {
            long start = System.nanoTime();
            try {
                products.getPreviousPage();
                renderProducts();
                if(products.getCurrentPage() == 1) {
                    previousPage.setInactive();
                } else {
                    nextPage.setActive();
                }
            } catch (FirstPageException ex) {//TODO Exceptions
                throw new RuntimeException(ex);
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println((System.nanoTime() - start)/1000000);
        });

        nextPage.setOnMouseClicked(e -> {

            if(products.getCurrentPage() == products.getPagesAmount()) {
                return;
            }

            long start = System.nanoTime();
            try {
                products.getNextPage();
                renderProducts();
                if(products.getCurrentPage() == products.getPagesAmount()) {
                    nextPage.setInactive();
                } else {
                    previousPage.setActive();
                }
            } catch (LastPageException ex) { //TODO Exception
                throw new RuntimeException(ex);
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println((System.nanoTime() - start)/1000000);
        });

        reloadButton.setOnMouseClicked(e -> {
            try {
                products.updateList();
                renderProducts();
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex); // TODO Exception Handling
            }
        });
    }

    private void renderProducts() {

        if(products == null) {
            return;
        }

        productContiner.getChildren().clear();

        for(Product product : products) {
            productContiner.getChildren().add(new ProductCard(product));
        }
    }
}
