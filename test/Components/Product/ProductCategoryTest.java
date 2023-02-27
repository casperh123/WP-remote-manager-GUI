package Components.Product;

import Components.Product.ProductComponents.ProductCategory;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

public class ProductCategoryTest {
    @Test
    public void metaDataCanBeGeneratedFromJson() {
        ProductCategory productCategory = JsonIterator.deserialize("{\"id\":457,\"name\":\"GreenLine\",\"slug\":\"greenline\"}", ProductCategory.class);

        assert(productCategory.getId() == 457);
        assert(productCategory.getName().equals("GreenLine"));
        assert(productCategory.getSlug().equals("greenline"));
    }
}
