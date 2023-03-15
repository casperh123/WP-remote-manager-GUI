package GUI;

import GUI.Cards.WebsiteCard;
import GUI.ListPanes.ProductListPane;
import GUI.Utility.StateButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import Website.Website;
import Website.User;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class HelloController implements Initializable {

    Stack<Node> backPaneStack;
    Stack<Node> forwardPaneStack;

    Map<String, Website> websites;
    Map<String, Node> currentPanes;

    Website activeWebsite;


    public HelloController() throws URISyntaxException {

        User casper = new User("Casper", "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558", "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357");
        Website skadedyrsexperten = new Website("Skadedyrsexperten", "https://staging-skadedyrsexpertendk-test.kinsta.cloud", casper);

        User trekantensCasper = new User("Casper", "ck_01f48076048289976cd89f0a3324d5c418c068be", "cs_cc7f2290c7ca39b2fd5abfe21fd34e6cdccf0dd0");
        Website trekantens = new Website("Trekantens-Trailercenter", "https://www.trekantens-trailercenter.dk", trekantensCasper);

        this.backPaneStack = new Stack<>();
        this.forwardPaneStack = new Stack<>();
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
    @FXML
    private HBox topBar;


    StateButton backButton;
    StateButton forwardButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        listWrapper.setContent(new ProductListPane(activeWebsite));
        loadWebsites();

        backButton = new StateButton("Back", backPaneStack, forwardPaneStack, listWrapper);
        forwardButton = new StateButton("Forward", forwardPaneStack, backPaneStack, listWrapper);
        backButton.activateCounterStateEvent(forwardButton);
        forwardButton.activateCounterStateEvent(backButton);

        topBar.getChildren().add(backButton);
        topBar.getChildren().add(forwardButton);
    }

    private void loadWebsites() {
        for(String websiteName : websites.keySet()) {

            WebsiteCard addedWebsite = new WebsiteCard(websites.get(websiteName));

            websitePanel.getChildren().add(addedWebsite);

            addedWebsite.setOnMouseClicked(e -> {

                currentPanes.put(activeWebsite.getName(), listWrapper.getContent());
                backPaneStack.push(listWrapper.getContent());
                backButton.setBackGroundActive();

                if(websites.containsKey(websiteName)) {
                    if(currentPanes.containsKey(websiteName)) {
                        listWrapper.setContent(currentPanes.get(websiteName));
                        activeWebsite = websites.get(websiteName);
                    } else {

                        activeWebsite = websites.get(websiteName);

                        ProductListPane newPane = new ProductListPane(activeWebsite);

                        currentPanes.put(websiteName, newPane);
                        listWrapper.setContent(newPane);
                    }
                }
            });
        }
    }

}