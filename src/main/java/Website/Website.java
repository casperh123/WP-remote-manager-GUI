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
import Lists.CompleteProductList;
import Lists.QueryList;
import Lists.SingleRequestList;
import Lists.SingleRequestOrderList;
import REST.RESTConnection;
import REST.RESTEndpoints;
import REST.RequestBuilder;
import com.jsoniter.JsonIterator;
import com.jsoniter.any.Any;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    private final RESTConnection connection;
    private static final Logger logger = Logger.getLogger(Website.class.getName());


    public Website(APICredentials apiCredentials) {
        this.apiCredentials = apiCredentials;
        this.connection = new RESTConnection(new RequestBuilder(apiCredentials.getUrl(), apiCredentials));
        logger.info("Initializing currency for: " + apiCredentials.getUrl());
        initializeCurrency();
    }

    private void initializeCurrency() {
        try {
            byte[] responseBody = connection.GETRequest(RESTEndpoints.getCurrentCurrencyEndpoint());
            Any json = JsonIterator.deserialize(responseBody);
            currency = json.as(Currency.class);
            logger.info("Successfully initialized currency for: " + apiCredentials.getUrl());
        } catch (BadHTTPResponseException e) {
            currency = new Currency("NaN", "NaN", "NaN");
            logger.log(Level.SEVERE, "Failed to fetch currency data from: " + apiCredentials.getUrl(), e);
        }
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

    public Currency getCurrency() {
        return currency;
    }

    public QueryList<Product> getPaginatedProducts() throws IOException {
        if (paginatedProducts == null) {
            paginatedProducts = new SingleRequestList<>(connection, RESTEndpoints.getProductsEndpoint(), Product.class);
            logger.info("Fetched paginated products for website: " + getUrl());
        }
        return paginatedProducts;
    }

    public QueryList<Product> getAllProducts() throws IOException {
        if (allProducts == null) {
            allProducts = new CompleteProductList(connection, RESTEndpoints.getProductsEndpoint());
            logger.info("Fetched all products for website: " + getUrl());
        }
        return allProducts;
    }

    public QueryList<Coupon> getCoupons() throws IOException {
        if (coupons == null) {
            coupons = new SingleRequestList<>(connection, RESTEndpoints.getCouponsEndpoint(), Coupon.class);
            logger.info("Fetched coupons for website: " + getUrl());
        }
        return coupons;
    }

    public QueryList<Customer> getCustomers() throws IOException {
        if (customers == null) {
            customers = new SingleRequestList<>(connection, RESTEndpoints.getCustomersEndpoint(), Customer.class);
            logger.info("Fetched customers for website: " + getUrl());
        }
        return customers;
    }

    public QueryList<Order> getOrders() throws IOException {
        if (orders == null) {
            orders = new SingleRequestOrderList(connection, RESTEndpoints.getOrdersEndpoint());
            logger.info("Fetched orders for website: " + getUrl());
        }
        return orders;
    }

    public QueryList<Tag> getTags() throws IOException {
        if (tags == null) {
            tags = new SingleRequestList<>(connection, RESTEndpoints.getProductTagsEndpoint(), Tag.class);
            logger.info("Fetched product tags for website: " + getUrl());
        }
        return tags;
    }

    public QueryList<Category> getCategories() throws IOException {
        if (categories == null) {
            categories = new SingleRequestList<>(connection, RESTEndpoints.getProductCategoriesEndpoint(), Category.class);
            logger.info("Fetched product categories for website: " + getUrl());
        }
        return categories;
    }

    public QueryList<ProductAttribute> getProductAttributes() throws IOException {
        QueryList<ProductAttribute> attributes = new SingleRequestList<>(connection, RESTEndpoints.getProductAttributesEndpoint(), ProductAttribute.class);
        logger.info("Fetched product attributes for website: " + getUrl());
        return attributes;
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
