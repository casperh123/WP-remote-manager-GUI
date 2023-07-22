package GUI.Cards;

import Components.Product.Product ;
import Components.Product.ProductComponents.ProductCategory;
import Utility.GetWebImage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class ProductCard extends ListCard {

    ImageView productImage;
    Label productTitle;
    Label productSku;
    Label stockStatus;
    Label price;
    Label categories;
    Label status;
    Label dateCreated;
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
        productTitle = new Label(product.getName());
        productSku = new Label(product.getSku());
        price = new Label(Integer.toString(product.getRegularPrice()) + " " + currencyCode);
        status = new Label(product.getStatus());
        dateCreated = new Label(product.getDateCreated());
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

        categories = new Label(categoryString.toString());

        this.getChildren().addAll(productImage, productTitle, productSku, stockStatus, price, categories, statusTimeWrapper);
    }

    @Override
    protected void setEventListeners() {

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
        productTitle.setMaxWidth(300);
        productTitle.setWrapText(true);
        productTitle.setPadding(new Insets(0, 0, 0, 10));

        categories.setMaxWidth(250);
        categories.setWrapText(true);

        productImage.setFitHeight(100);
        productImage.setFitWidth(100);
    }

    public Product getProduct() {
        return product;
    }
}
