package GUI.Components;

import GUI.Enums.State;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class StateButton extends Button {

    public StateButton() {
        super();
    }

    public StateButton(String var1) {
        super(var1);
        setBackgroundState(State.INACTIVE);
    }

    public void loadSetOnMouseClicked(Consumer<MouseEvent> onClickFunction, boolean automaticallyUpdate) {

        this.setOnMousePressed(e -> {
            setBackgroundState(State.LOADING);
        });

        this.setOnMouseReleased(e -> {
            onClickFunction.accept(e);
            if(automaticallyUpdate) setBackgroundState(State.ACTIVE);
        });
    }

    public void loadSetOnMouseClicked(Consumer<MouseEvent> onClickFunction) {
        loadSetOnMouseClicked(onClickFunction, true);
    }

    public void setBackgroundState(State state) {
        switch (state) {
            case ACTIVE ->
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.3), new CornerRadii(5), new Insets(0))));
            case INACTIVE ->
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(0))));
            case LOADING ->
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.2), new CornerRadii(5), new Insets(0))));
        }
    }
}
