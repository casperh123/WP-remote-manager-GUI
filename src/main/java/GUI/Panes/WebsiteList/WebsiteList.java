package GUI.Panes.WebsiteList;

import GUI.Cards.WebsiteCard;
import GUI.Components.PrimaryButton;
import GUI.PaneHistory.PaneHistory;
import GUI.Panes.ProductListPane;
import GUI.Panes.WebsiteList.AddWebsite.AddWebsiteView;
import Utility.FileManager;
import Website.Website;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public class WebsiteList extends VBox {

    Map<String, Node> cachedPanes;
    private List<Website> websites;
    private Website activeWebsite;

    private ScrollPane mainContent;
    private HBox topContent;
    private VBox websiteList;
    private PaneHistory paneHistory;

    public WebsiteList(ScrollPane mainContent, Map<String, Node> cachedPanes) {

        this.cachedPanes = cachedPanes;
        this.mainContent = mainContent;
        this.topContent = new HBox();
        this.paneHistory = PaneHistory.getInstance();

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

    public List<Website> getWebsites() {
        return websites;
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

        Button addNewButton = new PrimaryButton("Add");

        addNewButton.setOnMouseClicked(e -> {
            addWebsite();
        });

        topContent.getChildren().add(new Label("Websites"));
        topContent.getChildren().add(addNewButton);
        topContent.setSpacing(10.0);
        topContent.setPadding(new Insets(5));

        websiteList = new VBox();
        websiteList.setSpacing(10.0);
        websiteList.setPadding(new Insets(5));

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
    }

    private void generateWebsiteCard(Website website) {

        WebsiteCard websiteCard = new WebsiteCard(website);

        websiteList.getChildren().add(websiteCard);

        websiteCard.loadSetOnMouseClicked(e -> {

            Website containedWebsite = websiteCard.getWebsite();

            for(Node childNode : websiteList.getChildren()) {
                if(childNode instanceof WebsiteCard inactiveCard) {
                    inactiveCard.setInactive();
                }
            }

            activeWebsite = websiteCard.getWebsite();

            ProductListPane productList = new ProductListPane(activeWebsite);

            //cachedPanes.put(activeWebsite.getName(), mainContent.getContent());

            /*if(cachedPanes.containsKey(containedWebsite.getName())) {
                mainContent.setContent(cachedPanes.get(containedWebsite.getName()));
                activeWebsite = containedWebsite;
            } else {*/

            /*}*/
            mainContent.setContent(productList);
            paneHistory.addPane(mainContent.getContent());
        });
    }
}
