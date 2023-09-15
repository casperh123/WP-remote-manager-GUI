package GUI.Components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class CopyableText extends HBox {

    private Label text;
    private static Image clipboardImage;
    private ImageView clipboardIcon;
    private Clipboard clipboard;

    static {
        try {
            clipboardImage = new Image(new FileInputStream("src/main/resources/Images/clipboard.png"), 30, 30, true, true);
        } catch (FileNotFoundException e) {
            //TODO exception handling
        }
    }

    public CopyableText(String text) {

        setContent(text);

        clipboardIcon.setVisible(false);

        setStyling();
        setEventHandlers();
    }

    private void setStyling() {
        this.setAlignment(Pos.CENTER);

        clipboardIcon.setFitWidth(15);
        clipboardIcon.setFitHeight(15);
        clipboardIcon.pickOnBoundsProperty().set(true);
    }

    private void setContent(String text) {
        this.text = new Label(text);
        this.clipboard = Clipboard.getSystemClipboard();
        this.clipboardIcon = new ImageView(clipboardImage);

        this.getChildren().addAll(this.text, clipboardIcon);
    }

    private void setEventHandlers() {
        this.setOnMouseEntered(e -> {
            clipboardIcon.setVisible(true);
        });


        this.setOnMouseExited(e -> {
            clipboardIcon.setVisible(false);
        });

        clipboardIcon.setOnMouseClicked(e -> {
            if(clipboardIcon.isVisible()) {
                e.consume();
            }
        });

        clipboardIcon.setOnMousePressed(e -> {
            this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.3), new CornerRadii(5), new Insets(10))));
        });

        clipboardIcon.setOnMouseReleased(e -> {
            this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0), new CornerRadii(5), new Insets(10))));

            if(clipboardIcon.isVisible()) {
                ClipboardContent clipboardContent = new ClipboardContent();
                clipboardContent.putString(text.getText());
                clipboard.setContent(clipboardContent);
            }
        });
    }

    public void setFont(Font font) {
        text.setFont(font);
    }

    public void setTextPadding(Insets insets) {
        text.setPadding(insets);
    }

    public void setWrapText(boolean b) {
        text.setWrapText(b);
    }

    public void setTextMaxWidth(double v) {
        text.setMaxWidth(v);
    }

    public void setTextUnderline(boolean b) {
        text.setUnderline(b);
    }

    public void setTextFill(Paint paint) {
        text.setTextFill(paint);
    }
}
