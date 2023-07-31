package GUI.Panes;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import GUI.Components.StateButton;
import Lists.QueryList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.*;

public abstract class ListPane extends VBox {

    private Label totalItems;
    private TextField currentPage;
    private StateButton previousPage;
    private StateButton  nextPage;
    private StateButton reloadButton;
    private HBox pageSelectorWrapper;
    private HBox topButtonContainer;
    private HBox bottomButtonContainer;
    protected VBox listContainer;
    protected QueryList<? extends ID> componentList;

    public ListPane(QueryList<? extends ID> componentList) {

        this.componentList = componentList;

        setContent();
        setEventListeners();
        setStyling();
    }

    private void setContent() {

        if(componentList.size() == 0) {
            this.getChildren().add(new Label("No elements found"));
            return;
        }

        totalItems = new Label(Integer.toString(componentList.getTotalItems()) + " Elements");
        currentPage = new TextField(Integer.toString(componentList.getCurrentPage()));
        previousPage = new StateButton("Previous Page");
        nextPage = new StateButton("Next Page");
        reloadButton = new StateButton("Reload");
        pageSelectorWrapper = new HBox();

        pageSelectorWrapper.getChildren().addAll(currentPage, new Label("/"), new Label(Integer.toString(componentList.getPagesAmount())));

        topButtonContainer = new HBox(totalItems, pageSelectorWrapper, previousPage, nextPage, reloadButton);
        listContainer = new VBox();

        this.getChildren().add(topButtonContainer);
        this.getChildren().add(listContainer);
    }

    private void setStyling() {
        if(componentList.getCurrentPage() == 1) {
            nextPage.setActive();
        } else if(componentList.getCurrentPage() == componentList.getPagesAmount()) {
            previousPage.setActive();
        } else {
            previousPage.setActive();
            nextPage.setActive();
        }
        reloadButton.setActive();

        pageSelectorWrapper.setSpacing(5);
        pageSelectorWrapper.setAlignment(Pos.CENTER_LEFT);

        topButtonContainer.setPadding(new Insets(10));
        topButtonContainer.setSpacing(10);
        topButtonContainer.setAlignment(Pos.CENTER_LEFT);

        currentPage.setMaxWidth(50);
        currentPage.setAlignment(Pos.CENTER);
    }

    private void setEventListeners() {
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
                } else {
                    previousPage.setActive();
                    nextPage.setActive();
                }
            } catch (FirstPageException ex) {//TODO Exceptions
                throw new RuntimeException(ex);
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex);
            }

            currentPage.setText(Integer.toString(componentList.getCurrentPage()));

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

            currentPage.setText(Integer.toString(componentList.getCurrentPage()));

            System.out.println("Full load Timing: " + (System.nanoTime() - start)/1000000 + " ms");
        }, false);

        reloadButton.loadSetOnMouseClicked(e -> {
            try {
                componentList.refresh();
                renderList();
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex); // TODO Exception Handling
            }
        });

        currentPage.setOnKeyPressed(e -> {

            if(!(e.getCode() == KeyCode.ENTER)) {
                return;
            }

            int currentPageValue = Integer.parseInt(currentPage.getText());

            if(!(currentPageValue <= 0 || currentPageValue > componentList.getPagesAmount())) {
                try {
                    componentList.setPage(currentPageValue);
                    renderList();
                    if(currentPageValue == componentList.getPagesAmount()) {
                        previousPage.setActive();
                        nextPage.setInactive();
                    } else if(currentPageValue == 1) {
                        nextPage.setActive();
                        previousPage.setInactive();
                    } else {
                        previousPage.setActive();
                        nextPage.setActive();
                    }
                } catch (BadHTTPResponseException ex) { //TODO exception handling
                    throw new RuntimeException(ex);
                }
            }

        });
    }

    abstract void renderList();

}
