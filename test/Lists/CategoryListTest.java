package Lists;

import Components.Category.Category;
import Exceptions.BadHTTPResponseException;
import REST.RESTConnection;
import REST.RESTEndpoints;
import Website.APICredentials;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

public class CategoryListTest {
    private QueryList<Category> categories;

    @BeforeEach
    public void setUp() throws BadHTTPResponseException, URISyntaxException {

        long startTime = System.nanoTime();

        categories = new CompleteComponentList<Category>(
                new RESTConnection("https://staging-skadedyrsexpertendk-test.kinsta.cloud/",
                        new APICredentials("Casper",
                                "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558",
                                "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357")), RESTEndpoints.getProductCategoriesEndpoint(),
                Category.class);

        long endTime = System.nanoTime();

        System.out.println((endTime-startTime)/1000000);
    }

    @AfterEach
    public void tearDown() {
        categories = null;
    }

    @Test
    public void updateListTest() {
        for(Category category : categories) {
            System.out.println(category);
        }
        System.out.println(categories.size());
        assert(categories.size() != 0);
    }
}
