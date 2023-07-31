package GUI.Panes;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import GUI.Components.PrimaryButton;
import Website.Website;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class MissingDescriptionPane extends DualWebsiteSelect {

    private Button startSearchButton;
    private TextArea ignoreString;
    private Text ignoreStringTitle;

    public MissingDescriptionPane(List<Website> websites) {
        super("Missing Descriptions", websites);

        this.missingProductsText = new TextArea();
        this.startSearchButton = new PrimaryButton("Start Search");
        this.ignoreStringTitle = new Text("Ignore This Text");
        this.ignoreString = new TextArea();

        this.getChildren().add(startSearchButton);
        this.getChildren().add(missingProductsText);
        this.getChildren().add(ignoreStringTitle);
        this.getChildren().add(ignoreString);

        setEventListeners();
    }

    private void setEventListeners() {
        startSearchButton.setOnMouseClicked(e -> {
            search();
        });
    }

    protected void search() {

        if(mainWebsite == null || comparingWebsite == null) {
            return;
        }

        String sanitisedDescription;

        Set<Product> mainProducts = null;
        Set<Product> comparingProducts = null;
        List<Product> missingDescription = new ArrayList<>();
        List<Product> missingShortDescription = new ArrayList<>();

        try {
            mainProducts = new HashSet<>(mainWebsite.getAllProducts());
            comparingProducts = new HashSet<>(comparingWebsite.getAllProducts());
        } catch (BadHTTPResponseException e) {
            throw new RuntimeException(e); // TODO EXCEPTION
        }

        for(Product product : mainProducts) {

            String ignore = escapeHtml(ignoreString.getText());
            String productShortDescription = escapeHtml(product.getShortDescription()).replaceAll(ignore, "");
            String productDescription = escapeHtml(product.getDescription()).replaceAll(ignore, "");

            for(Product comparingProduct : comparingProducts) {
                if(!product.equals(comparingProduct)) {
                    continue;
                }

                String comparingShortDescription = escapeHtml(comparingProduct.getShortDescription()).replaceAll(ignore, "");
                String comparingDescription = escapeHtml(comparingProduct.getDescription()).replaceAll(ignore, "");

                if(productShortDescription.length() != 0 && comparingShortDescription.length() == 0) {
                    missingShortDescription.add(comparingProduct);
                } else if(productDescription.length() != 0 && comparingDescription.length() == 0) {
                        missingDescription.add(comparingProduct);
                }
            }
        }

        for(Product product : missingShortDescription) {
            missingProductsText.appendText(product.getName() + ", " + product.getSku() + ", " + "MISSING SHORT DESCRIPTION " + System.lineSeparator());
        }

        for(Product product : missingDescription) {
            missingProductsText.appendText(product.getName() + ", " + product.getSku() + ", " + "MISSING DESCRIPTION" + System.lineSeparator());
        }
    }

    public String escapeHtml(String string) {
        return string.replaceAll("<[^>]*>| |\n", "");
    }
}
