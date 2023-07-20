package GUI.Components;

import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;

import java.util.function.Consumer;

public class LoadingTextField extends TextField {
    public LoadingTextField(String text) {
        super(text);
    }

    public LoadingTextField() {
        super();
    }

    public void loadSetOnKeyPressed(Consumer<KeyEvent> onClickFunction, KeyCode keyCode) {

        this.setOnKeyPressed(e -> {
            if(e.getCode() == keyCode) {
                this.setDisable(true);
            }
        });

        this.setOnKeyReleased(e -> {
            if(e.getCode() == keyCode) {
                onClickFunction.accept(e);
                this.setDisable(false);
            }
        });
    }
}
