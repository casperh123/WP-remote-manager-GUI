package GUI.Cards;

import GUI.Enums.State;
import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public abstract class BaseCard extends GridPane {

    // Abstract methods to be implemented by child classes
    protected abstract void setStyle();
    protected abstract void setContent();
    protected abstract void setEventListeners();

    public BaseCard() {
        this.setPadding(new Insets(20));
        setBackgroundState(State.INACTIVE);
    }

    public void loadSetOnMouseClicked(Consumer<MouseEvent> onClickFunction) {

        this.setOnMousePressed(e ->
                this.setBackgroundState(State.LOADING)
        );

        this.setOnMouseReleased(e -> {
            onClickFunction.accept(e);
            setBackgroundState(State.ACTIVE);
        });
    }

    // Common utility methods
    public void setBackgroundState(State state) {
        switch (state) {
            case ACTIVE ->
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.3), new CornerRadii(5), new Insets(5))));
            case INACTIVE ->
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(5))));
            case LOADING ->
                    this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.2), new CornerRadii(5), new Insets(5))));
        }
    }
}
