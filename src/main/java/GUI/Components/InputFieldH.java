package GUI.Components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class InputFieldH extends GridPane {

    private Label text;
    private TextField textField;

    public InputFieldH(String text, String inputFieldContent) {

        this.text = new Label(text);
        this.textField = new TextField();

        GridPane.setConstraints(this.text, 0, 0);
        GridPane.setConstraints(textField, 1, 0);

        this.getChildren().addAll(this.text, textField);

        ColumnConstraints columnConstraint1 = new ColumnConstraints(100);
        ColumnConstraints columnConstraint2 = new ColumnConstraints(100);

        this.getColumnConstraints().addAll(columnConstraint1, columnConstraint2);
    }

    public String getText() {
        return textField.getText();
    }

    public void setString(String text) {
        textField.setText(text);
    }
}
