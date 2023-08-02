package GUI.Panes.ListPaneComponents;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Lists.QueryList;
import Lists.QueryList.StatusFilter;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.HBox;

public class StatusControl extends HBox {

    private ComboBox<StatusFilter> statusComboBox;
    private QueryList<? extends ID> componentList;
    private final ObjectProperty<StatusFilter> currentStatusProperty = new SimpleObjectProperty<>();

    public StatusControl(StatusFilter[] statuses, QueryList<? extends ID> componentList) {
        this.componentList = componentList;

        setContent(statuses);
        setStyling();
        setEventListeners();
    }

    private void setContent(StatusFilter[] statuses) {
        statusComboBox = new ComboBox<>();
        statusComboBox.getItems().addAll(statuses);
        statusComboBox.getSelectionModel().selectFirst();

        this.getChildren().add(statusComboBox);
    }

    private void setStyling() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(10));
    }

    private void setEventListeners() {
        statusComboBox.setOnAction(e -> {
            currentStatusProperty.set(statusComboBox.getSelectionModel().getSelectedItem());
            try {
                componentList.filterByStatus(statusComboBox.getSelectionModel().getSelectedItem());
            } catch (BadHTTPResponseException ex) {
                throw new RuntimeException(ex); //TODO Exception lol
            }
        });
    }

    public ObjectProperty<StatusFilter> currentStatusProperty() {
        return currentStatusProperty;
    }
}
