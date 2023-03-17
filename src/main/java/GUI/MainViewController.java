package GUI;

import GUI.AddWebsite.AddWebsiteView;
import GUI.Cards.WebsiteCard;
import GUI.ListPanes.ProductListPane;
import GUI.Utility.StateButton;
import Utility.FileManager;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import Website.Website;
import Website.User;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class MainViewController implements Initializable {

    Stack<Node> backPaneStack;
    Stack<Node> forwardPaneStack;

    Map<String, Website> websiteMap;
    Map<String, Node> currentPanes;

    Website activeWebsite;

    List<Website> websites;


    public MainViewController() throws URISyntaxException {

        /*User casper = new User("Casper", "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558", "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357");
        Website skadedyrsexperten = new Website("Skadedyrsexperten", "https://staging-skadedyrsexpertendk-test.kinsta.cloud", casper);

        User trekantensCasper = new User("Casper", "ck_01f48076048289976cd89f0a3324d5c418c068be", "cs_cc7f2290c7ca39b2fd5abfe21fd34e6cdccf0dd0");
        Website trekantens = new Website("Trekantens-Trailercenter", "https://www.trekantens-trailercenter.dk", trekantensCasper);
*/
        this.backPaneStack = new Stack<>();
        this.forwardPaneStack = new Stack<>();
        this.websiteMap = new HashMap<>();
        this.currentPanes = new HashMap<>();

        File websiteFile = new File("src/main/resources/Websites/website.obj");

        if(websiteFile.isFile()) {
            try {
                this.websites = (List<Website>) FileManager.loadFile(websiteFile);
            } catch (IOException e) {
                this.websites = new ArrayList<>();
            }
        } else {
            this.websites = new ArrayList<>();
        }
/*
        websiteMap.put(skadedyrsexperten.getName(), skadedyrsexperten);
        websiteMap.put(trekantens.getName(), trekantens);
*/

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

        if(websites.size() == 0) {
            addWebsite(websites);
        } else {
            this.activeWebsite = websites.get(0);
            listWrapper.setContent(new ProductListPane(activeWebsite));
            loadWebsites();
        }

        addStateButtons();

    }

    public void addWebsite(List<Website> websites) {
        Stage loginStage = new Stage();
        Scene loginScene = new Scene(new AddWebsiteView(websites), 500, 300);
        loginStage.setScene(loginScene);
        loginStage.setAlwaysOnTop(true);
        loginStage.setOnCloseRequest(e -> loadWebsites());
        loginStage.show();
    }

    private void addStateButtons() {
        backButton = new StateButton("Back", backPaneStack, forwardPaneStack, listWrapper);
        forwardButton = new StateButton("Forward", forwardPaneStack, backPaneStack, listWrapper);
        backButton.activateCounterStateEvent(forwardButton);
        forwardButton.activateCounterStateEvent(backButton);

        topBar.getChildren().add(backButton);
        topBar.getChildren().add(forwardButton);
    }

    private void loadWebsites() {
        for(Website website : websites) {

            WebsiteCard addedWebsite = new WebsiteCard(websiteMap.get(website.getName()));

            websitePanel.getChildren().add(addedWebsite);

            addedWebsite.setOnMouseClicked(e -> {

                currentPanes.put(activeWebsite.getName(), listWrapper.getContent());
                backPaneStack.push(listWrapper.getContent());
                backButton.setBackGroundActive();

                if(websiteMap.containsKey(website.getName())) {
                    if(currentPanes.containsKey(website.getName())) {
                        listWrapper.setContent(currentPanes.get(website.getName()));
                        activeWebsite = websiteMap.get(website.getName());
                    } else {

                        activeWebsite = websiteMap.get(website.getName());

                        ProductListPane newPane = new ProductListPane(activeWebsite);

                        currentPanes.put(website.getName(), newPane);
                        listWrapper.setContent(newPane);
                    }
                }
            });
        }
    }

}