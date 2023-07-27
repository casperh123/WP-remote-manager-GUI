package GUI.PaneHistory;

import GUI.GlobalState.GlobalState;
import GUI.Components.StateButton;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

import java.util.Stack;

public class PaneHistory {

    private Stack<Pane> backStack;
    private Stack<Pane> forwardStack;
    private Pane nodepane;
    private StateButton backButton;
    private StateButton forwardButton;
    private static PaneHistory paneHistory;

    private PaneHistory() {

        this.backStack = new Stack<>();
        this.forwardStack = new Stack<>();
        this.backButton = new StateButton("Back");
        this.forwardButton = new StateButton("Forward");
        this.nodepane = null;

        setEventHandlers();
    }


    public void addPane(Pane pane) {
        if(nodepane == null) {
            nodepane = pane;
        } else {
            backStack.push(nodepane);
            backButton.setActive();
            nodepane = pane;
        }

        if(forwardStack.size() > 0) {
            forwardButton.setInactive();
            forwardStack.clear();
        }
    }

    private void setEventHandlers() {
        backButton.setOnMouseClicked(e -> {
            if(backStack.size() == 0) {
                return;
            }

            getPreviousPane();

            if(backStack.size() == 0) {
                backButton.setInactive();
            }
        });

        forwardButton.setOnMouseClicked(e -> {
            if(forwardStack.size() == 0) {
                return;
            }

            getNextPane();

            if(forwardStack.size() == 0) {
                forwardButton.setInactive();
            }
        });
    }

    private void getPreviousPane() {

        forwardStack.push(nodepane);
        forwardButton.setActive();

        nodepane = backStack.pop();

        GlobalState.setMainContentNoCache(nodepane);

    }

    private void getNextPane() {

        backStack.push(nodepane);
        backButton.setActive();

        nodepane = forwardStack.pop();

        GlobalState.setMainContentNoCache(nodepane);
    }


    public Button getBackButton() {
        return backButton;
    }

    public Button getForwardButton() {
        return forwardButton;
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
