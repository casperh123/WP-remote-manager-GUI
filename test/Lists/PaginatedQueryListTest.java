package Lists;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.RESTConnection;
import REST.RESTEndpoints;
import Website.User;
import org.junit.jupiter.api.Test;

public class PaginatedQueryListTest {

    PaginatedQueryList<Product> products = new PaginatedQueryList<>(
            new RESTConnection("https://staging-skadedyrsexpertendk-test.kinsta.cloud/",
                    new User("Casper",
                            "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558",
                            "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357")), RESTEndpoints.getProductsEndpoint(),
            Product.class);

    public PaginatedQueryListTest() throws BadHTTPResponseException {
    }


    @Test
    public void updateListCurrentPage() throws BadHTTPResponseException, LastPageException {

        products.updateList();

        assert(products.size() == 30);
        assert(products.getCurrentPage() == 1);
    }

    @Test
    public void getPreviousPage() throws BadHTTPResponseException, FirstPageException {

        products.setCurrentPage(4);

        products.getPreviousPage();

        assert(products.size() == 30);
        assert(products.getCurrentPage() == 3);
    }

    @Test
    public void getNextPage() throws BadHTTPResponseException, LastPageException {

        products.getNextPage();

        assert(products.size() == 30);
        assert(products.getCurrentPage() == 2);
    }
}
