package GUI.Panes;

import Components.Interfaces.ID;
import GUI.Panes.ListPaneComponents.PaginationControl;
import GUI.Panes.ListPaneComponents.StatusControl;
import Lists.QueryList;
import javafx.application.Platform;
import javafx.scene.layout.VBox;

public abstract class ListPane extends VBox {

    private PaginationControl paginationControl;
    private StatusControl statusControl;
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

        statusControl = new StatusControl(componentList.getStatuses(), componentList);

        this.getChildren().addAll(paginationControl, statusControl, listContainer);
    }

    private void setEventListeners() {
        paginationControl.currentPageProperty().addListener((obs, oldPage, newPage) -> {
            renderList();
        });

        statusControl.currentStatusProperty().addListener((obs, oldStatus, newStatus) -> {
            Platform.runLater(() -> {
                renderList();
                paginationControl.updateListMetrics();
            });
        });
    }

    abstract void renderList();

}
