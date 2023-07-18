package GUI.Panes;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import GUI.PaneHistory.StateButton.StateButton;
import Lists.QueryList;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public abstract class ListPane extends VBox{

    private StateButton previousPage;
    private StateButton  nextPage;
    private StateButton reloadButton;
    private HBox buttonContainer;
    protected VBox listContainer;
    protected QueryList<? extends ID> componentList;

    public ListPane(QueryList<? extends ID> componentList) {

        previousPage = new StateButton("Previous Page");
        nextPage = new StateButton("Next Page");
        reloadButton = new StateButton("Reload");

        previousPage.setInactive();
        nextPage.setActive();
        reloadButton.setActive();

        buttonContainer = new HBox(previousPage, nextPage, reloadButton);
        listContainer = new VBox();

        this.getChildren().add(buttonContainer);
        this.getChildren().add(listContainer);
        this.componentList = componentList;

        setOnClick();
        setStyling();
        renderList();
    }

    private void setStyling() {
        buttonContainer.setPadding(new Insets(10));
        buttonContainer.setSpacing(10);
    }

    private void setOnClick() {
        previousPage.loadSetOnMouseClicked(e -> {

            if(componentList.getCurrentPage() == 1) {
                previousPage.setInactive();
                return;
            }

            long start = System.nanoTime();
            try {
                componentList.getPreviousPage();
                renderList();
                if(componentList.getCurrentPage() == 1) {
                    previousPage.setInactive();
                    return;
                } else {
                    previousPage.setActive();
                    nextPage.setActive();
                }
            } catch (FirstPageException ex) {//TODO Exceptions
                throw new RuntimeException(ex);
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Full load Timing: " + (System.nanoTime() - start)/1000000 + " ms");
        }, false);

        nextPage.loadSetOnMouseClicked(e -> {

            if(componentList.getCurrentPage() == componentList.getPagesAmount()) {
                nextPage.setInactive();
                return;
            }

            long start = System.nanoTime();
            try {
                componentList.getNextPage();
                renderList();
                if(componentList.getCurrentPage() == componentList.getPagesAmount()) {
                    nextPage.setInactive();
                } else {
                    previousPage.setActive();
                    nextPage.setActive();
                }
            } catch (LastPageException ex) { //TODO Exception
                throw new RuntimeException(ex);
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            }
            System.out.println("Full load Timing: " + (System.nanoTime() - start)/1000000 + " ms");
        }, false);

        reloadButton.loadSetOnMouseClicked(e -> {
            try {
                componentList.updateList();
                renderList();
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex); // TODO Exception Handling
            }
        });
    }

    abstract void renderList();

}
