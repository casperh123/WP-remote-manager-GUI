package GUI.AddWebsite;

import Utility.FileManager;
import Website.Website;
import Website.User;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.w3c.dom.Text;

import java.net.URISyntaxException;
import java.util.List;

public class AddWebsiteView extends VBox {

    Stage parentStage;

    TextField websiteUrl;
    TextField websiteName;
    TextField userName;
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
        userName = new TextField();
        userKey = new TextField();
        userSecret = new TextField();
        submitButton = new Button("Add Website");
        errorLabel = new Label();

        this.getChildren().add(new Label("Website Name"));
        this.getChildren().add(websiteName);
        this.getChildren().add(new Label("Website URL"));
        this.getChildren().add(websiteUrl);
        this.getChildren().add(new Label("Username"));
        this.getChildren().add(userName);
        this.getChildren().add(new Label("API Key"));
        this.getChildren().add(userKey);
        this.getChildren().add(new Label("API Secret"));
        this.getChildren().add(userSecret);
        this.getChildren().add(submitButton);
        this.getChildren().add(errorLabel);

        submitButton.setOnMouseClicked(e -> {
            try {
                addWebsite();
                FileManager.save("src/main/resources/Websites/website.obj", websites);
                errorLabel.setText("Success");
                parentStage.close();
            } catch (URISyntaxException ex) {
                errorLabel.setText(ex.getMessage());
            }
        });
    }

    public void addWebsite() throws URISyntaxException {
        User newUser = new User(userName.getText(), userKey.getText(), userSecret.getText());

        websites.add(new Website(websiteName.getText(), websiteUrl.getText(), newUser));
    }
}
