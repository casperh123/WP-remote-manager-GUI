package REST;

import Exceptions.BadHTTPResponseException;
import Utility.HTTPClient;
import okhttp3.*;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

public class RESTConnection {

    private final RequestBuilder requestBuilder;
    private final OkHttpClient httpClient;
    private static final Logger logger = Logger.getLogger(RESTConnection.class.getName());

    public RESTConnection(RequestBuilder requestBuilder) {
        this.httpClient = HTTPClient.getHTTPClient();
        this.requestBuilder = requestBuilder;
    }

    public byte[] GETRequest(String endpoint, Parameter... parameters) throws BadHTTPResponseException {
        try (Response response = executeRequest(requestBuilder.buildRequest(endpoint, parameters))) {
            if (!response.isSuccessful()) {
                logger.warning("Failed GET request on endpoint: " + endpoint + " with parameters: " + Arrays.toString(parameters));
                throw new BadHTTPResponseException(response.code());
            }
            return response.body().bytes();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while processing GET request", e);
            throw new RuntimeException(e);
        }
    }

    public List<byte[]> GETRequest(String endpoint, int pages, Parameter... parameters) {
        long start = System.nanoTime(); // Begin benchmarking

        List<CompletableFuture<byte[]>> futures = new ArrayList<>();
        for (int i = 1; i <= pages; i++) {
            futures.add(sendAsyncRequestWithPagination(endpoint, i, parameters));
        }

        logger.info("Done sending requests for endpoint: " + endpoint + " with parameters: " + Arrays.toString(parameters));

        List<byte[]> results = futures.parallelStream()
                .map(future -> {
                    try {
                        return future.get(); // Waits for this future to complete
                    } catch (Exception e) {
                        logger.log(Level.SEVERE, "Error retrieving future result", e);
                        throw new RuntimeException("Error retrieving future result", e);
                    }
                })
                .collect(Collectors.toList());

        // End benchmarking and log the duration
        long duration = (System.nanoTime() - start) / 1000000;
        logger.info("Async request timing: " + pages + " pages from endpoint " + endpoint + ": " + duration + " ms");

        return results;
    }

    private CompletableFuture<byte[]> sendAsyncRequestWithPagination(String endpoint, int page, Parameter... parameters) {
        List<Parameter> paginationAddedParameters = new ArrayList<>(Arrays.asList(parameters));
        paginationAddedParameters.add(new Parameter("page", Integer.toString(page)));
        return sendAsyncRequest(requestBuilder.buildRequest(endpoint, paginationAddedParameters));
    }

    private CompletableFuture<byte[]> sendAsyncRequest(Request request) {

        CompletableFuture<byte[]> future = new CompletableFuture<>();

        httpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if (!response.isSuccessful()) {
                    logger.warning("Async request failed for URL: " + request.url());
                    future.completeExceptionally(new BadHTTPResponseException(response.code()));
                } else {
                    byte[] responseBodyBytes = response.body().bytes();
                    response.close(); // Close the response
                    future.complete(responseBodyBytes);
                }
            }

            @Override
            public void onFailure(Call call, IOException e) {
                logger.log(Level.SEVERE, "Async request failure", e);
                future.completeExceptionally(e);
            }
        });
        return future;
    }

    public Headers GETRequestHeaders(String endpoint, Parameter... parameters) {
        try (Response response = executeRequest(requestBuilder.buildRequest(endpoint, parameters))) {
            if (!response.isSuccessful()) {
                logger.warning("Failed GET request for headers on endpoint: " + endpoint + " with parameters: " + Arrays.toString(parameters));
                throw new BadHTTPResponseException(response.code());
            }
            return response.headers();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "IOException while processing GET request for headers", e);
            throw new RuntimeException(e);
        }
    }

    private Response executeRequest(Request request) throws BadHTTPResponseException {
        long start = System.nanoTime();
        try {
            Response response = httpClient.newCall(request).execute();
            if (!response.isSuccessful()) {
                logger.warning("Request failed for URL: " + request.url());
                throw new BadHTTPResponseException(response.code());
            }
            logger.info("Request timing: " + ((System.nanoTime() - start) / 1000000) + " ms, URL: " + request.url());
            return response;
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to execute request", e);
            throw new BadHTTPResponseException(e.getMessage());
        }
    }
}
