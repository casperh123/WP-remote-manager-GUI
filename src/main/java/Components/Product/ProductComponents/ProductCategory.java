package Components.Product.ProductComponents;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;

public class ProductCategory extends AbstractComponent {

    //Name Read-Only

    //Read-only
    private String slug;


    public ProductCategory(int id, String name, String slug) {
        super(id, name);
        this.slug = slug;
    }

    @JsonCreator
    public ProductCategory() {

    }

    public String getSlug() {
        return slug;
    }
}
