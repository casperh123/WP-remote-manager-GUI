package Components.Product.ProductComponents;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;

public class Tag extends AbstractComponent {

    private String slug;
    private String description;
    private int count;

    @JsonCreator
    public Tag() {
    }

    public Tag(int id, String name, String slug) {
        super(id, name);
        this.slug = slug;
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

    public int getCount() {
        return count;
    }
}
