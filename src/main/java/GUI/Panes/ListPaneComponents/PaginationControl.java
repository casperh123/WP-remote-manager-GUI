package GUI.Panes.ListPaneComponents;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import GUI.Components.StateButton;
import GUI.Enums.State;
import Lists.QueryList;
import javafx.application.Platform;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;

public class PaginationControl extends HBox {

    private QueryList<?> queryList;
    private StateButton previousPage;
    private StateButton nextPage;
    private StateButton reloadButton;
    private TextField currentPage;
    private Label elements;
    private Label pages;

    private final IntegerProperty currentPageProperty = new SimpleIntegerProperty();
    private Label errorMessage;

    public PaginationControl(QueryList<? extends ID> queryList) {
        this.queryList = queryList;
        setContent();
        setStyling();
        setEventListeners();
    }

    private void setContent() {

        previousPage = new StateButton("Previous Page");
        nextPage = new StateButton("Next Page");
        pages = new Label("/ " + queryList.getPages());
        reloadButton = new StateButton("Reload");
        currentPage = new TextField(Integer.toString(queryList.getPage()));
        elements = new Label(queryList.loadedItems() + " elements");
        errorMessage = new Label("");
        errorMessage.setTextFill(Color.RED);  // Set the error message to red.

        updateListMetrics();

        this.getChildren().addAll(elements, currentPage, pages, previousPage, nextPage, reloadButton, errorMessage);
    }

    private void setStyling() {

        elements.underlineProperty().set(true);

        currentPage.setMaxWidth(50);
        currentPage.setAlignment(Pos.CENTER_LEFT);

        this.setSpacing(10);
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(10));
    }

    private void setEventListeners() {
        previousPage.loadSetOnMouseClicked(e -> {
            if(queryList.getPage() == 1) {
                updateListMetrics();
                return;
            }

            try {
                queryList.previousPage();
                currentPageProperty.set(queryList.getPage());
                clearErrorMessage();
                updateListMetrics();
            } catch (Exception ex) {
                setErrorMessage(ex.getMessage());
            }
        });

        nextPage.loadSetOnMouseClicked(e -> {
            if(queryList.getPage() == queryList.getPages()) {
                updateListMetrics();
                return;
            }

            try {
                queryList.nextPage();
                currentPageProperty.set(queryList.getPage());
                clearErrorMessage();
                updateListMetrics();
            } catch (Exception ex) {
                setErrorMessage(ex.getMessage());
            }
        });

        currentPage.setOnKeyPressed(e -> {
            if(!(e.getCode() == KeyCode.ENTER)) {
                return;
            }

            int enteredPage = Integer.parseInt(currentPage.getText());

            if(enteredPage >= 1 && enteredPage <= queryList.getPages()) {
                try {
                    queryList.setPage(enteredPage);
                    currentPageProperty.set(queryList.getPage());
                    clearErrorMessage();
                    updateButtonStates();
                } catch (Exception ex) {
                    setErrorMessage(ex.getMessage());
                }
            } else {
                setErrorMessage("Page number out of range.");
            }
        });

        reloadButton.loadSetOnMouseClicked(e -> {
            try {
                queryList.refresh();
                currentPageProperty.set(queryList.getPage());
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex); // TODO Exception Handling
            }
        });
    }

    private void setErrorMessage(String message) {
        errorMessage.setText(message);
    }

    private void clearErrorMessage() {
        errorMessage.setText("");
    }

    public IntegerProperty currentPageProperty() {
        return currentPageProperty;
    }

    public void updateListMetrics() {
        updateButtonStates();
        updateCurrentPageField();
        pages.setText("/ " + queryList.getPages());
    }

    private void updateButtonStates() {

        int currentPage = queryList.getPage();
        int totalPages = queryList.getPages();

        Platform.runLater(() -> {
            if (currentPage == 1) {
                previousPage.setBackgroundState(State.INACTIVE);
                nextPage.setBackgroundState(State.ACTIVE);
            } else if (currentPage == totalPages) {
                previousPage.setBackgroundState(State.ACTIVE);
                nextPage.setBackgroundState(State.INACTIVE);
            } else {
                previousPage.setBackgroundState(State.ACTIVE);
                nextPage.setBackgroundState(State.ACTIVE);
            }
        });
    }

    private void updateCurrentPageField() {
        currentPage.setText(Integer.toString(queryList.getPage()));
    }
}
