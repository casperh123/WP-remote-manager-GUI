package GUI.ListPanes.WebsiteList;

import GUI.Cards.WebsiteCard;
import GUI.ListPanes.ProductListPane;
import GUI.ListPanes.WebsiteList.AddWebsite.AddWebsiteView;
import GUI.Utility.StateButton;
import Utility.FileManager;
import Website.Website;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class WebsiteListPane extends VBox {

    Map<String, Node> cachedPanes;
    private List<Website> websites;
    private Website activeWebsite;

    private ScrollPane mainContent;
    private StateButton backButton;
    private Pane topContent;
    private VBox websiteList;

    public WebsiteListPane(ScrollPane mainContent, StateButton backButton) {

        this.mainContent = mainContent;
        this.cachedPanes = new HashMap<>();
        this.backButton = backButton;
        this.topContent = new HBox();

        renderBasicLayout();

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

        if(websites.size() == 0) {
            addWebsite();
        } else {
            this.activeWebsite = websites.get(0);
            loadWebsites();
        }
    }

    public Website getActiveWebsite() {
        return activeWebsite;
    }

    private void addWebsite() {
        Stage loginStage = new Stage();
        Scene loginScene = new Scene(new AddWebsiteView(websites, loginStage), 500, 300);
        loginStage.setScene(loginScene);
        loginStage.setAlwaysOnTop(true);
        loginStage.setOnHidden(e -> loadWebsites());
        loginStage.setTitle("Add Website");
        loginStage.initModality(Modality.APPLICATION_MODAL);
        loginStage.show();
    }

    private void renderBasicLayout() {

        Button addNewButton = new Button("Add");

        addNewButton.setOnMouseClicked(e -> {
            addWebsite();
        });

        topContent.getChildren().add(new Label("Websites"));
        topContent.getChildren().add(addNewButton);

        websiteList = new VBox();

        this.getChildren().add(topContent);
        this.getChildren().add(new ScrollPane(websiteList));
    }

    private void loadWebsites() {

        websiteList.getChildren().clear();

        if (websites.size() != 0) {

            activeWebsite = websites.get(0);

            for (Website website : websites) {
                generateWebsiteCard(website);
            }
        }

        mainContent.setContent(new ProductListPane(activeWebsite));

    }

    private void generateWebsiteCard(Website website) {

        WebsiteCard addedCard = new WebsiteCard(website);

        websiteList.getChildren().add(addedCard);

        addedCard.setOnMouseClicked(e -> {

            Website containedWebsite = addedCard.getWebsite();

            cachedPanes.put(activeWebsite.getName(), mainContent.getContent());
            backButton.push(mainContent.getContent());
            backButton.setBackGroundActive();

            if(cachedPanes.containsKey(containedWebsite.getName())) {
                mainContent.setContent(cachedPanes.get(containedWebsite.getName()));
                activeWebsite = containedWebsite;
            } else {

                activeWebsite = containedWebsite;

                ProductListPane newPane = new ProductListPane(activeWebsite);

                cachedPanes.put(activeWebsite.getName(), newPane);
                mainContent.setContent(newPane);
            }
        });
    }
}
