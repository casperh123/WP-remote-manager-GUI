package Lists;

import Components.Order.Order;
import Lists.StatusFilter.StatusFilter;
import REST.Parameter;
import REST.RESTConnection;
import REST.RESTEndpoints;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

public class SingleRequestOrderList extends SingleRequestList<Order> {

    private final Map<StatusFilter, Integer> statusCounts = new ConcurrentHashMap<>();

    public SingleRequestOrderList(RESTConnection connection, String restEndpoint) throws IOException {
        super(connection, restEndpoint, Order.class);
        initializeStatusCounts();
    }

    private void initializeStatusCounts() {
        Arrays.stream(getStatuses())
                .map(this::fetchAndStoreStatusCount)
                .toList() // Collecting to list to ensure all tasks are executed
                .forEach(CompletableFuture::join); // Joining to ensure all tasks are completed
    }

    private CompletableFuture<Void> fetchAndStoreStatusCount(StatusFilter filter) {
        Parameter parameter = getQueryParamForStatus(filter);

        return CompletableFuture.supplyAsync(() -> connection.GETRequestHeaders(RESTEndpoints.getOrdersEndpoint(), parameter).toMultimap())
                .thenApply(headers -> Integer.parseInt(headers.getOrDefault("x-wp-total", Collections.singletonList("0")).get(0)))
                .thenAccept(count -> statusCounts.put(filter, count));
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
    public void filterByStatus(StatusFilter filter) throws IOException {
        updateList(getQueryParamForStatus(filter));
    }

    @Override
    public StatusFilter[] getStatuses() {
        return new StatusFilter[] {
                StatusFilter.ALL,
                StatusFilter.PENDING,
                StatusFilter.PROCESSING,
                StatusFilter.ON_HOLD,
                StatusFilter.COMPLETED,
                StatusFilter.CANCELLED,
                StatusFilter.REFUNDED,
                StatusFilter.FAILED,
                StatusFilter.TRASH
        };
    }

    @Override
    public int elementsWithStatus(StatusFilter status) {
        return statusCounts.getOrDefault(status, 0);
    }
}
