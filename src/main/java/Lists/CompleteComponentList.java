package Lists;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.Parameter;
import REST.RESTConnection;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CompleteComponentList<E extends ID> extends QueryList<E> {

    protected int perPage;
    protected int currentPage;

    public CompleteComponentList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws IOException {

        super(connection, restEndpoint, containedClass);

        this.perPage = 100;
        this.currentPage = 1;

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
    public void refresh() throws IOException {
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

    private void updateList() throws IOException {
        updateList(new Parameter("", ""));
    }

    private void updateList(Parameter parameter) throws IOException {

        this.clear();

        List<byte[]> responseBodies;

        responseBodies = connection.GETRequest(restEndpoint, totalPages, parameter, new Parameter("per_page", "100"));

        for(byte[] responseBody : responseBodies) {

            Any json = JsonIterator.deserialize(responseBody);

            json.forEach(item -> this.add(item.as(containedClass)));
        }
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
