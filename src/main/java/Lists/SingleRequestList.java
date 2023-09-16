package Lists;

import Components.Interfaces.ID;
import Exceptions.ElementNotInArrayException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.Parameter;
import REST.RESTConnection;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SingleRequestList<E extends ID> extends QueryList<E> {

    private static final int MAX_ELEMENTS_PER_REQUEST = 100;
    private List<E> primerList = null;
    private int perPage;
    private int currentPage;

    public SingleRequestList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws IOException {
        super(connection, restEndpoint, containedClass);
        this.currentPage = 1;
        this.perPage = 100;

        updateList();
    }

    protected void updateList() throws IOException {
        updateList(null);
    }

    protected void updateList(Parameter parameter) throws IOException {

        this.clear();

        List<Parameter> parameters = new ArrayList<>(Arrays.asList(
                new Parameter("per_page", Integer.toString(perPage)),
                new Parameter("page", Integer.toString(currentPage))
        ));

        if(parameter != null) {
            parameters.add(parameter);
        }

        Any json = JsonIterator.deserialize(connection.GETRequest(restEndpoint, parameters.toArray(new Parameter[0])));

        json.forEach(item -> this.add(item.as(containedClass)));
    }

    public void nextPage() throws LastPageException, IOException {
        if(currentPage == totalPages) {
            throw new LastPageException("Cannot display next page: Already on the last page");
        } else {
            currentPage++;
            updateList();
        }
    }

    public void previousPage() throws FirstPageException, IOException {
        if(currentPage == 1) {
            throw new FirstPageException("Cannot display previous page: Already on the first page");
        } else {
            currentPage--;
            updateList();
        }
    }

    @Override
    public void refresh() throws IOException {
        updateList();
    }

    public void setPage(int page) throws IOException {
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
    public void filterByStatus(StatusFilter filter) throws IOException {

    }

    @Override
    public StatusFilter[] getStatuses() {
        return new StatusFilter[0];
    }

    @Override
    public int elementsWithStatus(StatusFilter status){
        return 0;
    }

    /*
    public void primeUpdatedList(int page) throws BadHTTPResponseException {

        primerList = new ArrayList<>();

        byte[] getResponse = connection.GETRequest(restEndpoint, "&per_page=100&page=" + page);

        Any json = JsonIterator.deserialize(getResponse);

        json.forEach(item -> primerList.add(item.as(containedClass)));

        System.out.println("Primed");
    }*/

    public boolean swapUpdatedList() {
        if(primerList != null) {
            internalList = primerList;
        }
        return false;
    }

    /*
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
    }*/

    /*

    public void loadElementById(String endpoint, int id) throws BadHTTPResponseException {

        List<E> newElements = new ArrayList<>();

        byte[] getResponse = connection.GETRequest(endpoint, "");

        Any json = JsonIterator.deserialize(getResponse);

        json.forEach(item -> newElements.add(item.as(containedClass)));

        internalList = newElements;
    }*/

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
