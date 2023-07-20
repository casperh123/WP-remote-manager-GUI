package GUI.Cards;

import Components.Category.Category;
import Utility.GetWebImage;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class CategoryCard extends ListCard {


    ImageView categoryImage;
    Label categoryName;
    Label descriptionPreview;
    Label slug;
    Label productsInCategory;
    String sanitisedDescription;

    Category category;

    public CategoryCard(Category category) {
        this.category = category;
        sanitisedDescription = category.getDescription().replaceAll("<[^>]*>", "");

        setContent();
        setStyle();
    }

    public void setContent() {
        categoryImage = GetWebImage.getImage(category.getImage().getImageUrl());
        categoryName = new Label(category.getName());

        if(descriptionPreview == null) {
            descriptionPreview = new Label("-");
        } else {
            descriptionPreview = new Label(sanitisedDescription);
        }

        slug = new Label(category.getSlug());
        productsInCategory = new Label(Integer.toString(category.getCount()));

        GridPane.setConstraints(categoryImage, 0, 0);
        GridPane.setConstraints(categoryName, 1, 0);
        GridPane.setConstraints(descriptionPreview, 2, 0);
        GridPane.setConstraints(slug, 3, 0);
        GridPane.setConstraints(productsInCategory, 4, 0);

        this.getChildren().addAll(categoryImage, categoryName, descriptionPreview, slug, productsInCategory);
    }

    public void setStyle() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgap(10);

        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(200));
        this.getColumnConstraints().add(new ColumnConstraints(200));
        this.getColumnConstraints().add(new ColumnConstraints(100));
        this.getColumnConstraints().add(new ColumnConstraints(50));

        categoryImage.setFitWidth(100);
        categoryImage.setFitHeight(100);
    }
}
