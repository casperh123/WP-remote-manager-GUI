package Components.ProductAttribute;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class ProductAttributeTerm extends AbstractComponent {

    private String slug;
    private String description;
    @JsonProperty("menu_order")
    private int menuOrder;
    private int count;

    @JsonCreator
    public ProductAttributeTerm() {}

    @Override
    public String toString() {
        return "ProductAttributeTerm{" +
                "slug='" + slug + '\'' +
                ", description='" + description + '\'' +
                ", menuOrder=" + menuOrder +
                ", count=" + count +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setMenuOrder(int menuOrder) {
        this.menuOrder = menuOrder;
    }

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public int getMenuOrder() {
        return menuOrder;
    }

    public int getCount() {
        return count;
    }
}
