package GUI.Cards;

import Components.Product.ProductComponents.Tag;
import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class TagCard extends BaseCard {

    private Tag tag;
    private Label name;
    private Label description;
    private Label slug;
    private Label timesUsed;
    public TagCard(Tag tag) {
        this.tag = tag;
        setContent();
        setStyle();
        setEventListeners();
    }

    @Override
    protected void setContent() {
        name = new Label(tag.getName());
        description = new Label(tag.getDescription());
        slug = new Label(tag.getSlug());
        timesUsed = new Label(Integer.toString(tag.getCount()));
    }

    @Override
    protected void setStyle() {
        setGridConstraints();
        setColumnConstraints();

        this.getChildren().addAll(name, description, slug, timesUsed);
    }

    public void setGridConstraints() {
        GridPane.setConstraints(name, 0, 0);
        GridPane.setConstraints(description, 1, 0);
        GridPane.setConstraints(slug, 2, 0);
        GridPane.setConstraints(timesUsed, 3, 0);
    }

    public void setColumnConstraints() {
        this.getColumnConstraints().addAll(
                new ColumnConstraints(150),
                new ColumnConstraints(250),
                new ColumnConstraints(150),
                new ColumnConstraints(100)
        );
    }

    @Override
    protected void setEventListeners() {

    }
}
