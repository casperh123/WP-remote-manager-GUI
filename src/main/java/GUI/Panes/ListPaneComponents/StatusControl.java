package GUI.Panes.ListPaneComponents;

import Components.Interfaces.ID;
import Lists.QueryList;
import Lists.QueryList.StatusFilter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

import java.io.IOException;

public class StatusControl extends HBox {

    private QueryList<? extends ID> componentList;
    private final ObjectProperty<StatusFilter> currentStatusProperty = new SimpleObjectProperty<>();

    public StatusControl(StatusFilter[] statuses, QueryList<? extends ID> componentList) {
        this.componentList = componentList;

        setContent(statuses);
        setStyling();
    }

    private void setContent(StatusFilter[] statuses) {

        for(StatusFilter status : statuses) {

            StatusFilterSelector statusControl = new StatusFilterSelector(status, componentList.elementsWithStatus(status));

            statusControl.loadSetOnMouseClicked(e -> {
                currentStatusProperty.set(statusControl.getStatus());
                try {
                    componentList.filterByStatus(statusControl.getStatus());
                } catch (IOException ex) {
                    throw new RuntimeException(ex); //TODO Exception lol
                }
            });
            this.getChildren().add(statusControl);
        }
    }

    private void setStyling() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(10));
        this.setSpacing(10);
    }

    public ObjectProperty<StatusFilter> currentStatusProperty() {
        return currentStatusProperty;
    }
}
