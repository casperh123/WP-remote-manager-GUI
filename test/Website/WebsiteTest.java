package Website;

import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class WebsiteTest {
    @Test
    public void websiteTest() throws BadHTTPResponseException, URISyntaxException, FetchException {
        APICredentials casper = new APICredentials("Casper", "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558", "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357");
        Website skadedyrsexperten = new Website("Skadedyrsexperten", "https://staging-skadedyrsexpertendk-test.kinsta.cloud", casper);

        assert(skadedyrsexperten.getPaginatedProducts().size() > 0);
    }

    @Test
    public void websiteThrowsURISyntaxException() {
        APICredentials casper = new APICredentials("Casper", "ck_1a62e360c9cfdfe4d4438f35155c6816e872b558", "cs_ac785b31f21fe1835e2dd6adb3e0c6a474d56357");
        assertThrows(URISyntaxException.class, () -> new Website("Skadedyrsexperten", "asldfjasdf", casper));
    }
}
