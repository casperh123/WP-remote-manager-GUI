package GUI.Components;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class InputFieldH extends GridPane {

    private Label text;
    private TextField textField;

    public InputFieldH(String label, String content) {
        setContent(label, content);
        setGridConstraints();
        setColumnConstraints();
    }

    public String getContent() {
        return textField.getText();
    }

    public void setContent(String content) {
        textField.setText(content);
    }

    private void setContent(String label, String content) {
        this.text = new Label(label);
        this.textField = new TextField(content);
        this.getChildren().addAll(this.text, textField);
    }

    private void setGridConstraints() {
        GridPane.setConstraints(this.text, 0, 0);
        GridPane.setConstraints(textField, 1, 0);
    }

    private void setColumnConstraints() {
        this.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(100)
        );
    }
}
