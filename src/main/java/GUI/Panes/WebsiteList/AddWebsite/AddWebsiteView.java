package GUI.Panes.WebsiteList.AddWebsite;

import Utility.FileManager;
import Website.Website;
import Website.APICredentials;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

public class AddWebsiteView extends VBox {

    Stage parentStage;

    TextField websiteUrl;
    TextField websiteName;
    TextField userKey;
    TextField userSecret;
    Label errorLabel;

    Button submitButton;

    List<Website> websites;

    private AddWebsiteController addWebsiteController;

    public AddWebsiteView(List<Website> websites, Stage parentStage) {

        this.addWebsiteController = new AddWebsiteController(websites);
        this.websites = websites;
        this.parentStage = parentStage;

        websiteUrl = new TextField();
        websiteName = new TextField();
        userKey = new TextField();
        userSecret = new TextField();
        submitButton = new Button("Add Website");
        errorLabel = new Label();

        this.getChildren().add(new Label("Website Name"));
        this.getChildren().add(websiteName);
        this.getChildren().add(new Label("Website URL"));
        this.getChildren().add(websiteUrl);
        this.getChildren().add(new Label("API Key"));
        this.getChildren().add(userKey);
        this.getChildren().add(new Label("API Secret"));
        this.getChildren().add(userSecret);
        this.getChildren().add(submitButton);
        this.getChildren().add(errorLabel);

        submitButton.setOnMouseClicked(e -> {
            try {
                addWebsite();
                saveWebsites();
                errorLabel.setText("Success");
                parentStage.close();
            } catch (URISyntaxException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });
    }

    public void saveWebsites() {
        List<APICredentials> apiCredentials = new ArrayList<>();

        for(Website website : websites) {
            apiCredentials.add(website.getApiCredentials());
        }

        FileManager.save("src/main/resources/Websites/apicredentials.obj", apiCredentials);
    }

    public void addWebsite() throws URISyntaxException {
        APICredentials newApiCredentials = new APICredentials(userKey.getText(), userSecret.getText(), websiteUrl.getText(), websiteName.getText());

        websites.add(new Website(newApiCredentials));
    }
}
