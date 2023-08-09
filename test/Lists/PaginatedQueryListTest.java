package Lists;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.RESTConnection;
import REST.RESTEndpoints;
import Website.APICredentials;
import org.junit.jupiter.api.Test;

public class PaginatedQueryListTest {

    SingleRequestList<Product> products = new SingleRequestList<>(
            new RESTConnection("https://staging-skadedyrsexpertendk-test.kinsta.cloud/",
                    new APICredentials("Casper",
                            "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558",
                            "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357")), RESTEndpoints.getProductsEndpoint(),
            Product.class);

    public PaginatedQueryListTest() throws BadHTTPResponseException {
    }


    @Test
    public void updateListCurrentPage() throws BadHTTPResponseException, LastPageException {

        products.updateList();

        assert(products.size() == 100);
        assert(products.getPage() == 1);
    }

    @Test
    public void getPreviousPage() throws BadHTTPResponseException, FirstPageException {

        products.setCurrentPage(4);

        products.previousPage();

        assert(products.size() == 100);
        assert(products.getPage() == 3);
    }

    @Test
    public void getNextPage() throws BadHTTPResponseException, LastPageException {

        products.nextPage();

        assert(products.size() == 100);
        assert(products.getPage() == 2);
    }
}
