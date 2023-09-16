package Lists;

import Components.Order.Order;
import Exceptions.BadHTTPResponseException;
import REST.Parameter;
import REST.RESTConnection;
import REST.RESTEndpoints;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class SingleRequestOrderList extends SingleRequestList<Order> {

    private final Map<StatusFilter, Integer> statusCounts = new HashMap<>();

    public SingleRequestOrderList(RESTConnection connection, String restEndpoint) throws BadHTTPResponseException {
        super(connection, restEndpoint, Order.class);
        initializeStatusCounts();
    }

    private void initializeStatusCounts() throws BadHTTPResponseException {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (StatusFilter filter : StatusFilter.values()) {
            futures.add(fetchAndStoreStatusCount(filter));
        }

        CompletableFuture<Void> combinedFuture = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        combinedFuture.join();
    }

    private CompletableFuture<Void> fetchAndStoreStatusCount(StatusFilter filter) {
        return fetchElementsCountForStatus(filter)
                .thenAccept(count -> {
                    synchronized (statusCounts) {
                        statusCounts.put(filter, count);
                    }
                });
    }

    private CompletableFuture<Integer> fetchElementsCountForStatus(StatusFilter status) {
        Parameter parameter = getQueryParamForStatus(status);

        return getElementsAmount(parameter)
                .thenApply(headers -> Integer.parseInt(headers.getOrDefault("x-wp-total", Collections.singletonList("0")).get(0)));
    }

    private Parameter getQueryParamForStatus(StatusFilter status) {
        return switch (status) {
            case PENDING -> new Parameter("status", "pending");
            case PROCESSING -> new Parameter("status", "processing");
            case ON_HOLD -> new Parameter("status", "on-hold");
            case COMPLETED -> new Parameter("status", "completed");
            case CANCELLED -> new Parameter("status", "cancelled");
            case REFUNDED -> new Parameter("status", "refunded");
            case FAILED -> new Parameter("status", "failed");
            case TRASH -> new Parameter("status", "trash");
            default -> new Parameter("", ""); // TODO "=" in request doesnt break it, but not very nice
        };
    }

    @Override
    public void filterByStatus(StatusFilter filter) throws BadHTTPResponseException {
        updateList(getQueryParamForStatus(filter));
    }

    @Override
    public StatusFilter[] getStatuses() {
        return StatusFilter.values();
    }

    @Override
    public int elementsWithStatus(StatusFilter status) {
        return statusCounts.getOrDefault(status, 0);
    }

    public CompletableFuture<Map<String, List<String>>> getElementsAmount(Parameter parameter) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return connection.GETRequestHeaders(RESTEndpoints.getOrdersEndpoint(), parameter).toMultimap();
            } catch (BadHTTPResponseException e) {
                return new HashMap<>();
            }
        });
    }
}
