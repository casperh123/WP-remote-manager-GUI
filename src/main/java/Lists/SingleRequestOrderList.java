package Lists;

import Components.Order.Order;
import Exceptions.BadHTTPResponseException;
import REST.RESTConnection;
import REST.RESTEndpoints;

import java.util.*;
import java.util.concurrent.CompletableFuture;

public class SingleRequestOrderList extends SingleRequestList<Order> {

    private final Map<StatusFilter, Integer> statusCounts = new HashMap<>();

    public SingleRequestOrderList(RESTConnection connection, String restEndpoint) throws BadHTTPResponseException {
        super(connection, restEndpoint, Order.class);
        initStatusCounts();
    }

    private void initStatusCounts() throws BadHTTPResponseException {
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        for (StatusFilter filter : getStatuses()) {
            CompletableFuture<Void> future = getElementsAmount(getQueryParamForStatus(filter))
                    .thenAccept(headers -> {
                        int count = Integer.parseInt(
                                headers.getOrDefault("x-wp-total", Collections.singletonList("0")).get(0)
                        );
                        synchronized (statusCounts) { // Ensure thread safety
                            statusCounts.put(filter, count);
                        }
                    });
            futures.add(future);
        }

        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join(); // This will wait for all futures to complete, but won't block each individual future.
    }

    private String getQueryParamForStatus(StatusFilter status) {
        switch (status) {
            case PENDING:
                return "status=pending";
            case PROCESSING:
                return "status=processing";
            case ON_HOLD:
                return "status=on-hold";
            case COMPLETED:
                return "status=completed";
            case CANCELLED:
                return "status=cancelled";
            case REFUNDED:
                return "status=refunded";
            case FAILED:
                return "status=failed";
            case TRASH:
                return "status=trash";
            case ALL:
            default:
                return "";
        }
    }

    @Override
    public void filterByStatus(StatusFilter filter) throws BadHTTPResponseException {
        updateList(getQueryParamForStatus(filter));
    }

    @Override
    public StatusFilter[] getStatuses() {

        StatusFilter[] filters = new StatusFilter[9];

        filters[0] = StatusFilter.ALL;
        filters[1] = StatusFilter.PENDING;
        filters[2] = StatusFilter.PROCESSING;
        filters[3] = StatusFilter.ON_HOLD;
        filters[4] = StatusFilter.COMPLETED;
        filters[5] = StatusFilter.CANCELLED;
        filters[6] = StatusFilter.REFUNDED;
        filters[7] = StatusFilter.FAILED;
        filters[8] = StatusFilter.TRASH;

        return filters;
    }

    @Override
    public int elementsWithStatus(StatusFilter status) {
        return statusCounts.getOrDefault(status, 0);
    }

    public CompletableFuture<Map<String, List<String>>> getElementsAmount(String parameter) throws BadHTTPResponseException {
        return CompletableFuture.supplyAsync(() -> {
            try {
                Map<String, List<String>> headers = connection.GETRequestHeaders(RESTEndpoints.getOrdersEndpoint(), parameter).toMultimap();
                System.out.println("Fetched for parameter: " + parameter + ", Result: " + headers);
                return headers;
            } catch (BadHTTPResponseException e) {
                return new HashMap<>();
            }
        });
    }

}

