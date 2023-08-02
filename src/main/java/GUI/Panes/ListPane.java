package GUI.Panes;

import Components.Interfaces.ID;
import GUI.Panes.ListPaneComponents.PaginationControl;
import Lists.QueryList;
import javafx.scene.control.Label;
import javafx.scene.layout.*;

public abstract class ListPane extends VBox {

    private PaginationControl paginationControl;
    private HBox statusButtonContainer;
    protected VBox listContainer;
    protected QueryList<? extends ID> componentList;

    public ListPane(QueryList<? extends ID> componentList) {

        this.componentList = componentList;

        setContent();
        setEventListeners();
    }

    private void setContent() {
        paginationControl = new PaginationControl(componentList);
        listContainer = new VBox();

        this.getChildren().add(paginationControl);
        this.getChildren().add(listContainer);
    }

    private void setEventListeners() {
        paginationControl.currentPageProperty().addListener((obs, oldPage, newPage) -> {
            renderList();
        });
    }

    abstract void renderList();

}
