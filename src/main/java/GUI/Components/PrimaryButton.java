package GUI.Components;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;

public class PrimaryButton extends Button {

    public PrimaryButton(String content) {
        super(content);
        setStyle();
    }

    public PrimaryButton() {
        super();
        setStyle();
    }

    private void setStyle() {
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.2), new CornerRadii(5), new Insets(0))));
    }
}
