package GUI.Panes;

import Components.Product.Product;
import Exceptions.FetchException;
import GUI.Components.PrimaryButton;
import Website.Website;
import javafx.scene.control.Button;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MissingProductsPane extends DualWebsiteSelect {

    private Button startSearchButton;

    public MissingProductsPane(List<Website> websites) {
        super("Missing Products", websites);

        this.startSearchButton = new PrimaryButton("Start Search");

        this.getChildren().add(startSearchButton);
        this.getChildren().add(missingProductsText);

        setEventHandlers();
    }

    private void setEventHandlers() {
        startSearchButton.setOnMouseClicked(e -> {
            search();
        });
    }

    protected void search() {

        if(mainWebsite == null || comparingWebsite == null) {
            return;
        }

        Set<Product> mainProducts = null;
        Set<Product> comparingProducts = null;
        List<Product> missingProducts = new ArrayList<>();

        try {
            mainProducts = new HashSet<>(mainWebsite.getAllProducts());
            comparingProducts = new HashSet<>(comparingWebsite.getAllProducts());
        } catch (FetchException e) {
            throw new RuntimeException(e);
        }

        for(Product product : mainProducts) {
            if(!comparingProducts.contains(product)) {
                missingProducts.add(product);
            }
        }

        for(Product product : missingProducts) {
            missingProductsText.appendText(product.getName() + ", " + product.getUrl() + ", " + product.getSku() + ", " + product.getStatus() + System.lineSeparator());
        }
    }
}
