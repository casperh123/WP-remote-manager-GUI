package Components.Product;

import Components.Product.ProductComponents.Dimensions;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

public class DimensionsTest {
    @Test
    public void dimensionsCanBeGeneratedFromJson() {
        Dimensions dimensions = JsonIterator.deserialize("{\"length\":\"1\",\"width\":\"50\",\"height\":\"100\"}", Dimensions.class);

        assert(dimensions.getLength().equals("1"));
        assert(dimensions.getWidth().equals("50"));
        assert(dimensions.getHeight().equals("100"));
    }
}
