package Lists;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.ElementNotInArrayException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.RESTConnection;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SingleRequestList<E extends ID> extends QueryList<E> {

    private static final int MAX_ELEMENTS_PER_REQUEST = 100;
    private List<E> primerList = null;
    private int perPage;
    private int currentPage;

    public SingleRequestList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws BadHTTPResponseException {
        super(connection, restEndpoint, containedClass);
        this.currentPage = 1;
        this.perPage = 100;

        updateList();

        Map<String, List<String>> httpHeaders = connection.getLatestHeaders().toMultimap();
        System.out.println(httpHeaders);

        if(httpHeaders.containsKey("x-wp-total")) {
            this.totalItems = Integer.parseInt(httpHeaders.get("x-wp-total").get(0));
        } else {
            this.totalItems = 0;
        }

        if (httpHeaders.containsKey("x-wp-totalpages")) {
            this.totalPages = Integer.parseInt(httpHeaders.get("x-wp-totalpages").get(0));
        } else {
            this.totalPages = 0;
        }
    }

    protected void updateList() throws BadHTTPResponseException {
        updateList("");
    }

    protected void updateList(String parameter) throws BadHTTPResponseException {

        this.clear();

        byte[] getResponse = connection.GETRequest(restEndpoint,  parameter + "&per_page= + " + perPage + "&page=" + currentPage);

        Any json = JsonIterator.deserialize(getResponse);

        json.forEach(item -> this.add(item.as(containedClass)));
    }

    public void nextPage() throws LastPageException, BadHTTPResponseException {
        if(currentPage == totalPages) {
            throw new LastPageException("Cannot display next page: Already on the last page");
        } else {
            currentPage++;
            updateList();
        }
    }

    public void previousPage() throws FirstPageException, BadHTTPResponseException {
        if(currentPage == 1) {
            throw new FirstPageException("Cannot display previous page: Already on the first page");
        } else {
            currentPage--;
            updateList();
        }
    }

    @Override
    public void refresh() throws BadHTTPResponseException {
        updateList();
    }

    public void setPage(int page) throws BadHTTPResponseException {
        currentPage = page;
        updateList();
    }

    @Override
    public void setPerPage(int perPage) {
        if(perPage <= maxPerPage()) {
            this.perPage = perPage;
        }
    }

    @Override
    public int maxPerPage() {
        return MAX_ELEMENTS_PER_REQUEST;
    }

    @Override
    public void filterByStatus(StatusFilter filter) throws BadHTTPResponseException {

    }

    @Override
    public StatusFilter[] getStatuses() {
        return new StatusFilter[0];
    }

    @Override
    public int elementsWithStatus(StatusFilter status){
        return 0;
    }

    public void primeUpdatedList(int page) throws BadHTTPResponseException {

        primerList = new ArrayList<>();

        byte[] getResponse = connection.GETRequest(restEndpoint, "&per_page=100&page=" + page);

        Any json = JsonIterator.deserialize(getResponse);

        json.forEach(item -> primerList.add(item.as(containedClass)));

        System.out.println("Primed");
    }

    public boolean swapUpdatedList() {
        if(primerList != null) {
            internalList = primerList;
        }
        return false;
    }

    public void loadIds(List<Integer> ids) throws BadHTTPResponseException {

        List<E> newElements = new ArrayList<>();

        StringBuilder parameters = new StringBuilder().append("include=[");

        for(int i = 0; i < ids.size(); i++) {
            if(i == ids.size()-1) {
                parameters.append(ids.get(i));
                parameters.append("]");
            } else {
                parameters.append(ids.get(i));
                parameters.append(',');
            }
        }

        byte[] getResponse = connection.GETRequest(restEndpoint, parameters.toString());

        Any json = JsonIterator.deserialize(getResponse);

        json.forEach(item -> newElements.add(item.as(containedClass)));

        internalList = newElements;
    }

    public void loadElementById(String endpoint, int id) throws BadHTTPResponseException {

        List<E> newElements = new ArrayList<>();

        byte[] getResponse = connection.GETRequest(endpoint, "");

        Any json = JsonIterator.deserialize(getResponse);

        json.forEach(item -> newElements.add(item.as(containedClass)));

        internalList = newElements;
    }

    public ID getElementById(int id) throws ElementNotInArrayException {

        for(E element : this) {
            if(element.getId() == id) {
                return element;
            }
        }
        throw new ElementNotInArrayException(String.valueOf(id));
    }

    public int getPage() {
        return currentPage;
    }

    @Override
    public int getPages() {
        return totalPages;
    }

    public void setCurrentPage(int page) {
        this.currentPage = page;
    }

}
