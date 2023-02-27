package Website;

public class User {

    private String name;
    private String apiKey;
    private String apiSecret;

    public User(String name, String apiKey, String  apiSecret) {
        this.name = name;
        this.apiKey = apiKey;
        this.apiSecret = apiSecret;
    }

    public String getName() {
        return name;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getApiSecret() {
        return apiSecret;
    }



}
