package Lists;

import Components.AbstractComponent;
import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.RESTConnection;

public abstract class QueryList<E extends ID> extends AbstractComponentList<E> {
    protected RESTConnection connection;
    protected String restEndpoint;
    protected int totalItems;
    protected int totalPages;
    protected int currentPage;

    protected Class<E> containedClass;

    public QueryList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws BadHTTPResponseException {
        super();
        this.connection = connection;
        this.restEndpoint = restEndpoint;
        this.containedClass = containedClass;
    }

    public abstract void getNextPage() throws LastPageException, BadHTTPResponseException;

    public abstract void getPreviousPage() throws FirstPageException, BadHTTPResponseException;

    public abstract int getCurrentPage();

    public abstract int getPagesAmount();

    public abstract void updateList() throws BadHTTPResponseException;
}
