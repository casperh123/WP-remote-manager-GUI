package Components.Product;

import Components.MetaData;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

public class MetaDataTest {
    @Test
    public void metaDataCanBeGeneratedFromJson() {
        MetaData metaData = JsonIterator.deserialize("{\"id\":462059,\"key\":\"_wc_cog_cost\",\"value\":\"45\"}", MetaData.class);

        assert(metaData.getId() == 462059);
        assert(metaData.getKey().equals("_wc_cog_cost"));
        assert(metaData.getValue().equals("45"));
    }
}
