package GUI;

import Components.WPImage;
import Components.Product.AbstractProduct;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import Website.Website;
import Website.User;

import java.net.URISyntaxException;

public class HelloController {

    Website skadedyrsexperten;

    public HelloController() throws URISyntaxException {

        User casper = new User("Casper", "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558", "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357");
        Website skadedyrsexperten = new Website("Skadedyrsexperten", "https://staging-skadedyrsexpertendk-test.kinsta.cloud", casper);

        for(AbstractProduct product : skadedyrsexperten.getProducts()) {
            for(WPImage image : product.getImages()) {

                    System.out.println("Yes");
                    mainBox.getChildren().add(new ImageView(new Image(image.getImageUrl())));

            }
        }
        
    }

    @FXML
    private VBox mainBox;



}