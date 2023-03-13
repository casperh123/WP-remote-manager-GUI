package Lists;

import Exceptions.BadHTTPResponseException;
import Components.Product.Product;
import REST.RESTConnection;
import REST.RESTEndpoints;
import Website.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class ProductListTest {

    private QueryList<Product> products;

    @BeforeEach
    public void setUp() throws BadHTTPResponseException, URISyntaxException {
        products = new UnpaginatedQueryList<>(
                new RESTConnection("https://staging-skadedyrsexpertendk-test.kinsta.cloud/",
                new User("Casper",
                        "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558",
                        "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357")), RESTEndpoints.getProductsEndpoint(),
                Product.class);
    }

    @AfterEach
    public void tearDown() {
        products = null;
    }

    @Test
    public void updateListTest() {
        for(Product product : products) {
            System.out.println(product);
        }
        assert(products.size() > 0);
    }
}
