package GUI.Cards;

import Website.Website;
import javafx.geometry.Insets;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;

public class WebsiteCard extends BaseCard {

    Website website;

    public WebsiteCard(Website website) {

        this.website = website;

        setContent();
        setStyle();
        setEventListeners();
    }

    public Website getWebsite() {
        return website;
    }

    @Override
    protected void setStyle() {
        this.setPadding(new Insets(20));
        this.setBackground(new Background(new BackgroundFill(Color.rgb(0, 0, 0, 0.1), new CornerRadii(5), new Insets(0))));
    }

    @Override
    protected void setContent() {
        this.getChildren().add(new Text(website.getName()));
    }

    @Override
    protected void setEventListeners() {

    }
}
