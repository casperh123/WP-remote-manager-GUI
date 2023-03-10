package Components.Category;

import Components.AbstractComponent;
import Components.WPImage;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class Category extends AbstractComponent {

    private int parent;
    private String slug;
    private String description;
    private String display;
    private WPImage image;

    @JsonProperty("menu_order")
    private int menuOrder;
    private int count;

    @JsonCreator
    public Category() {
    }

    @Override
    public String toString() {
        return "Category{" +
                "parent=" + parent +
                ", slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", display='" + display + '\'' +
                ", image=" + image +
                ", menuOrder=" + menuOrder +
                ", count=" + count +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getParent() {
        return parent;
    }

    public void setParent(int parent) {
        this.parent = parent;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDisplay() {
        return display;
    }

    public void setDisplay(String display) {
        this.display = display;
    }
}
