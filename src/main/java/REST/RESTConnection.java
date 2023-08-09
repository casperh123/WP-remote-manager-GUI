package REST;

import Exceptions.BadHTTPResponseException;
import Utility.HTTPClient;
import Website.APICredentials;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class RESTConnection implements Serializable {

    private String websiteRootUrl;
    private String credentials;
    private Headers latestHeaders;

    public RESTConnection(String websiteRootUrl, APICredentials user) {
        this.websiteRootUrl = websiteRootUrl;
        this.credentials = Credentials.basic(user.getApiKey(), user.getApiSecret());
    }

    public byte[] GETRequest(String endpoint, String parameters) throws BadHTTPResponseException {

        long start = System.nanoTime();

        OkHttpClient client = HTTPClient.getHTTPClient();

        Request request = new Request.Builder()
                .url(websiteRootUrl + endpoint + parameters)
                .header("Authorization", credentials)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if(!response.isSuccessful()) throw new BadHTTPResponseException(response.code());
            this.latestHeaders = response.headers();
            System.out.println("Request timing: " + ((System.nanoTime() - start) / 1000000) + " ms");
            return response.body().bytes();
        } catch (IOException e) {
            throw new BadHTTPResponseException(e.getMessage());
        }
    }

    public List<byte[]> GETRequest(String endpoint, List<String> parameters) throws BadHTTPResponseException {

        OkHttpClient client = HTTPClient.getHTTPClient();
        List<CompletableFuture<byte[]>> futures = new ArrayList<>();

        for (String parameter : parameters) {
            CompletableFuture<byte[]> future = new CompletableFuture<>();

            client.newCall(new Request.Builder()
                            .url(websiteRootUrl + endpoint + parameter)
                            .header("Authorization", credentials)
                            .build())
                    .enqueue(new Callback() {
                        public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                            if (!response.isSuccessful()) {
                                future.completeExceptionally(new BadHTTPResponseException(response.code()));
                                return;
                            }

                            try (ResponseBody responseBody = response.body()) {
                                future.complete(responseBody.bytes());
                            }
                        }

                        @Override
                        public void onFailure(Call call, IOException e) {
                            System.out.println("Request failed: " + e.getMessage());
                            future.completeExceptionally(e);
                        }
                    });

            futures.add(future);
        }

        return futures.stream()
                .map(future -> {
                    try {

                        return future.get(); // Waits for this future to complete
                    } catch (Exception e) {
                        throw new RuntimeException(e); // or handle exception
                    }
                })
                .collect(Collectors.toList());
    }

    public Headers GETRequestHeaders(String endpoint, String parameters) throws BadHTTPResponseException {

        OkHttpClient client = HTTPClient.getHTTPClient();
        Request request = new Request.Builder()
                .url(websiteRootUrl + endpoint + parameters)
                .header("Authorization", credentials)
                .build();

        System.out.println(request.url());

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