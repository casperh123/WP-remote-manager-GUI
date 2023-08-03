package Lists;

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
    protected StatusFilter componentStatus;
    protected Class<E> containedClass;

    public enum StatusFilter {
        ALL,
        PUBLISHED,
        PRIVATE,
        DRAFT,
        PENDING,
        PROCESSING,
        ON_HOLD,
        COMPLETED,
        CANCELLED,
        REFUNDED,
        FAILED,
        TRASH
    }

    public QueryList(RESTConnection connection, String restEndpoint, Class<E> containedClass) throws BadHTTPResponseException {
        super();
        this.connection = connection;
        this.restEndpoint = restEndpoint;
        this.containedClass = containedClass;
    }

    public abstract void getNextPage() throws LastPageException, BadHTTPResponseException;

    public abstract void getPreviousPage() throws FirstPageException, BadHTTPResponseException;

    public abstract void refresh() throws BadHTTPResponseException;

    public abstract int getCurrentPage();

    public abstract int getPagesAmount();
    public int getTotalItems() {
        return totalItems;
    }
    public abstract void setPage(int page) throws BadHTTPResponseException;
    public abstract void setPerPage(int perPage);
    public abstract int maxPerPage();
    public abstract void filterByStatus(StatusFilter filter) throws BadHTTPResponseException;
    public abstract StatusFilter[] getStatuses();
    public abstract int elementsWithStatus(StatusFilter status);
}
