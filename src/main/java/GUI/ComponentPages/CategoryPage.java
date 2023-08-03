package GUI.ComponentPages;

import Components.Category.Category;
import Components.WPImage;
import GUI.Components.InputFieldH;
import GUI.Components.StateButton;
import GUI.Utility.QuillHTMLEditor;
import Utility.GetWebImage;
import javafx.geometry.Insets;
import javafx.scene.control.ComboBox;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class CategoryPage extends VBox {

    private InputFieldH name;
    private InputFieldH slug;
    private ComboBox<String> mainCategoryList;
    private Category category;
    private QuillHTMLEditor description;
    private HBox thumbnailContainer;
    private WPImage wpThumbnail;
    private ImageView thumbnail;
    private StateButton updateButton;

    public CategoryPage(Category category) {

        this.category = category;
        wpThumbnail = category.getImage();

        setContent();
        setStyle();
        setEventListeners();
    }

    private void setContent() {
        name = new InputFieldH("Name", category.getName());
        slug = new InputFieldH("Short Title", category.getSlug());
        initializeComboBoxes();
        description = new QuillHTMLEditor(category.getDescription());
        thumbnailContainer = new HBox();
        thumbnail = GetWebImage.getImageView(wpThumbnail.getImageUrl(), 100, 100);
        updateButton = new StateButton("Update");

        thumbnailContainer.getChildren().add(thumbnail);

        this.getChildren().addAll(name, slug, description.getWebView(), thumbnailContainer, updateButton);
    }

    private void setStyle() {
        this.setPadding(new Insets(20));
        this.setSpacing(10);
    }

    private void setEventListeners() {

    }

    private void initializeComboBoxes() {

    }


}
