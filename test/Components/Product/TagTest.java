package Components.Product;

import Components.Product.ProductComponents.Tag;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

public class TagTest {
    @Test
    public void metaDataCanBeGeneratedFromJson() {
        Tag tag = JsonIterator.deserialize("{\"id\": 32, \"name\": \"Priority\", \"slug\": \"priority\"}", Tag.class);

        assert(tag.getId() == 32);
        assert(tag.getName().equals("Priority"));
        assert(tag.getSlug().equals("priority"));
    }
}
