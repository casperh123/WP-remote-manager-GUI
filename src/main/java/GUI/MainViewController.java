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
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;
import Website.Website;
import Website.User;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

public class MainViewController implements Initializable {

    Stack<Node> backPaneStack;
    Stack<Node> forwardPaneStack;

    Map<String, Node> currentPanes;

    Website activeWebsite;

    List<Website> websites;


    public MainViewController() throws URISyntaxException {
        /*
        User trekantensCasper = new User("Casper", "ck_01f48076048289976cd89f0a3324d5c418c068be", "cs_cc7f2290c7ca39b2fd5abfe21fd34e6cdccf0dd0");
        Website trekantens = new Website("Trekantens-Trailercenter", "https://www.trekantens-trailercenter.dk", trekantensCasper);
        */
        this.backPaneStack = new Stack<>();
        this.forwardPaneStack = new Stack<>();
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

        System.out.println("Mannes");

    }

    @FXML
    private BorderPane mainWrapper;
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
            loadWebsites();
        }

        Button addNewButton = new Button("Add");

        addNewButton.setOnMouseClicked(e -> {
            addWebsite(websites);
        });

        websitePanel.getChildren().add(addNewButton);

        addStateButtons();

    }

    public void addWebsite(List<Website> websites) {
        Stage loginStage = new Stage();
        Scene loginScene = new Scene(new AddWebsiteView(websites, loginStage), 500, 300);
        loginStage.setScene(loginScene);
        loginStage.setAlwaysOnTop(true);
        loginStage.setOnHidden(e -> loadWebsites());
        loginStage.setTitle("Add Website");
        loginStage.initModality(Modality.APPLICATION_MODAL);
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

        if (websites.size() != 0) {

            activeWebsite = websites.get(0);

            for (Website website : websites) {
                generateWebsiteCard(website);
            }
        }

        listWrapper.setContent(new ProductListPane(activeWebsite));

    }

    private void generateWebsiteCard(Website website) {

        WebsiteCard addedCard = new WebsiteCard(website);

        websitePanel.getChildren().add(addedCard);

        addedCard.setOnMouseClicked(e -> {

            Website containedWebsite = addedCard.getWebsite();

            currentPanes.put(activeWebsite.getName(), listWrapper.getContent());
            backPaneStack.push(listWrapper.getContent());
            backButton.setBackGroundActive();

            if(currentPanes.containsKey(containedWebsite.getName())) {
                listWrapper.setContent(currentPanes.get(containedWebsite.getName()));
                activeWebsite = containedWebsite;
            } else {

                activeWebsite = containedWebsite;

                ProductListPane newPane = new ProductListPane(activeWebsite);

                currentPanes.put(activeWebsite.getName(), newPane);
                listWrapper.setContent(newPane);
            }
        });
    }
}



