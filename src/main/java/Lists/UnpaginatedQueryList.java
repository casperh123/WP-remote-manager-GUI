package Lists;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.RESTConnection;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class UnpaginatedQueryList<E extends ID> extends QueryList<E> {

    public UnpaginatedQueryList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws BadHTTPResponseException {

        super(connection, restEndpoint, containedClass);

        long start = System.nanoTime();

        Map<String, List<String>> httpHeaders = connection.GETRequestHeaders(restEndpoint, "&per_page=1").toMultimap();

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

    @Override
    public void getNextPage() throws LastPageException, BadHTTPResponseException {

    }

    @Override
    public void getPreviousPage() throws FirstPageException, BadHTTPResponseException {

    }

    @Override
    public void refresh() throws BadHTTPResponseException {
        updateList();
    }

    public void setPage(int page) {

    }

    @Override
    public int getCurrentPage() {
        return 0;
    }

    @Override
    public int getPagesAmount() {
        return 0;
    }

    private void updateList() throws BadHTTPResponseException {

        this.clear();

        List<String> parameters = new ArrayList<>();
        List<byte[]> getResponses;

        for(int i = 1; i <= totalPages; i++) {
            parameters.add("&per_page=100&page=" + i);
        }

        long start = System.nanoTime();

        getResponses = connection.GETRequest(restEndpoint, parameters);

        for(byte[] getResponse : getResponses) {

            Any json = JsonIterator.deserialize(getResponse);

            json.forEach(item -> this.add(item.as(containedClass)));
        }

        System.out.println("Request timing: " + ((System.nanoTime() - start) / 1000000) + " ms");
    }
}
