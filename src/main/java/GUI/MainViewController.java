package GUI;

import GUI.GlobalState.GlobalState;
import GUI.PaneHistory.PaneHistory;
import GUI.Components.StateButton;
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

import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class MainViewController implements Initializable {

    public MainViewController() {}

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

    private StateButton productsButton;
    private StateButton orderButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        mainWrapper.setCenter(GlobalState.getMainScrollPane());

        cachedPanes = new HashMap<>();
        websiteList = new WebsiteList(cachedPanes);
        paneHistory = PaneHistory.getInstance();
        backButton = paneHistory.getBackButton();
        forwardButton = paneHistory.getForwardButton();
        productsButton = new StateButton("Products");
        orderButton = new StateButton("Orders");

        setEventHandlers();
        setStyle();
        initializeToolBar();

        topBar.getChildren().add(backButton);
        topBar.getChildren().add(forwardButton);

        for(Button button : GlobalState.getGlobalButtons()) {
            topBar.getChildren().add(button);
        }

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
            paneHistory.addPane(GlobalState.getMainContent());
            GlobalState.setMainContent(new MissingProductsPane(websiteList.getWebsites()));
        });

        missingDescriptionsTool.setOnAction(e -> {
            paneHistory.addPane(GlobalState.getMainContent());
            GlobalState.setMainContent(new MissingDescriptionPane(websiteList.getWebsites()));
        });

    }

    public void setStyle() {
        topBar.setSpacing(10.0);
        topBar.setPadding(new Insets(5));
    }

    public void setEventHandlers() {
    }
}



