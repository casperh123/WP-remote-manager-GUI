package GUI.Cards;

import Components.Category.Category;
import GUI.ComponentPages.CategoryPage;
import GUI.GlobalState.GlobalComponents;
import Utility.WebUtilities;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class CategoryCard extends BaseCard {

    private ImageView categoryImage;
    private Label categoryName;
    private Label descriptionPreview;
    private Label slug;
    private Label productsInCategory;
    private Category category;

    public CategoryCard(Category category) {
        this.category = category;
        setContent();
        setStyle();
        setEventListeners();
    }

    protected void setContent() {
        categoryImage = WebUtilities.getImageView(category.getImage().getImageUrl(), 100, 100);
        categoryName = new Label(category.getName());
        descriptionPreview = new Label(WebUtilities.sanitizeHtml(category.getDescription()));
        slug = new Label(category.getSlug());
        productsInCategory = new Label(Integer.toString(category.getCount()));

        setGridConstraints();
        this.getChildren().addAll(categoryImage, categoryName, descriptionPreview, slug, productsInCategory);
    }

    private void setGridConstraints() {
        GridPane.setConstraints(categoryImage, 0, 0);
        GridPane.setConstraints(categoryName, 1, 0);
        GridPane.setConstraints(descriptionPreview, 2, 0);
        GridPane.setConstraints(slug, 3, 0);
        GridPane.setConstraints(productsInCategory, 4, 0);
    }

    protected void setStyle() {
        this.setAlignment(Pos.CENTER_LEFT);
        this.setHgap(10);

        setupColumnConstraints();
        styleComponents();
    }

    private void setupColumnConstraints() {
        this.getColumnConstraints().addAll(
                new ColumnConstraints(100),
                new ColumnConstraints(200),
                new ColumnConstraints(200),
                new ColumnConstraints(100),
                new ColumnConstraints(50)
        );
    }

    private void styleComponents() {
        categoryImage.setFitWidth(100);
        categoryImage.setFitHeight(100);
    }

    protected void setEventListeners() {
        this.setOnMouseClicked(e -> GlobalComponents.getInstance().setMainContent(new CategoryPage(category)));
    }
}
