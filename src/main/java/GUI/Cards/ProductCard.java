package GUI.Cards;

import Components.Product.Product ;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ProductCard extends ListCard {

    ImageView productImage;
    Label productTitle;
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

        productTitle = new Label(product.getName());

        setContent();
        setStyling();
    }

    protected void setContent() {
        this.getChildren().add(productImage);
        this.getChildren().add(productTitle);
    }

    protected void setStyling() {
        productTitle.setFont(new Font(18));
        productTitle.setPadding(new Insets(0, 0, 0, 10));

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
