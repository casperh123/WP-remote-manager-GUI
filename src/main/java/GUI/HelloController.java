package GUI;

import Components.WPImage;
import Components.Product.AbstractProduct;
import javafx.css.Size;
import javafx.css.SizeUnits;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import Website.Website;
import Website.User;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Box;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;

import java.io.File;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;

public class HelloController implements Initializable {

    Website skadedyrsexperten;

    public HelloController() throws URISyntaxException {

        User casper = new User("Casper", "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558", "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357");
        skadedyrsexperten = new Website("Skadedyrsexperten", "https://staging-skadedyrsexpertendk-test.kinsta.cloud", casper);

    }

    @FXML
    private Pane mainBox;

    @FXML
    private Pane mainWrapper;
    @FXML
    private ScrollPane websitePanelWrapper;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            renderProducts();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    private void renderProducts() throws ExecutionException, InterruptedException {

        List<CompletableFuture<Pane>> productCards = new ArrayList<>();

        for(AbstractProduct product : skadedyrsexperten.getProducts()) {
            productCards.add(
                    CompletableFuture.supplyAsync(() -> {
                        System.out.println("Send");
                        return productCard(product);
                    })
            );
        }

        for(CompletableFuture<Pane> productCard : productCards) {
            System.out.println("Return");
            mainBox.getChildren().add(productCard.get());
        }
    }

    private Pane productCard(AbstractProduct product) {

        Pane productCard = new HBox();
        ImageView productImage = new ImageView(new Image(product.getImages().get(0).getImageUrl()));
        Label productTitle = new Label(product.getName());

        productCard.setPadding(new Insets(10));
        productCard.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(10), new Insets(10))));

        productTitle.setFont(new Font(18));
        productTitle.setPadding(new Insets(0, 0, 0, 10));

        productImage.setFitHeight(100);
        productImage.setFitWidth(100);

        productCard.getChildren().add(productImage);
        productCard.getChildren().add(productTitle);

        return productCard;
    }
}