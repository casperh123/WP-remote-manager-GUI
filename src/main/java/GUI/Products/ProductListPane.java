package GUI.Products;

import Components.Product.Product;
import Website.Website;
import Lists.QueryList;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class ProductListPane extends VBox {

    private QueryList<Product> products;
    private Stack<Pane> paneStack;

    public ProductListPane(Website website, Stack<Pane> paneStack) {
        this.products = website.getProducts();
        try {
            renderProducts();
            //TODO exception handling
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderProducts() throws ExecutionException, InterruptedException {

        this.getChildren().clear();

        List<CompletableFuture<Pane>> productCards = new ArrayList<>();

        for(Product product : products) {
            productCards.add(
                    CompletableFuture.supplyAsync(() -> {
                        System.out.println("Send");
                        return productCard(product);
                    })
            );
        }

        for(CompletableFuture<Pane> productCard : productCards) {
            System.out.println("Return");
            this.getChildren().add(productCard.get());
        }
    }

    private Pane productCard(Product product) {

        Pane productCard = new HBox();
        ImageView productImage = generateImageView(product.getImages().get(0).getImageUrl());
        Label productTitle = new Label(product.getName());

        productCard.setPadding(new Insets(20));
        productCard.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(10), new Insets(10))));

        productTitle.setFont(new Font(18));
        productTitle.setPadding(new Insets(0, 0, 0, 10));

        productImage.setFitHeight(100);
        productImage.setFitWidth(100);

        productCard.getChildren().add(productImage);
        productCard.getChildren().add(productTitle);

        return productCard;
    }

    private ImageView generateImageView(String imageUrl) {
        ImageView imageView = new ImageView(imageUrl);

        if(imageView.getImage() == null || imageView.getImage().isError()) {
            try {
                imageView = new ImageView(new Image(new FileInputStream("src/main/resources/Images/placeholder.jpg")));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return imageView;
    }


}
