package Lists;

import Components.Order.Order;
import Exceptions.BadHTTPResponseException;
import REST.RESTConnection;
import REST.RESTEndpoints;
import Website.APICredentials;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.fail;

public class OrderListTest {
    @Test
    public void customerQueryListTest() {
        PaginatedQueryList<Order> customers = null;
        try {
            customers = new PaginatedQueryList<>(
                    new RESTConnection("https://staging-skadedyrsexpertendk-test.kinsta.cloud/",
                            new APICredentials("Casper",
                                    "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558",
                                    "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357")), RESTEndpoints.getOrdersEndpoint(),
                    Order.class);
        } catch (BadHTTPResponseException e) {
            fail(e.getMessage());
        }

        customers.forEach(System.out::println);

        assert(customers.size() != 0);
    }
}
