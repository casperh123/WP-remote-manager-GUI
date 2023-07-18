package GUI.Cards;

import Components.Category.Category;
import Components.Product.Product ;
import Components.Product.ProductComponents.ProductCategory;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ProductCard extends ListCard {

    ImageView productImage;
    Label productTitle;
    Label productSku;
    Label stockStatus;
    Label price;
    Label categories;

    Product product;

    public ProductCard(Product product) {

        this.product = product;

        if(product.getImages().size() > 0) {
            productImage = generateImageView(product.getImages().get(0).getImageUrl());
        } else {
            try {
                productImage = new ImageView(new Image(new FileInputStream("src/main/resources/Images/placeholder.jpg")));
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        setContent();
        setStyling();
    }

    protected void setContent() {
        productTitle = new Label(product.getName());
        productSku = new Label(product.getSku());
        price = new Label(Integer.toString(product.getRegularPrice()));

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

        this.getChildren().addAll(productImage, productTitle, productSku, stockStatus, price, categories);
    }

    protected void setStyling() {
        this.setSpacing(20.0);
        this.setAlignment(Pos.CENTER_LEFT);

        productTitle.setFont(new Font(18));
        productTitle.setMaxWidth(300);
        productTitle.setWrapText(true);
        productTitle.setPadding(new Insets(0, 0, 0, 10));

        categories.setMaxWidth(250);
        categories.setWrapText(true);

        productImage.setFitHeight(100);
        productImage.setFitWidth(100);
    }

    private ImageView generateImageView(String imageUrl) {
        return new ImageView(new Image(imageUrl, 200, 200, true, true, true));
    }

    public Product getProduct() {
        return product;
    }
}
