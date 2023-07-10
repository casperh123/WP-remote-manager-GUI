package GUI.PaneHistory.StateButton;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.function.Consumer;
import java.util.function.Function;

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

    public void setLoading() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.13), new CornerRadii(5), new Insets(0))));
    }

    public void loadSetOnMouseClicked(Consumer<MouseEvent> onClickFunction, boolean automaticallyUpdate) {

        this.setOnMousePressed(e -> {
            this.setLoading();
        });

        this.setOnMouseReleased(e -> {
            onClickFunction.accept(e);
            if(automaticallyUpdate) setActive();
        });
    }

    public void loadSetOnMouseClicked(Consumer<MouseEvent> onClickFunction) {
        loadSetOnMouseClicked(onClickFunction, true);
    }
}
