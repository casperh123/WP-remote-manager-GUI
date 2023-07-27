package Website;

import Components.Category.Category;
import Components.Coupon.Coupon;
import Components.Currency;
import Components.Customer.Customer;
import Components.Order.Order;
import Components.Product.Product;
import Components.Product.ProductComponents.Tag;
import Components.ProductAttribute.ProductAttribute;
import Exceptions.BadHTTPResponseException;
import Exceptions.FetchException;
import Lists.PaginatedQueryList;
import Lists.QueryList;
import Lists.UnpaginatedQueryList;
import REST.RESTConnection;
import REST.RESTEndpoints;
import com.google.common.io.CountingOutputStream;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Website {

    private APICredentials apiCredentials;
    private Currency currency;
    private QueryList<Product> products;
    private QueryList<Order> orders;
    private QueryList<Tag> tags;
    private QueryList<Category> categories;
    private QueryList<Coupon> coupons;
    private QueryList<Customer> customers;

    public Website(APICredentials apiCredentials) {

        this.apiCredentials = apiCredentials;
        this.currency = null;
        this.products = null;
        this.orders = null;
        this.tags = null;
        this.categories = null;
        this.coupons = null;
        customers = null;

        RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

        try {
            byte[] getResponse = connection.GETRequest(RESTEndpoints.getCurrentCurrencyEndpoint(), "");

            Any json = JsonIterator.deserialize(getResponse);

            currency = json.as(Currency.class);
        } catch (BadHTTPResponseException e) {
            currency = new Currency("NaN", "NaN", "NaN");
        }

        System.out.println("done");
    }

    public String getName() {
        return apiCredentials.getName();
    }
    public String getUrl() {
        return apiCredentials.getUrl();
    }

    public APICredentials getApiCredentials() {
        return apiCredentials;
    }

    public QueryList<Product> getProducts() throws BadHTTPResponseException {

        if(products == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            products = new PaginatedQueryList<>(connection, RESTEndpoints.getProductsEndpoint(), Product.class);
        }
        return products;
    }

    public QueryList<Product> getAllProducts() throws FetchException, BadHTTPResponseException {

        RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

        return new UnpaginatedQueryList<>(connection, RESTEndpoints.getProductsEndpoint(), Product.class);
    }

    public QueryList<Coupon> getCoupons() throws BadHTTPResponseException {

        if(coupons == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            coupons = new PaginatedQueryList<>(connection, RESTEndpoints.getCouponsEndpoint(), Coupon.class);
        }
        return coupons;
    }

    public QueryList<Customer> getCustomers() throws BadHTTPResponseException {

        if(currency == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            customers = new PaginatedQueryList<>(connection, RESTEndpoints.getCustomersEndpoint(), Customer.class);
        }
        return customers;
    }

    public QueryList<Order> getOrders() throws BadHTTPResponseException {

        if(orders == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            orders = new PaginatedQueryList<>(connection, RESTEndpoints.getOrdersEndpoint(), Order.class);
        }
        return orders;
    }

    public QueryList<Tag> getTags() throws BadHTTPResponseException {

        if(tags == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            tags = new PaginatedQueryList<>(connection, RESTEndpoints.getProductTagsEndpoint(), Tag.class);
        }
        return tags;
    }

    public QueryList<Category> getCategories() throws BadHTTPResponseException {

        if(categories == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            categories = new PaginatedQueryList<>(connection, RESTEndpoints.getProductCategoriesEndpoint(), Category.class);
        }
        return categories;
    }

    public QueryList<ProductAttribute> getProductAttributes() throws FetchException {

        RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

        CompletableFuture<PaginatedQueryList<ProductAttribute>> listFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return new PaginatedQueryList<>(connection, RESTEndpoints.getProductAttributesEndpoint(), ProductAttribute.class) {
                };
            } catch (BadHTTPResponseException e) {
                System.out.println("Fuck"); //TODO Exception
                return null;
            }
        });

        try {
            if(listFuture.get() == null) {
                throw new FetchException("Could not complete request");
            }
            return listFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new FetchException(e.getMessage());
        }
    }

    public Currency getCurrency() {
        return currency;
    }

    //TODO implement endpoint
/*
    public QueryList<ProductAttributeTerm> getProductAttributeTerm() throws FetchException {

        RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), user);

        CompletableFuture<PaginatedQueryList<ProductAttributeTerm>> listFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return new PaginatedQueryList<>(connection, RESTEndpoints.getProdu(), ProductAttributeTerm.class) {
                };
            } catch (BadHTTPResponseException e) {
                System.out.println("Fuck");
                return null;
            }
        });

        try {
            if(listFuture.get() == null) {
                throw new FetchException("Could not complete request");
            }
            return listFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new FetchException(e.getMessage());
        }
    }*/
}
