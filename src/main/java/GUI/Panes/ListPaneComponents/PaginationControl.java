package GUI.Panes.ListPaneComponents;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import GUI.Components.StateButton;
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
        reloadButton = new StateButton("Reload");
        currentPage = new TextField(Integer.toString(queryList.getCurrentPage()));
        elements = new Label(queryList.getTotalItems() + " elements");
        errorMessage = new Label("");
        errorMessage.setTextFill(Color.RED);  // Set the error message to red.

        updateListMetrics();

        this.getChildren().addAll(elements, currentPage, new Label("/"), new Label(Integer.toString(queryList.getPagesAmount())), previousPage, nextPage, reloadButton, errorMessage);
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
            if(queryList.getCurrentPage() == 1) {
                updateListMetrics();
                return;
            }

            try {
                queryList.getPreviousPage();
                currentPageProperty.set(queryList.getCurrentPage());
                clearErrorMessage();
                updateListMetrics();
            } catch (Exception ex) {
                setErrorMessage(ex.getMessage());
            }
        });

        nextPage.loadSetOnMouseClicked(e -> {
            if(queryList.getCurrentPage() == queryList.getPagesAmount()) {
                updateListMetrics();
                return;
            }

            try {
                queryList.getNextPage();
                currentPageProperty.set(queryList.getCurrentPage());
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

            if(enteredPage >= 1 && enteredPage <= queryList.getPagesAmount()) {
                try {
                    queryList.setPage(enteredPage);
                    currentPageProperty.set(queryList.getCurrentPage());
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
                currentPageProperty.set(queryList.getCurrentPage());
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

    private void updateListMetrics() {
        updateButtonStates();
        updateCurrentPageField();
    }

    private void updateButtonStates() {

        int currentPage = queryList.getCurrentPage();
        int totalPages = queryList.getPagesAmount();

        Platform.runLater(() -> {
            if (currentPage == 1) {
                previousPage.setInactive();
                nextPage.setActive();
            } else if (currentPage == totalPages) {
                previousPage.setActive();
                nextPage.setInactive();
            } else {
                previousPage.setActive();
                nextPage.setActive();
            }
        });
    }

    private void updateCurrentPageField() {
        currentPage.setText(Integer.toString(queryList.getCurrentPage()));
    }
}
