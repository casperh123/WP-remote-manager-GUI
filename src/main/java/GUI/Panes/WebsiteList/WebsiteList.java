package GUI.Panes.WebsiteList;

import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import GUI.Cards.WebsiteCard;
import GUI.Components.PrimaryButton;
import GUI.GlobalState.GlobalState;
import GUI.Panes.WebsiteList.AddWebsite.AddWebsiteView;
import Utility.FileManager;
import Website.Website;
import Website.APICredentials;
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

public class WebsiteList extends VBox {

    Map<String, Node> cachedPanes;
    private List<Website> websites;
    private List<APICredentials> websiteCredentials;
    private HBox topContent;
    private VBox websiteList;

    public WebsiteList(Map<String, Node> cachedPanes) {

        this.cachedPanes = cachedPanes;
        this.topContent = new HBox();
        this.websites = new ArrayList<>();

        renderBasicLayout();

        File apiCredentialFile = new File("src/main/resources/Websites/apicredentials.obj");

        if(apiCredentialFile.isFile()) {
            try {
                this.websiteCredentials = (List<APICredentials>) FileManager.loadFile(apiCredentialFile);
            } catch (IOException e) {
                this.websiteCredentials = new ArrayList<>();
            }
        } else {
            this.websiteCredentials = new ArrayList<>();
        }

        for(APICredentials credentials : websiteCredentials) {
            websites.add(new Website(credentials));
        }

        if(websites.size() == 0) {
            addWebsite();
        } else {
            //TODO Exception handling lol
            try {
                GlobalState.setActiveWebsite( websites.get(0));
            } catch (BadHTTPResponseException e) {
                throw new RuntimeException(e);
            } catch (FetchException e) {
                throw new RuntimeException(e);
            }
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
            for (Website website : websites) {
                generateWebsiteCard(website);
            }
        }
    }

    private void generateWebsiteCard(Website website) {

        WebsiteCard websiteCard = new WebsiteCard(website);

        websiteList.getChildren().add(websiteCard);

        websiteCard.loadSetOnMouseClicked(e -> {

            try {
                GlobalState.setActiveWebsite(websiteCard.getWebsite());
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            } catch (FetchException ex) {
                throw new RuntimeException(ex);
            }

            for(Node childNode : websiteList.getChildren()) {
                if(childNode instanceof WebsiteCard inactiveCard) {
                    inactiveCard.setInactive();
                }
            }
        });
    }
}
