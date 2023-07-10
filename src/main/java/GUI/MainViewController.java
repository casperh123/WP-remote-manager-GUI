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
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    public MainViewController() {}


    @FXML
    private ScrollPane mainContent;
    @FXML
    private HBox topBar;
    @FXML
    private BorderPane mainWrapper;
    public MenuBar toolBar;

    private Button backButton;
    private Button forwardButton;
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

        setEventHandlers();
        setStyle();
        initializeToolBar();

        topBar.getChildren().add(backButton);
        topBar.getChildren().add(forwardButton);

        mainWrapper.setLeft(websiteList);

    }

    public void initializeToolBar() {

        Menu toolDropdown = new Menu("Tools");

        MenuItem missingProductsTool = new MenuItem("Missing Products");
        MenuItem missingDescriptionsTool = new MenuItem("Missing Descriptions");

        toolDropdown.getItems().add(missingProductsTool);
        toolDropdown.getItems().add(missingDescriptionsTool);

        toolBar.getMenus().add(toolDropdown);

        missingProductsTool.setOnAction(e -> {
            mainContent.setContent(new MissingProductsPane(websiteList.getWebsites()));
            paneHistory.addPane(mainContent.getContent());
        });

        missingDescriptionsTool.setOnAction(e -> {
            mainContent.setContent(new MissingDescriptionPane(websiteList.getWebsites()));
            paneHistory.addPane(mainContent.getContent());
        });

    }

    public void setStyle() {
        topBar.setSpacing(10.0);
        topBar.setPadding(new Insets(5));
    }

    public void setEventHandlers() {
    }
}



