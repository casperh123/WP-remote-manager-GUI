package Lists;

import Exceptions.BadHTTPResponseException;
import Components.Product.Product;
import REST.RESTConnection;
import REST.RESTEndpoints;
import Website.APICredentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

public class ProductListTest {

    private QueryList<Product> products;

    @AfterEach
    public void tearDown() {
        products = null;
    }

    @Test
    public void updateListTest() throws BadHTTPResponseException {
        products = new UnpaginatedQueryList<>(
                new RESTConnection("https://staging-skadedyrsexpertendk-test.kinsta.cloud/",
                        new APICredentials("Casper",
                                "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558",
                                "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357")), RESTEndpoints.getProductsEndpoint(),
                Product.class);
        for(Product product : products) {
            System.out.println(product);
        }
        assert(products.size() > 0);
    }
}
