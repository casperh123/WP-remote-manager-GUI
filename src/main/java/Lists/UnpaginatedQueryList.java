package Lists;

import Components.AbstractComponent;
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

        Map<String, List<String>> httpHeaders = connection.GETRequestHead(restEndpoint, "&per_page=1").toMultimap();

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
    public int getCurrentPage() {
        return 0;
    }

    @Override
    public int getPagesAmount() {
        return 0;
    }

    @Override
    public void updateList() throws BadHTTPResponseException {

        this.clear();

        List<String> parameters = new ArrayList<>();
        List<byte[]> getResponses;

        for(int i = 1; i <= totalPages; i++) {
            parameters.add("&per_page=100&page=" + i);
        }

        getResponses = connection.GETRequest(restEndpoint, parameters);

        for(byte[] getResponse : getResponses) {

            Any json = JsonIterator.deserialize(getResponse);

            json.forEach(item -> this.add(item.as(containedClass)));
        }
    }
}
