package GUI.Cards;

import Website.Website;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

import java.util.function.Consumer;

public class WebsiteCard extends HBox {

    Website website;

    public WebsiteCard(Website website) {

        Label websiteNameLabel = new Label(website.getName());

        this.website = website;

        this.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(0))));
        this.getChildren().add(websiteNameLabel);;

    }

    public Website getWebsite() {
        return website;
    }

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
