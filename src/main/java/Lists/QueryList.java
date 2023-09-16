package Lists;

import Components.Interfaces.ID;
import Exceptions.BadHTTPResponseException;
import Exceptions.FirstPageException;
import Exceptions.LastPageException;
import REST.Parameter;
import REST.RESTConnection;
import okhttp3.Headers;

import java.io.IOException;

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

        Headers httpHeaders = connection.GETRequestHeaders(restEndpoint, new Parameter("per_page", "1"));
        String headerValueTotal = httpHeaders.get("x-wp-total");
        String headerValueTotalPages = httpHeaders.get("x-wp-totalpages");

        totalItems = (headerValueTotal == null) ? 0 : Integer.parseInt(headerValueTotal);
        totalPages = (headerValueTotalPages == null) ? 0 : (int) Math.ceil(totalItems / 100.0);
    }

    public abstract void nextPage() throws LastPageException, IOException;

    public abstract void previousPage() throws FirstPageException, IOException;

    public abstract void refresh() throws IOException;

    public abstract int getPage();

    public abstract int getPages();
    public int loadedItems() {
        return totalItems;
    }
    public abstract void setPage(int page) throws IOException;
    public abstract void setPerPage(int perPage);
    public abstract int maxPerPage();
    public abstract void filterByStatus(StatusFilter filter) throws IOException;
    public abstract StatusFilter[] getStatuses();
    public abstract int elementsWithStatus(StatusFilter status);
}
