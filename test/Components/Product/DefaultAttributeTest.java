package Components.Product;

import Components.Product.ProductComponents.DefaultAttribute;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

public class DefaultAttributeTest {
    @Test
    public void defaultAttributeCanBeGeneratedFromJson() {
        DefaultAttribute defaultAttribute = JsonIterator.deserialize("{\"id\": 6, \"name\": \"Color\", \"option\": \"black\"}", DefaultAttribute.class);

        assert(defaultAttribute.getId() == 6);
        assert(defaultAttribute.getName().equals("Color"));
        assert(defaultAttribute.getOption().equals("black"));
    }
}
