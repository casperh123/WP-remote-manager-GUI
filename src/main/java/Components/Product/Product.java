package Components.Product;

import Components.AbstractComponent;
import Components.WPImage;
import Components.Product.ProductComponents.*;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;
import com.jsoniter.fuzzy.MaybeStringIntDecoder;

import java.util.List;

public class Product extends AbstractComponent {


    @JsonProperty("stock_quantity")
    protected Integer stockQuantity;

    //Read-Only
    @JsonProperty(decoder = MaybeStringIntDecoder.class)
    protected int total_sales;

    //Read-only
    @JsonProperty("rating_count")
    protected int ratingCount;
    @JsonProperty("parent_id")
    protected int parentId;
    @JsonProperty("menu_order")
    protected int menuOrder;

    //Read-only
    @JsonProperty("on_sale")
    protected boolean onSale;
    @JsonProperty("manage_stock")
    protected boolean manageStock;
    @JsonProperty("reviews_allowed")
    protected boolean reviewsAllowed;
    protected boolean featured;
    @JsonProperty("sold_individually")
    protected boolean soldIndividually;

    //Read-only
    protected boolean purchasable;
    protected boolean virtual;
    protected boolean downloadable;

    //Read-only
    @JsonProperty("backorders_allowed")
    protected boolean backordersAllowed;

    //Read-only
    protected boolean backordered;

    //Read-only
    @JsonProperty("shipping_required")
    protected boolean shippingRequired;

    //Read-only
    @JsonProperty("shipping_taxable")
    protected boolean shippingTaxable;

    @JsonProperty("regular_price")
    protected String regularPrice;
    @JsonProperty("sale_price")
    protected String salePrice;
    //Read-only
    @JsonProperty("average_rating")
    protected String averageRating;
    protected String weight;
    protected String sku;
    @JsonProperty("date_on_sale_from")
    protected String dateOnSaleFrom;
    @JsonProperty("date_on_sale_from_gmt")
    protected String dateOnSaleFromGmt;
    @JsonProperty("date_on_sale_to")
    protected String dateOnSaleTo;
    @JsonProperty("date_on_sale_to_gmt")
    protected String dateOnSaleToGmt;

    //Read-only
    protected String permalink;
    protected String slug;
    @JsonProperty("external_url")
    protected String externalUrl;
    protected String backorders;
    @JsonProperty("button_text")
    protected String buttonText;
    @JsonProperty("tax_status")
    protected String taxStatus;
    @JsonProperty("tax_class")
    protected String taxClass;

    //Read-only
    @JsonProperty("date_created")
    protected String dateCreated;

    //Read-only
    @JsonProperty("date_created_gmt")
    protected String dateCreatedGmt;

    //Read-only
    @JsonProperty("date_modified")
    protected String dateModified;
    @JsonProperty("catalog_visibility")
    protected String catalogVisibility;
    protected String type;
    @JsonProperty("stock_status")
    protected String stockStatus;
    protected String description;
    @JsonProperty("short_description")
    protected String shortDescription;
    protected String status;

    //Read-only
    @JsonProperty("shipping_class")
    protected String shippingClass;

    //Read-only
    @JsonProperty("shipping_class_id")
    protected int shippingClassId;
    @JsonProperty("shipping_note")
    protected String shippingNote;

    protected List<WPImage> images;
    @JsonProperty("upsell_ids")
    protected List<Integer> upsellIds;
    @JsonProperty("cross_sell_ids")
    protected List<Integer> crossSellIds;

    //Read-only
    @JsonProperty("related_ids")
    protected List<Integer> relatedIds;
    protected List<Integer> variations;
    @JsonProperty("grouped_products")
    protected List<Integer> groupedProducts;
    protected List<ProductCategory> categories;
    protected List<Tag> tags;
    protected List<Attribute> attributes;
    @JsonProperty("default_attributes")
    protected List<DefaultAttribute> defaultAttributes;
    protected Dimensions dimensions;

    @JsonCreator
    public Product() {}

    @Override
    public int hashCode() {
        return sku.hashCode();
    }

    @Override
    public boolean equals(Object o) {

        if(o == this) {
            return true;
        }

        if(!(o instanceof Product product)) {
            return false;
        }

        return this.sku.equals(product.sku);
    }

    @Override
    public String toString() {
        return "AbstractProduct{" +
                "stockQuantity=" + stockQuantity +
                ", totalSales=" + total_sales +
                ", ratingCount=" + ratingCount +
                ", parentId=" + parentId +
                ", menuOrder=" + menuOrder +
                ", onSale=" + onSale +
                ", manageStock=" + manageStock +
                ", reviewsAllowed=" + reviewsAllowed +
                ", featured=" + featured +
                ", soldIndividually=" + soldIndividually +
                ", purchasable=" + purchasable +
                ", virtual=" + virtual +
                ", downloadable=" + downloadable +
                ", backordersAllowed=" + backordersAllowed +
                ", backordered=" + backordered +
                ", shippingRequired=" + shippingRequired +
                ", shippingTaxable=" + shippingTaxable +
                ", regularPrice='" + regularPrice + '\'' +
                ", salePrice='" + salePrice + '\'' +
                ", averageRating='" + averageRating + '\'' +
                ", weight='" + weight + '\'' +
                ", sku='" + sku + '\'' +
                ", dateOnSaleFrom='" + dateOnSaleFrom + '\'' +
                ", dateOnSaleFromGmt='" + dateOnSaleFromGmt + '\'' +
                ", dateOnSaleTo='" + dateOnSaleTo + '\'' +
                ", dateOnSaleToGmt='" + dateOnSaleToGmt + '\'' +
                ", permalink='" + permalink + '\'' +
                ", slug='" + slug + '\'' +
                ", externalUrl='" + externalUrl + '\'' +
                ", backorders='" + backorders + '\'' +
                ", buttonText='" + buttonText + '\'' +
                ", taxStatus='" + taxStatus + '\'' +
                ", taxClass='" + taxClass + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", catalogVisibility='" + catalogVisibility + '\'' +
                ", type='" + type + '\'' +
                ", stockStatus='" + stockStatus + '\'' +
                ", description='" + description + '\'' +
                ", shortDescription='" + shortDescription + '\'' +
                ", status='" + status + '\'' +
                ", shippingClass='" + shippingClass + '\'' +
                ", shippingClassId=" + shippingClassId +
                ", shippingNote='" + shippingNote + '\'' +
                ", images=" + images +
                ", upsellIds=" + upsellIds +
                ", crossSellIds=" + crossSellIds +
                ", relatedIds=" + relatedIds +
                ", variations=" + variations +
                ", groupedProducts=" + groupedProducts +
                ", categories=" + categories +
                ", tags=" + tags +
                ", attributes=" + attributes +
                ", defaultAttributes=" + defaultAttributes +
                ", dimensions=" + dimensions +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public List<WPImage> getImages() {
        return images;
    }

    public String getUrl() {
        return permalink;
    }

    public String getName() {
        return name;
    }

    public String getSku() {
        return sku;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() { return description; }

    public String getShortDescription() { return shortDescription; }
}
