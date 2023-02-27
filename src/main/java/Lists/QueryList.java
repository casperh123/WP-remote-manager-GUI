package Lists;

import Exceptions.BadHTTPResponseException;
import REST.RESTConnection;

public abstract class QueryList<E> extends AbstractComponentList<E> {
    protected RESTConnection connection;
    protected String restEndpoint;
    protected int totalItems;
    protected int totalPages;
    protected Class<E> containedClass;

    public QueryList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws BadHTTPResponseException {
        super();
        this.connection = connection;
        this.restEndpoint = restEndpoint;
        this.containedClass = containedClass;
    }

    public abstract void updateList() throws BadHTTPResponseException;
}
