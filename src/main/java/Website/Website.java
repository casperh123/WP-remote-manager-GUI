package Website;

import Components.Category.Category;
import Components.Product.Product;
import Exceptions.BadHTTPResponseException;
import Lists.PaginatedQueryList;
import Lists.QueryList;
import Lists.UnpaginatedQueryList;
import REST.RESTConnection;
import REST.RESTEndpoints;
import okhttp3.Connection;

import java.io.Serializable;
import java.net.URISyntaxException;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class Website implements Serializable {

    private String name;
    private String stringUrl;
    private User user;


    public Website(String name, String url, User user) throws URISyntaxException {

        if(url.matches("^(https|http)://[a-zA-Z]+(.[a-zA-Z]+)+$")) {
            this.stringUrl = url + "/";
        } else {
            throw new URISyntaxException(url, "URL has to start with http:// or https:// and end with .xxx");
        }

        this.name = name;
        this.user = user;

        System.out.println("done");
    }

    public String getName() {
        return name;
    }
    public String getUrl() {
        return stringUrl;
    }
    public QueryList<Product> getProducts() {

        RESTConnection connection = new RESTConnection(stringUrl, user);

        CompletableFuture<PaginatedQueryList<Product>> produtListFuture = CompletableFuture.supplyAsync(() -> {
            try {
                return new PaginatedQueryList<>(connection, RESTEndpoints.getProductsEndpoint(), Product.class) {
                };
            } catch (BadHTTPResponseException e) {
                System.out.println("Fuck");
                return null;
            }
        });

        //TODO FIX
        try {
            return produtListFuture.get();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        }
    }

}
