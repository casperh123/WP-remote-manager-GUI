package GUI;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import GUI.Products.ProductListPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import Website.Website;
import Website.User;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    Stack<Pane> paneStack;
    Map<String, Website> websites;
    Map<String, Node> currentPanes;
    Website activeWebsite;

    public HelloController() throws URISyntaxException {

        User casper = new User("Casper", "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558", "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357");
        Website skadedyrsexperten = new Website("Skadedyrsexperten", "https://staging-skadedyrsexpertendk-test.kinsta.cloud", casper);

        User trekantensCasper = new User("Casper", "ck_01f48076048289976cd89f0a3324d5c418c068be", "cs_cc7f2290c7ca39b2fd5abfe21fd34e6cdccf0dd0");
        Website trekantens = new Website("Trekantens-Trailercenter", "https://www.trekantens-trailercenter.dk", trekantensCasper);

        paneStack = new Stack<>();

        this.websites = new HashMap<>();
        this.currentPanes = new HashMap<>();

        websites.put(skadedyrsexperten.getName(), skadedyrsexperten);
        websites.put(trekantens.getName(), trekantens);

        this.activeWebsite = websites.get(skadedyrsexperten.getName());

    }

    @FXML
    private ScrollPane listWrapper;
    @FXML
    private HBox websitePanel;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listWrapper.setContent(new ProductListPane(activeWebsite, paneStack));
        loadWebsites();

    }

    private void loadWebsites() {
        for(String websiteName : websites.keySet()) {
            websitePanel.getChildren().add(websiteCard(websites.get(websiteName)));
        }
        websitePanel.getChildren().add(new Text());
    }

    private Pane websiteCard(Website website) {
        HBox websiteCard = new HBox();
        Label websiteNameLabel = new Label(website.getName());

        websiteCard.setPadding(new Insets(20));
        websiteCard.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(5))));

        websiteCard.getChildren().add(websiteNameLabel);

        websiteCard.setOnMouseClicked(e -> {

            currentPanes.put(activeWebsite.getName(), listWrapper.getContent());

            String websiteName = website.getName();

            if(websites.containsKey(websiteName)) {
                if(currentPanes.containsKey(websiteName)) {
                    listWrapper.setContent(currentPanes.get(websiteName));
                    activeWebsite = websites.get(websiteName);
                } else {

                    activeWebsite = websites.get(websiteName);

                    ProductListPane newPane = new ProductListPane(activeWebsite, paneStack);

                    currentPanes.put(websiteName, newPane);
                    listWrapper.setContent(newPane);
                }
            }
        });

        return websiteCard;
    }
}