package GUI.Panes.ListPaneComponents;

import GUI.Components.StateButton;
import Lists.QueryList.StatusFilter;

public class StatusFilterSelector extends StateButton {

    private StatusFilter status;

    public StatusFilterSelector(StatusFilter status, int amount) {
        super(status +  " " + amount);
        this.status = status;
        this.setUnderline(true);
        if(amount == 0) {
            this.setInactive();
        } else {
            this.setActive();
        }
    }

    public StatusFilter getStatus() {
        return status;
    }

}
