package GUI;

import GUI.Components.PrimaryButton;
import GUI.Panes.MissingDescriptionPane;
import GUI.Panes.MissingProductsPane;
import GUI.Panes.WebsiteList.WebsiteList;
import GUI.Utility.StateButton;
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
import java.util.Stack;

public class MainViewController implements Initializable {

    Stack<Node> backPaneStack;
    Stack<Node> forwardPaneStack;

    public MainViewController() throws URISyntaxException {
        /*
        User trekantensCasper = new User("Casper", "ck_01f48076048289976cd89f0a3324d5c418c068be", "cs_cc7f2290c7ca39b2fd5abfe21fd34e6cdccf0dd0");
        Website trekantens = new Website("Trekantens-Trailercenter", "https://www.trekantens-trailercenter.dk", trekantensCasper);
        */

        this.backPaneStack = new Stack<>();
        this.forwardPaneStack = new Stack<>();


        System.out.println("Mannes");

    }

    @FXML
    private ScrollPane mainContent;
    @FXML
    private HBox topBar;
    @FXML
    private BorderPane mainWrapper;

    private StateButton backButton;
    private StateButton forwardButton;

    private Button missingProductsButton;
    private Button missingDescriptionButton;
    private Map<String, Node> cachedPanes;
    private WebsiteList websiteList;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backButton = new StateButton("Back", backPaneStack, forwardPaneStack, mainContent);
        forwardButton = new StateButton("Forward", forwardPaneStack, backPaneStack, mainContent);
        backButton.activateCounterStateEvent(forwardButton);
        forwardButton.activateCounterStateEvent(backButton);
        cachedPanes = new HashMap<>();
        websiteList = new WebsiteList(mainContent, backButton, cachedPanes);

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
            backButton.push(mainContent.getContent());
        });

        missingDescriptionButton.setOnMouseClicked(e -> {
            mainContent.setContent(new MissingDescriptionPane(websiteList.getWebsites()));
            backButton.push(mainContent.getContent());
        });
    }
}



