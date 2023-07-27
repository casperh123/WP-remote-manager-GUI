package Website;

import java.io.Serializable;
import java.net.URISyntaxException;

public class APICredentials implements Serializable {

    private String name;
    private String apiKey;
    private String apiSecret;
    private String stringUrl;
    private String websiteName;


    public APICredentials(String apiKey, String  apiSecret, String url, String websiteName) throws URISyntaxException {
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
        this.websiteName = websiteName;

        if(url.matches("^(https|http)://[a-zA-Z]+(.[a-zA-Z]+)+$")) {
            this.stringUrl = url + "/";
        } else {
            throw new URISyntaxException(url, "URL has to start with http:// or https:// and end with .xxx");
        }
    }

    public String getName() {
        return websiteName;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }

    public String getUrl() { return stringUrl; };

}
