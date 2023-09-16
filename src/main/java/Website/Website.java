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
import Lists.SingleRequestList;
import Lists.QueryList;
import Lists.CompleteProductList;
import Lists.SingleRequestOrderList;
import REST.Parameter;
import REST.RESTConnection;
import REST.RESTEndpoints;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Website {

    private APICredentials apiCredentials;
    private Currency currency;
    private QueryList<Product> paginatedProducts;
    private QueryList<Product> allProducts;
    private QueryList<Order> orders;
    private QueryList<Tag> tags;
    private QueryList<Category> categories;
    private QueryList<Coupon> coupons;
    private QueryList<Customer> customers;

    public Website(APICredentials apiCredentials) {

        this.apiCredentials = apiCredentials;
        this.currency = null;
        this.paginatedProducts= null;
        this.allProducts = null;
        this.orders = null;
        this.tags = null;
        this.categories = null;
        this.coupons = null;
        customers = null;

        RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

        try {
            byte[] getResponse = connection.GETRequest(RESTEndpoints.getCurrentCurrencyEndpoint());

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

    public QueryList<Product> getPaginatedProducts() throws BadHTTPResponseException {

        if(paginatedProducts == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            paginatedProducts = new SingleRequestList<>(connection, RESTEndpoints.getProductsEndpoint(), Product.class);
        }
        return paginatedProducts;
    }

    public QueryList<Product> getAllProducts() throws BadHTTPResponseException {
        if(paginatedProducts == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            paginatedProducts = new CompleteProductList(connection, RESTEndpoints.getProductsEndpoint());
        }
        return paginatedProducts;
    }

    public QueryList<Coupon> getCoupons() throws BadHTTPResponseException {

        if(coupons == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            coupons = new SingleRequestList<>(connection, RESTEndpoints.getCouponsEndpoint(), Coupon.class);
        }
        return coupons;
    }

    public QueryList<Customer> getCustomers() throws BadHTTPResponseException {

        if(currency == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            customers = new SingleRequestList<>(connection, RESTEndpoints.getCustomersEndpoint(), Customer.class);
        }
        return customers;
    }

    public QueryList<Order> getOrders() throws BadHTTPResponseException {

        if(orders == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            orders = new SingleRequestOrderList(connection, RESTEndpoints.getOrdersEndpoint());
        }
        return orders;
    }

    public QueryList<Tag> getTags() throws BadHTTPResponseException {

        if(tags == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            tags = new SingleRequestList<>(connection, RESTEndpoints.getProductTagsEndpoint(), Tag.class);
        }
        return tags;
    }

    public QueryList<Category> getCategories() throws BadHTTPResponseException {

        if(categories == null) {
            RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

            categories = new SingleRequestList<>(connection, RESTEndpoints.getProductCategoriesEndpoint(), Category.class);
        }
        return categories;
    }

    public QueryList<ProductAttribute> getProductAttributes() throws FetchException {

        RESTConnection connection = new RESTConnection(apiCredentials.getUrl(), apiCredentials);

        CompletableFuture<SingleRequestList<ProductAttribute>> listFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return new SingleRequestList<>(connection, RESTEndpoints.getProductAttributesEndpoint(), ProductAttribute.class) {
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
