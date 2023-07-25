package GUI;

import GUI.GlobalState.GlobalState;
import GUI.PaneHistory.PaneHistory;
import GUI.Panes.MissingDescriptionPane;
import GUI.Panes.MissingProductsPane;
import GUI.Panes.WebsiteList.WebsiteList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


        mainWrapper.setCenter(GlobalState.getMainScrollPane());

        cachedPanes = new HashMap<>();
        websiteList = new WebsiteList(cachedPanes);
        backButton = PaneHistory.getInstance().getBackButton();
        forwardButton = PaneHistory.getInstance().getForwardButton();

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
            GlobalState.setMainContent(new MissingProductsPane(websiteList.getWebsites()));
        });

        missingDescriptionsTool.setOnAction(e -> {
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



