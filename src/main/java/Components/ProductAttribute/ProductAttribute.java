package Components.ProductAttribute;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class ProductAttribute extends AbstractComponent {

    private String slug;
    private String type;

    @JsonProperty("order_by")
    private String orderBy;
    @JsonProperty("has_archives")
    private boolean hasArchives;

    @JsonCreator
    public ProductAttribute() {}

    @Override
    public String toString() {
        return "ProductAttribute{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", slug='" + slug + '\'' +
                ", type='" + type + '\'' +
                ", orderBy='" + orderBy + '\'' +
                ", hasArchives=" + hasArchives +
                '}';
    }


    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    public boolean isHasArchives() {
        return hasArchives;
    }

    public void setHasArchives(boolean hasArchives) {
        this.hasArchives = hasArchives;
    }
}
