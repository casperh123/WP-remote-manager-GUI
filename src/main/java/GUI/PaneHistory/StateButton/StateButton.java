package GUI.PaneHistory.StateButton;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class StateButton extends Button {

    private Background activeBackground;
    private Background inactiveBackGround;

    public StateButton() {
        super();
    }

    public StateButton(String var1) {
        super(var1);
        this.activeBackground = new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.2), new CornerRadii(5), new Insets(0)));
        this.inactiveBackGround = new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.05), new CornerRadii(5), new Insets(0)));
        this.setBackground(inactiveBackGround);
    }

    public void setInactive() {
        this.setBackground(inactiveBackGround);
    }

    public void setActive() {
        this.setBackground(activeBackground);
    }
}
