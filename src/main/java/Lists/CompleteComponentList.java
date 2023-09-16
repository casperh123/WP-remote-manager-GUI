package Lists;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.Parameter;
import REST.RESTConnection;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.util.*;

public class CompleteComponentList<E extends ID> extends QueryList<E> {

    protected int perPage;
    protected int currentPage;

    public CompleteComponentList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws BadHTTPResponseException {

        super(connection, restEndpoint, containedClass);

        this.perPage = 100;
        this.currentPage = 1;

        long start = System.nanoTime();

        Map<String, List<String>> httpHeaders = connection.GETRequestHeaders(restEndpoint,new Parameter("per_page", "1")).toMultimap();

        System.out.println("Header Request timing: " + (System.nanoTime() - start) / 1000000 + " ms");

        if(httpHeaders.containsKey("x-wp-total")) {
            this.totalItems = Integer.parseInt(httpHeaders.get("x-wp-total").get(0));
        } else {
            this.totalItems = 0;
        }

        if (httpHeaders.containsKey("x-wp-totalpages")) {
            this.totalPages = (int) Math.ceil(Double.parseDouble(httpHeaders.get("x-wp-total").get(0)) / 100.0);
        } else {
            this.totalPages = 0;
        }

        if(this.size() < totalItems) {
            updateList();
        } else {
            this.internalList = new ArrayList<>();
        }
    }

    public void nextPage() throws LastPageException, BadHTTPResponseException {
        if(currentPage == totalPages) {
            throw new LastPageException("Cannot display next page: Already on the last page");
        } else {
            currentPage++;
        }
    }

    public void previousPage() throws FirstPageException, BadHTTPResponseException {
        if(currentPage == 1) {
            throw new FirstPageException("Cannot display previous page: Already on the first page");
        } else {
            currentPage--;
        }
    }

    @Override
    public void refresh() throws BadHTTPResponseException {
        updateList();
    }

    public void setPage(int page) {
        if(page > totalPages) {
            return; //TODO Exception or what?
        }
        this.currentPage = page;
    }

    @Override
    public int getPage() {
        return currentPage;
    }

    @Override
    public int getPages() {
        return totalPages;
    }

    private void updateList() throws BadHTTPResponseException {
        updateList(new Parameter("", ""));
    }

    private void updateList(Parameter parameter) throws BadHTTPResponseException {

        this.clear();

        List<Parameter> parameters = new ArrayList<>(Arrays.asList(
                parameter,
                new Parameter("per_page", "100")
        ));

        List<byte[]> getResponses;

        long start = System.nanoTime();

        getResponses = connection.GETRequest(restEndpoint, parameters, totalPages);

        for(byte[] getResponse : getResponses) {

            Any json = JsonIterator.deserialize(getResponse);

            json.forEach(item -> this.add(item.as(containedClass)));
        }

        System.out.println("Request timing: " + ((System.nanoTime() - start) / 1000000) + " ms");
    }

    @Override
    public Iterator<E> iterator() {

        ArrayList<E> paginatedInternalList = new ArrayList<>();

        int startElement = (currentPage - 1) * perPage;
        int endElement = currentPage * perPage;;

        if(endElement > internalList.size()) {
            endElement = internalList.size();
        }

        for(int i = startElement; i < endElement; i++) {
            paginatedInternalList.add(internalList.get(i));
        }

        return paginatedInternalList.iterator();
    }

    public int maxPerPage() {
        return Integer.MAX_VALUE;
    }

    @Override
    public void filterByStatus(StatusFilter filter) throws BadHTTPResponseException {

    }

    @Override
    public StatusFilter[] getStatuses() {
        return StatusFilter.values();
    }

    @Override
    public int elementsWithStatus(StatusFilter status) {
        return 0;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }
}
