package REST;

import Website.APICredentials;
import okhttp3.Credentials;
import okhttp3.HttpUrl;
import okhttp3.Request;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class RequestBuilder {

    private final String websiteRootUrl;
    private final String credentials;
    private Map<String, String> headers = new HashMap<>();

    public RequestBuilder(String websiteRootUrl, APICredentials user) {
        this.websiteRootUrl = websiteRootUrl;
        this.credentials = Credentials.basic(user.getApiKey(), user.getApiSecret());
        headers.put("Authorization", credentials);
    }

    public Request buildRequest(String endpoint, List<Parameter> parameters) {
        HttpUrl url = buildUrl(endpoint, parameters);

        Request.Builder requestBuilder = new Request.Builder().url(url);
        headers.forEach(requestBuilder::header);

        return requestBuilder.build();
    }

    public Request buildRequest(String endpoint, Parameter... parameters) {
        return buildRequest(endpoint, Arrays.asList(parameters));
    }

    private HttpUrl buildUrl(String endpoint, List<Parameter> parameters) {
        HttpUrl.Builder urlBuilder = HttpUrl.parse(websiteRootUrl)
                .newBuilder()
                .addPathSegments(endpoint);

        if (urlBuilder == null) {
            throw new IllegalArgumentException("Invalid websiteRootUrl: " + websiteRootUrl);
        }

        parameters.forEach(parameter -> urlBuilder.addQueryParameter(parameter.getParameter(), parameter.getValue()));

        return urlBuilder.build();
    }

    public void addHeader(String key, String value) {
        headers.put(key, value);
    }

    public void removeHeader(String key) {
        headers.remove(key);
    }
}
