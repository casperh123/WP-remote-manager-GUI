package Components.Product;

import Components.Product.ProductComponents.Download;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

public class DownloadTest {
    @Test
    public void downloadCanBeGeneratedFromJson() {
        Download download = JsonIterator.deserialize("{\"id\": 0, \"name\": \"Woo Single\", \"file\": \"http://demo.woothemes.com/woocommerce/wp-content/uploads/sites/56/2013/06/cd_4_angle.jpg\"}", Download.class);

        assert(download.getId() == 0);
        assert(download.getName().equals("Woo Single"));
        assert(download.getFile().equals("http://demo.woothemes.com/woocommerce/wp-content/uploads/sites/56/2013/06/cd_4_angle.jpg"));
    }
}
