package GUI.Cards;

import javafx.geometry.Insets;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public abstract class ListCard extends GridPane {

    public ListCard() {
        this.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(10))));
    }


    protected abstract void setContent();

    protected abstract void setStyle();

    protected abstract void setEventListeners();

    public void loadSetOnMouseClicked(Consumer<MouseEvent> onClickFunction) {

        this.setOnMousePressed(e -> {
            this.setLoading();
        });

        this.setOnMouseReleased(e -> {
            onClickFunction.accept(e);
            setActive();
        });
    }

    public void setActive() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.3), new CornerRadii(5), new Insets(0))));
    }

    public void setInactive() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(0))));
    }

    public void setLoading() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.2), new CornerRadii(5), new Insets(0))));
    }
}
