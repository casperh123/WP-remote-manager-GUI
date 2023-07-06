package GUI.PaneHistory;

import GUI.PaneHistory.StateButton.StateButton;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.util.Stack;

public class PaneHistory {

    private Stack<Node> backStack;
    private Stack<Node> forwardStack;
    private Node nodeBuffer;
    private StateButton backButton;
    private StateButton forwardButton;
    private ScrollPane content;
    private static PaneHistory paneHistory;

    private PaneHistory() {

        this.backStack = new Stack<>();
        this.forwardStack = new Stack<>();
        this.backButton = new StateButton("Back");
        this.forwardButton = new StateButton("Forward");
        this.nodeBuffer = null;

        setEventHandlers();
    }


    public void addPane(Node pane) {

        if(nodeBuffer == null) {
            nodeBuffer = pane;
        } else {
            backStack.push(nodeBuffer);
            backButton.setActive();
            nodeBuffer = pane;
        }

        if(forwardStack.size() > 0) {
            forwardButton.setInactive();
            forwardStack.clear();
        }
    }

    private void setEventHandlers() {
        backButton.setOnMouseClicked(e -> {
            if(backStack.size() == 1) {
                backButton.setInactive();
            } else if(backStack.size() == 0) {
                return;
            }

            getPreviousPane();
        });

        forwardButton.setOnMouseClicked(e -> {
            if(forwardStack.size() == 1) {
                forwardButton.setInactive();
            } else if(forwardStack.size() == 0) {
                return;
            }

            getNextPane();
        });
    }

    private void getPreviousPane() {

        forwardStack.push(nodeBuffer);
        forwardButton.setActive();

        nodeBuffer = backStack.pop();

        content.setContent(nodeBuffer);

    }

    private void getNextPane() {

        backStack.push(nodeBuffer);
        backButton.setActive();

        nodeBuffer = forwardStack.pop();

        content.setContent(nodeBuffer);
    }


    public Button getBackButton() {
        return backButton;
    }

    public Button getForwardButton() {
        return forwardButton;
    }

    public void setContent(ScrollPane content) {
        this.content = content;
    }

    public static PaneHistory getInstance() {
        if(paneHistory == null) {
            paneHistory = new PaneHistory();
            return paneHistory;
        } else {
            return paneHistory;
        }
    }
}
