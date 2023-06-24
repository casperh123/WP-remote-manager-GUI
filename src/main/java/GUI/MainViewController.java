package GUI;

import GUI.Components.PrimaryButton;
import GUI.PaneHistory.PaneHistory;
import GUI.Panes.MissingDescriptionPane;
import GUI.Panes.MissingProductsPane;
import GUI.Panes.WebsiteList.WebsiteList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    public MainViewController() throws URISyntaxException {
        /*
        User trekantensCasper = new User("Casper", "ck_01f48076048289976cd89f0a3324d5c418c068be", "cs_cc7f2290c7ca39b2fd5abfe21fd34e6cdccf0dd0");
        Website trekantens = new Website("Trekantens-Trailercenter", "https://www.trekantens-trailercenter.dk", trekantensCasper);
        */


        System.out.println("Mannes");

    }

    @FXML
    private ScrollPane mainContent;
    @FXML
    private HBox topBar;
    @FXML
    private BorderPane mainWrapper;

    private Button backButton;
    private Button forwardButton;

    private PrimaryButton missingProductsButton;
    private PrimaryButton missingDescriptionButton;
    private Map<String, Node> cachedPanes;
    private WebsiteList websiteList;
    private PaneHistory paneHistory;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        cachedPanes = new HashMap<>();
        websiteList = new WebsiteList(mainContent, cachedPanes);
        paneHistory = PaneHistory.getInstance();
        backButton = paneHistory.getBackButton();
        forwardButton = paneHistory.getForwardButton();
        paneHistory.setContent(mainContent);

        topBar.setSpacing(10.0);
        topBar.setPadding(new Insets(5));

        missingProductsButton = new PrimaryButton("Missing Products");
        missingDescriptionButton = new PrimaryButton("Missing Descriptions");

        setEventHandlers();

        topBar.getChildren().add(backButton);
        topBar.getChildren().add(forwardButton);
        topBar.getChildren().add(missingProductsButton);
        topBar.getChildren().add(missingDescriptionButton);

        mainWrapper.setLeft(websiteList);

    }

    public void setEventHandlers() {
        missingProductsButton.setOnMouseClicked(e -> {
            mainContent.setContent(new MissingProductsPane(websiteList.getWebsites()));
            paneHistory.addPane(mainContent.getContent());
        });

        missingDescriptionButton.setOnMouseClicked(e -> {
            mainContent.setContent(new MissingDescriptionPane(websiteList.getWebsites()));
            paneHistory.addPane(mainContent.getContent());
        });
    }
}



