package GUI.PaneHistory;

import GUI.PaneHistory.StateButton.StateButton;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;

import java.util.Stack;

public class PaneHistory {

    private Stack<Node> backStack;
    private Stack<Node> forwardStack;
    private StateButton backButton;
    private StateButton forwardButton;
    private ScrollPane content;
    private static PaneHistory paneHistory;

    private PaneHistory() {
        this.backStack = new Stack<>();
        this.forwardStack = new Stack<>();
        this.backButton = new StateButton("Back");
        this.forwardButton = new StateButton("Forward");

        setEventHandlers();
    }


    public void addPane(Node pane) {

        if(forwardStack.size() > 1) {
            forwardButton.setInactive();
            forwardStack.clear();
        }

        backStack.push(pane);
        backButton.setActive();
    }

    private void setEventHandlers() {
        backButton.setOnMouseClicked(e -> {
            getPreviousPane();
            if(backStack.size() == 1) {
                backButton.setInactive();
            }
        });

        forwardButton.setOnMouseClicked(e -> {
            getNextPane();
            if(forwardStack.size() == 1) {
                forwardButton.setInactive();
            }
        });
    }

    private void getPreviousPane() {
        if(backStack.size() == 1) {
            return;
        }

        forwardStack.push(content.getContent());
        forwardButton.setActive();

        content.setContent(backStack.pop());

    }

    private void getNextPane() {
        if(forwardStack.size() == 1) {
            return;
        }

        backStack.push(content.getContent());
        backButton.setActive();

        content.setContent(forwardStack.pop());
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
