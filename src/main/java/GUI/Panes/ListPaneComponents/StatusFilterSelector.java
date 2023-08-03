package GUI.Panes.ListPaneComponents;

import Lists.QueryList.StatusFilter;
import javafx.scene.control.Label;

public class StatusFilterSelector extends Label {

    private StatusFilter status;

    public StatusFilterSelector(StatusFilter status, int amount) {
        super(status +  " " + amount);
        this.status = status;
        this.setUnderline(true);
    }

    public StatusFilter getStatus() {
        return status;
    }

}
