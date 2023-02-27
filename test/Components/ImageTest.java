package Components;

import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ImageTest {

    @Test
    public void validImageObjectFromJson() {
        Image testImage = JsonIterator.deserialize("{\"id\":53302,\"date_created\":\"2021-10-15T12:50:27\",\"date_created_gmt\":\"2021-10-15T08:50:27\",\"date_modified\":\"2021-10-15T12:50:30\",\"date_modified_gmt\":\"2021-10-15T08:50:30\",\"src\":\"https:\\/\\/skadedyrsexperten.dk\\/wp-content\\/uploads\\/2018\\/12\\/Mottlock\\u00ae-Moelfaelde-Refill-2-stk.-i-pakke-1.jpg\",\"name\":\"Mottlock\\u00ae M\\u00f8lf\\u00e6lde Refill 2 stk. i pakke\",\"alt\":\"Mottlock\\u00ae M\\u00f8lf\\u00e6lde Refill 2 stk. i pakke\"}",
                                                Image.class);

        assert(testImage.getId() == 53302);
        assert(testImage.getAlt().equals("Mottlock® Mølfælde Refill 2 stk. i pakke"));
        assert(testImage.getName().equals("Mottlock® Mølfælde Refill 2 stk. i pakke"));
        assert(testImage.getSrc().equals("https://skadedyrsexperten.dk/wp-content/uploads/2018/12/Mottlock®-Moelfaelde-Refill-2-stk.-i-pakke-1.jpg"));
        assert(testImage.getDateCreated().equals("2021-10-15T12:50:27"));
        assert(testImage.getDateCreatedGmt().equals("2021-10-15T08:50:27"));
        assert(testImage.getDateModified().equals("2021-10-15T12:50:30"));
        assert(testImage.getDateModifiedGmt().equals("2021-10-15T08:50:30"));
    }
}