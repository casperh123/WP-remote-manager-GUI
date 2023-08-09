package Lists;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import REST.RESTConnection;

import java.util.ArrayList;
import java.util.List;

public class CompleteProductList extends CompleteComponentList<Product> {

    private List<Product> unalteredList;
    private int elementsPublished;
    private int elementsPrivate;
    private int elementsDraft;
    private int elementsPending;

    public CompleteProductList(RESTConnection connection, String restEndpoint) throws BadHTTPResponseException {
        super(connection, restEndpoint, Product.class);
        unalteredList = null;

        for(Product product : internalList) {
            switch(product.getStatus()) {
                case "publish" -> {
                    elementsPublished++;
                }
                case "private" -> {
                    elementsPrivate++;
                }
                case "draft" -> {
                    elementsDraft++;
                }
                case "pending" -> {
                    elementsPending++;
                }
            }
        }
    }

    @Override
    public void filterByStatus(StatusFilter filter) throws BadHTTPResponseException {
        switch(filter) {
            case ALL -> {
                resetFilter();
            }
            case PUBLISHED -> {
                saveInternalList();
                internalList = getListByStatus("publish");
                updateListMetrics();
            }
            case PRIVATE -> {
                saveInternalList();
                internalList = getListByStatus("private");
                updateListMetrics();
            }
            case DRAFT -> {
                saveInternalList();
                internalList = getListByStatus("draft");
                updateListMetrics();
            }
            case PENDING -> {
                saveInternalList();
                internalList = getListByStatus("pending");
                updateListMetrics();
            }
        }
    }

    public void updateListMetrics() {
        totalItems = internalList.size();
        totalPages = (int) Math.ceil(totalItems / (perPage + 0.0));
        currentPage = 1;
    }

    public void saveInternalList() {
        resetFilter();
        unalteredList = internalList;
    }

    private void resetFilter() {
        if(!(unalteredList == null)) {
            internalList = unalteredList;
            unalteredList = null;
        }
    }

    private List<Product> getListByStatus(String status) {

        List<Product> matchingProducts = new ArrayList<>();

        for(Product product : internalList) {
            if(product.getStatus().equals(status)) {
                matchingProducts.add(product);
            }
        }

        return matchingProducts;
    }

    public StatusFilter[] getStatuses() {

        StatusFilter[] statuses = new StatusFilter[5];

        statuses[0] = StatusFilter.ALL;
        statuses[1] = StatusFilter.PUBLISHED;
        statuses[2] = StatusFilter.PRIVATE;
        statuses[3] = StatusFilter.DRAFT;
        statuses[4] = StatusFilter.PENDING;

        return statuses;
    }

    @Override
    public int elementsWithStatus(StatusFilter status) {
        switch(status) {
            case ALL -> {
                return internalList.size();
            }
            case PUBLISHED -> {
                return elementsPublished;
            }
            case PRIVATE -> {
                return elementsPrivate;
            }
            case PENDING -> {
                return elementsPending;
            }
            case DRAFT -> {
                return elementsDraft;
            }
            default -> {
                return 0;
            }
        }
    }
}
