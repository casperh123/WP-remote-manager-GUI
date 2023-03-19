package GUI;

import GUI.ListPanes.WebsiteList.WebsiteListPane;
import GUI.Utility.StateButton;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.*;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.*;

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


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        backButton = new StateButton("Back", backPaneStack, forwardPaneStack, mainContent);
        forwardButton = new StateButton("Forward", forwardPaneStack, backPaneStack, mainContent);
        backButton.activateCounterStateEvent(forwardButton);
        forwardButton.activateCounterStateEvent(backButton);

        topBar.getChildren().add(backButton);
        topBar.getChildren().add(forwardButton);

        mainWrapper.setLeft(new WebsiteListPane(mainContent, backButton));

    }
}



