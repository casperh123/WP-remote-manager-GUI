package Components.Product;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;

public class ProductShippingClass extends AbstractComponent {
    private String slug;
    private String description;
    private int count;

    @JsonCreator
    public ProductShippingClass() {}

    @Override
    public String toString() {
        return "ProductShippingClass{" +
                "slug='" + slug + '\'' +
                ", description='" + description + '\'' +
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

    public String getSlug() {
        return slug;
    }

    public String getDescription() {
        return description;
    }

    public int getCount() {
        return count;
    }
}
