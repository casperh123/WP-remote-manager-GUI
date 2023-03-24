package REST;

import Exceptions.BadHTTPResponseException;
import Utility.HTTPClient;
import Website.User;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

public class RESTConnection implements Serializable {

    private String websiteRootUrl;
    private String authentication;
    private Headers latestHeaders;

    public RESTConnection(String websiteRootUrl, User user) {
        this.websiteRootUrl = websiteRootUrl;
        this.authentication = "consumer_key=" + user.getApiKey() + "&consumer_secret=" + user.getApiSecret();
    }

    public byte[] GETRequest(String endpoint, String parameters) throws BadHTTPResponseException {

        OkHttpClient client = HTTPClient.getHTTPClient();

        Request request = new Request.Builder()
                .url(websiteRootUrl + endpoint + authentication + parameters)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new BadHTTPResponseException(response.code());
            this.latestHeaders = response.headers();
            return response.body().bytes();
        } catch (IOException e) {
            throw new BadHTTPResponseException(e.getMessage());
        }
    }

    public List<byte[]> GETRequest(String endpoint, List<String> parameters) throws BadHTTPResponseException {

        OkHttpClient client = HTTPClient.getHTTPClient();
        List<byte[]> responseBodies = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(parameters.size());

        for(String parameter : parameters) {
            client.newCall(new Request.Builder()
                    .url(websiteRootUrl + endpoint + authentication + parameter)
                    .build())
                    .enqueue(new Callback() {
                public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                    if(!response.isSuccessful()) throw new BadHTTPResponseException(response.code());
                    try (ResponseBody responseBody = response.body()) {
                        responseBodies.add(responseBody.bytes());
                    }
                    countDownLatch.countDown();
                }

                @Override
                public void onFailure(@NotNull Call call, @NotNull IOException e) {
                    System.out.println("Fuck");
                }
            });
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            throw new BadHTTPResponseException(e.getMessage());
        }
        return responseBodies;
    }

    public Headers GETRequestHead(String endpoint, String parameters) throws BadHTTPResponseException {

        OkHttpClient client = HTTPClient.getHTTPClient();
        Request request = new Request.Builder()
                .url(websiteRootUrl + endpoint + authentication + parameters)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new BadHTTPResponseException(response.code());
            this.latestHeaders = response.headers();
            return response.headers();
        } catch (IOException e) {
            throw new BadHTTPResponseException(e.getMessage());
        }
    }

    public Headers getLatestHeaders() {
        return latestHeaders;
    }

}