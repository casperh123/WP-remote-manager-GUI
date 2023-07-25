package GUI.Cards;

import Components.Product.Product;
import Components.Product.ProductComponents.ProductCategory;
import GUI.ComponentPages.ProductPage;
import GUI.Components.CopyableText;
import GUI.GlobalState.GlobalState;
import Utility.GetWebImage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProductCard extends ListCard {

    ImageView productImage;
    CopyableText productTitle;
    CopyableText productSku;
    Label stockStatus;
    CopyableText price;
    CopyableText categories;
    Label status;
    CopyableText dateCreated;
    VBox statusTimeWrapper;
    String currencyCode;

    Product product;

    public ProductCard(Product product, String currencyCode) {

        this.product = product;
        this.currencyCode = currencyCode;

        if(product.getImages().size() > 0) {
            productImage = GetWebImage.getImage(product.getImages().get(0).getImageUrl());
        }

        setContent();
        setStyle();
        setEventListeners();
    }

    @Override
    protected void setContent() {
        productTitle = new CopyableText(product.getName());
        productSku = new CopyableText(product.getSku());
        price = new CopyableText(product.getRegularPrice() + " " + currencyCode);
        status = new Label(product.getFormattedStatus());
        dateCreated = new CopyableText(product.getFormattedDateCreated());
        statusTimeWrapper = new VBox();

        statusTimeWrapper.getChildren().addAll(status, dateCreated);

        if(product.getStockStatus().equals("outofstock")) {
            stockStatus = new Label("Out of stock ");
            stockStatus.setTextFill(Color.rgb(177, 68, 110));
        } else {
            stockStatus = new Label("In stock: " + product.getStockQuantity());
            stockStatus.setTextFill(Color.rgb(122, 211, 124));
        }

        StringBuilder categoryString = new StringBuilder();

        for(ProductCategory category : product.getCategories()) {

            categoryString.append(category).append(", ");
        }

        categories = new CopyableText(categoryString.toString());

        this.getChildren().addAll(productImage, productTitle, productSku, stockStatus, price, categories, statusTimeWrapper);
    }

    @Override
    protected void setEventListeners() {
        this.setOnMouseClicked(e -> {
            GlobalState.setMainContent(new ProductPage(product));
        });
    }

    @Override
    protected void setStyle() {
        this.setAlignment(Pos.CENTER_LEFT);

        this.setHgap(10);
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(250));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(80));
        this.getColumnConstraints().add(new ColumnConstraints(200));

        GridPane.setConstraints(productImage, 0, 0);
        GridPane.setConstraints(productTitle, 1, 0);
        GridPane.setConstraints(productSku, 2, 0);
        GridPane.setConstraints(stockStatus, 3, 0);
        GridPane.setConstraints(price, 4, 0);
        GridPane.setConstraints(categories, 5, 0);
        GridPane.setConstraints(statusTimeWrapper, 6, 0);

        statusTimeWrapper.setAlignment(Pos.CENTER_LEFT);

        productTitle.setFont(new Font(18));
        productTitle.setWrapText(true);
        productTitle.setTextMaxWidth(200);

        categories.setTextMaxWidth(150);
        categories.setWrapText(true);

        productImage.setFitHeight(100);
        productImage.setFitWidth(100);
    }

    public Product getProduct() {
        return product;
    }
}
