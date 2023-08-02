package Lists;

import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import REST.RESTConnection;

import java.util.ArrayList;
import java.util.List;

public class UnpaginatedProductList extends UnpaginatedQueryList<Product> {

    private List<Product> unalteredList;

    public UnpaginatedProductList(RESTConnection connection, String restEndpoint) throws BadHTTPResponseException {
        super(connection, restEndpoint, Product.class);
        unalteredList = null;
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
            }
            case PRIVATE -> {
                saveInternalList();
                internalList = getListByStatus("private");
            }
            case DRAFT -> {
                saveInternalList();
                internalList = getListByStatus("draft");
            }
            case PENDING -> {
                saveInternalList();
                internalList = getListByStatus("pending");
            }
        }
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
}
