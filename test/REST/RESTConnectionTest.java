package REST;

import Exceptions.BadHTTPResponseException;
import Website.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.fail;

class RESTConnectionTest {

    private RESTConnection connection;

    @BeforeEach
    void setUp() {
        connection = new RESTConnection("https://staging-skadedyrsexpertendk-test.kinsta.cloud/",
                new User("Casper",
                        "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558",
                        "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357"));
    }

    @AfterEach
    void tearDown() {
        connection = null;
    }

    @Test
    void singleGETRequestTest() {

        byte[] connectionResponse;

        try {
            connectionResponse = connection.GETRequest(RESTEndpoints.getProductsEndpoint(), "&per_page=100");
        } catch (IOException e) {
            fail(e.getMessage());
            connectionResponse = new byte[0];
        }
        assert (connectionResponse.length != 0);
    }


    @Test
    void listAsyncGETRequestTest() {

        int pages = 7;
        List<String> parameters = new ArrayList<>();
        List<byte[]> productStream = null;

        for (int i = 1; i < pages; i++) {
            parameters.add("&per_page=100&page=" + i + "&orderby=id");
        }

        try {
            productStream = connection.GETRequest(RESTEndpoints.getProductsEndpoint(), parameters);
        } catch (BadHTTPResponseException e) {
            fail(e.getMessage());
        }

        assert(productStream.size() == 6);
    }
}