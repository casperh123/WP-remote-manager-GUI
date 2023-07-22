package REST;

public class RESTEndpoints {

    private RESTEndpoints() {}

    public static String getProductsEndpoint() {
        return "/wp-json/wc/v3/products?";
    }

    public static String getProductEndpoint(int id) {return "/wp-json/wc/v3/products/" + id + "?"; }

    public static String getCustomersEndpoint() {return "/wp-json/wc/v3/customers?role=all&"; }

    public static String getCustomerEndpoint(int id) { return "/wp-json/wc/v3/customers" + id + "?"; }

    public static String getProductCategoriesEndpoint() { return "/wp-json/wc/v3/products/categories?"; }

    public static String getCouponsEndpoint() {
        return "/wp-json/wc/v3/coupons?";
    }

    public static String getOrdersEndpoint() {
        return "/wp-json/wc/v3/orders?";
    }

    public static String getOrderNotesEndpoint(int orderId) {
        return "/wp-json/wc/v3/orders/2" + orderId + "/notes?";
    }

    public static String getRefundsEndpoint(int orderId) {
        return "/wp-json/wc/v3/orders/" + orderId +"/refunds?";
    }

    public static String getProductVariationsEndpoint(int productId) { return "/wp-json/wc/v3/products/" + productId + "/variations?";}

    public static String getProductAttributesEndpoint() {
        return "/wp-json/wc/v3/products/attributes?";
    }

    public static String getProductReviewsEndpoint() {return "/wp-json/wc/v3/products/reviews?";};

    public static String getProductTagsEndpoint() { return "/wp-json/wc/v3/products/tags?"; }

    public static String getCurrentCurrencyEndpoint() { return "/wp-json/wc/v3/data/currencies/current?"; }
}
