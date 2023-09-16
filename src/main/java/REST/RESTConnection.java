package REST;

import Exceptions.BadHTTPResponseException;
import Utility.HTTPClient;
import Website.APICredentials;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

public class RESTConnection implements Serializable {

    private OkHttpClient httpClient;
    private String websiteRootUrl;
    private String credentials;

    public RESTConnection(String websiteRootUrl, APICredentials user) {
        this.websiteRootUrl = websiteRootUrl;
        this.credentials = Credentials.basic(user.getApiKey(), user.getApiSecret());
        this.httpClient = HTTPClient.getHTTPClient();
    }

    public byte[] GETRequest(String endpoint, Parameter... parameters) throws BadHTTPResponseException {
        try (Response response = executeRequest(buildRequest(endpoint, parameters))) {
            if (!response.isSuccessful()) {
                throw new BadHTTPResponseException(response.code());
            }
            return response.body().bytes();
        } catch (IOException e) {
            throw new RuntimeException(e); // Consider a custom exception here
        }
    }

    public List<byte[]> GETRequest(String endpoint, int pages, Parameter... parameters) throws BadHTTPResponseException {

        List<CompletableFuture<byte[]>> futures = new ArrayList<>();

        for (int i = 1; i <= pages; i++) {
            List<Parameter> paginationAddedParameters = new ArrayList<>(Arrays.asList(parameters));
            paginationAddedParameters.add(new Parameter("page", Integer.toString(i)));
            futures.add(sendAsyncRequest(buildRequest(endpoint, paginationAddedParameters)));
        }

        System.out.println("Done sending requests");

        return futures.parallelStream()
                .map(future -> {
                    try {
                        return future.get(); // Waits for this future to complete
                    } catch (Exception e) {
                        throw new RuntimeException(e); // or handle exception
                    }
                })
                .collect(Collectors.toList());
    }

    private CompletableFuture<byte[]> sendAsyncRequest(Request request) {
        CompletableFuture<byte[]> future = new CompletableFuture<>();
        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    future.completeExceptionally(new BadHTTPResponseException(response.code()));
                } else {
                    // Read the bytes from the response before it's closed
                    byte[] responseBodyBytes = response.body().bytes();
                    response.close(); // Close the response
                    future.complete(responseBodyBytes);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    public Headers GETRequestHeaders(String endpoint, Parameter... parameters) throws BadHTTPResponseException {
        try (Response response = executeRequest(buildRequest(endpoint, parameters))) {
            return response.headers();
        }
    }

    private Response executeRequest(Request request) throws BadHTTPResponseException {

        long start = System.nanoTime();

        Response response;

        try {
            response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) throw new BadHTTPResponseException(response.code());
            System.out.println("Request timing: " + ((System.nanoTime() - start) / 1000000) + " ms, URL:" + request.url());
            return response;
        } catch (IOException e) {
            throw new BadHTTPResponseException(e.getMessage());
        }

    }

    private Request buildRequest(String endpoint, List<Parameter> parameters) {

        HttpUrl.Builder urlBuilder = HttpUrl.parse(websiteRootUrl)
                .newBuilder()
                .addPathSegments(endpoint);

        parameters.forEach(parameter -> urlBuilder.addQueryParameter(parameter.getParameter(), parameter.getValue()));

        HttpUrl url = urlBuilder.build();

        return new Request.Builder()
                .url(url)
                .header("Authorization", credentials)
                .build();
    }

    private Request buildRequest(String endpoint, Parameter... parameters) {
        return buildRequest(endpoint, Arrays.asList(parameters));
    }
}