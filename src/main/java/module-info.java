module GUI {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.google.common;
    requires jsoniter;
    requires okhttp3;
    requires org.jetbrains.annotations;
    requires kotlin.stdlib;
    requires java.desktop;

    opens GUI to javafx.fxml;
    opens Components.Product.ProductComponents;
    opens Components;
    opens Components.Product;
    opens Components.Category;
    opens Components.Coupon;
    opens Components.Customer;
    opens Components.ProductAttribute;
    opens Components.Order;
    opens Components.Order.OrderComponents;
    opens Components.Order.RefundComponents;
    exports Components.Order.RefundComponents;
    exports Components.Order;
    exports Components.ProductAttribute;
    exports GUI;
    exports Components.Product.ProductComponents;
    exports Components;
    exports Components.Category;
    exports Components.Product;
    exports Components.Coupon;
    exports Components.Customer;


}