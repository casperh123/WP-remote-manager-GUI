package Utility;

import okhttp3.OkHttpClient;

public class HTTPClient {

    private final static OkHttpClient HTTP_CLIENT = new OkHttpClient();

    public static OkHttpClient getHTTPClient() {
        return HTTP_CLIENT;
    }
}
