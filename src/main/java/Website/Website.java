package Website;

import Components.Category.Category;
import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import Lists.PaginatedQueryList;
import Lists.QueryList;
import REST.RESTConnection;
import REST.RESTEndpoints;

import java.net.URISyntaxException;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Website {
    private String name;
    private String stringUrl;
    private PaginatedQueryList<Product> productList;
    private QueryList<Category> categoryList;
    private User user;
    private RESTConnection connection;


    public Website(String name, String url, User user) throws URISyntaxException {

        if(url.matches("^(https|http)://[a-zA-Z]+(.[a-zA-Z]+)+$")) {
            this.stringUrl = url + "/";
        } else {
            throw new URISyntaxException(url, "URL has to start with http:// or https:// and end with .xxx");
        }

        this.name = name;
        this.user = user;
        this.connection = new RESTConnection(stringUrl, user);

        //TODO ExceptionHandling
        CompletableFuture<PaginatedQueryList<Product>> produtListFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return new PaginatedQueryList<>(connection, RESTEndpoints.getProductsEndpoint(), Product.class) {
                };
            } catch (BadHTTPResponseException e) {
                System.out.println("Fuck");
                return null;
            }
        });

        /*//TODO Exception Handling
        CompletableFuture<QueryList<Category>> categoryListFuture = CompletableFuture.supplyAsync(()-> {
            try {
                return new PaginatedQueryList<>(connection, RESTEndpoints.getProductCategoriesEndpoint(), Category.class);
            } catch (BadHTTPResponseException e) {
                System.out.println("Fuck");
                return null;
            }
        });*/

        //TODO Exception Handling
        try {
            this.productList = produtListFuture.get();
            //this.categoryList = categoryListFuture.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }
        System.out.println("done");
    }

    public String getName() {
        return name;
    }
    public String getUrl() {
        return stringUrl;
    }
    public PaginatedQueryList<Product> getProducts() {
        return productList;
    }
    public QueryList<Category> getCategories() {
        return categoryList;
    }

}
