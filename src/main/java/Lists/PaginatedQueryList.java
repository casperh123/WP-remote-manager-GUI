package Lists;

import Components.AbstractComponent;
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

public class PaginatedQueryList<E extends ID> extends QueryList<E> {

    private List<E> primerList = null;
    private int perPage;
    private int currentPage;

    public PaginatedQueryList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws BadHTTPResponseException {
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

    public void updateList() throws BadHTTPResponseException {

        this.clear();

        byte[] getResponse = connection.GETRequest(restEndpoint, "&per_page= + " + perPage + "&page=" + currentPage);

        Any json = JsonIterator.deserialize(getResponse);

        json.forEach(item -> this.add(item.as(containedClass)));
    }

    public void getNextPage() throws LastPageException, BadHTTPResponseException {
        if(currentPage == totalPages) {
            throw new LastPageException("Cannot display next page: Already on the last page");
        } else {
            currentPage++;
            updateList();
        }
    }

    public void getPreviousPage() throws FirstPageException, BadHTTPResponseException {
        if(currentPage == 1) {
            throw new FirstPageException("Cannot display previous page: Already on the first page");
        } else {
            currentPage--;
            updateList();
        }
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

    public int getCurrentPage() {
        return currentPage;
    }

    @Override
    public int getPagesAmount() {
        return totalPages;
    }

    public void setCurrentPage(int page) {
        this.currentPage = page;
    }
}
