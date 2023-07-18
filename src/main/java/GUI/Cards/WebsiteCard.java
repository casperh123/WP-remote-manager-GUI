package GUI.Cards;

import Website.Website;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

import java.util.function.Consumer;

public class WebsiteCard extends ListCard {

    Website website;

    public WebsiteCard(Website website) {

        super();

        Label websiteNameLabel = new Label(website.getName());

        this.website = website;

        this.getChildren().add(new Text(website.getName()));

        this.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(0))));
    }

    public Website getWebsite() {
        return website;
    }
}
