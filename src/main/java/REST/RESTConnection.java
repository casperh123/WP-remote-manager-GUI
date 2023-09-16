package REST;

import Exceptions.BadHTTPResponseException;
import Utility.HTTPClient;
import Website.APICredentials;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
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

    public byte[] GETRequest(String endpoint) throws BadHTTPResponseException {
        return GETRequest(endpoint, new ArrayList<>());
    }

    public byte[] GETRequest(String endpoint, Parameter parameter) throws BadHTTPResponseException {
        return GETRequest(endpoint, Collections.singletonList(parameter));
    }

    public byte[] GETRequest(String endpoint, List<Parameter> parameters) throws BadHTTPResponseException {

        long start = System.nanoTime();

        OkHttpClient client = HTTPClient.getHTTPClient();

        //TODO lol exception much
        try (Response response = executeRequest(buildRequest(endpoint, parameters))) {
            if(!response.isSuccessful()) { throw new BadHTTPResponseException(response.code()); }
            return response.body().bytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<byte[]> GETRequest(String endpoint, List<Parameter> parameters, int pages) throws BadHTTPResponseException {

        OkHttpClient client = HTTPClient.getHTTPClient();
        List<CompletableFuture<byte[]>> futures = new ArrayList<>();

        for (int i = 1; i <= pages; i++) {

            CompletableFuture<byte[]> future = new CompletableFuture<>();

            List<Parameter> paginationAddedParameters = new ArrayList<>(parameters);

            paginationAddedParameters.add(new Parameter("page", Integer.toString(i)));

            client.newCall(buildRequest(endpoint, paginationAddedParameters))
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

    public Headers GETRequestHeaders(String endpoint, Parameter parameters) throws BadHTTPResponseException {
        return executeRequest(buildRequest(endpoint, parameters)).headers();
    }

    public Headers GETRequestHeaders(String endpoint) throws BadHTTPResponseException {
        return executeRequest(buildRequest(endpoint)).headers();
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

    private Request buildRequest(String endpoint, Parameter parameter) {
        return buildRequest(endpoint, Collections.singletonList(parameter));
    }

    private Request buildRequest(String endpoint) {
        return buildRequest(endpoint, new ArrayList<>());
    }
}