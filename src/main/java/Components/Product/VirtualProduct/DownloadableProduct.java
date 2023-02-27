package Components.Product.VirtualProduct;

import Components.Product.RegularProduct.RegularProduct;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class DownloadableProduct extends RegularProduct {

    @JsonProperty("download_limit")
    private int downloadLimit;

    @JsonProperty("download_expiry")
    private int downloadExpiry;

    @JsonCreator
    DownloadableProduct() {}
}
