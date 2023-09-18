package GUI.Panes.ListPaneComponents;

import GUI.Components.StateButton;
import GUI.Enums.State;
import Lists.StatusFilter.StatusFilter;

public class StatusFilterSelector extends StateButton {

    private StatusFilter status;

    public StatusFilterSelector(StatusFilter status, int amount) {
        super(status +  " " + amount);
        this.status = status;
        this.setUnderline(true);
        if(amount == 0) {
            setBackgroundState(State.INACTIVE);
        } else {
            setBackgroundState(State.ACTIVE);
        }
    }

    public StatusFilter getStatus() {
        return status;
    }

}
