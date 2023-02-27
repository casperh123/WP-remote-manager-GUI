package Components.Product;

import Components.Product.ProductComponents.Attribute;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

class AttributeTest {
    @Test
    public void attributeCanBeGeneratedFromJson() {

        Attribute attribute = JsonIterator.deserialize("{\"id\":3,\"name\":\"Mannes in a Mannesman\",\"position\":5,\"visible\":true,\"variation\":false,\"options\":[\"Men\", \"Mannes\", \"Menner\"]}", Attribute.class);

        System.out.println(attribute);

        assert(attribute.getId() == 3);
        assert(attribute.getName().equals("Mannes in a Mannesman"));
        assert(attribute.getPosition() == 5);
        assert(attribute.isVisible());
        assert(!attribute.isVariation());
    }

}