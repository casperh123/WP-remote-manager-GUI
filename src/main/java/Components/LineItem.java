package Components;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

import java.util.List;

public class LineItem extends AbstractComponent {

    //Read-only
    @JsonProperty("product_id")
    private int produdctId;
    @JsonProperty("variation_id")
    private int variationId;
    private int quantity;
    @JsonProperty("tax_class")
    private String taxClass;
    private String subtotal;

    //Read-only
    @JsonProperty("subtotal_tax")
    private String subtotalTax;
    private String total;

    //Read-only
    @JsonProperty("total_tax")
    private String totalTax;

    //Read-only
    private List<Tax> taxes;

    //Read-only
    private String sku;
    //Read-only
    private float price;

    @JsonCreator
    public LineItem() {}

    @Override
    public String toString() {
        return "LineItem{" +
                "produdctId=" + produdctId +
                ", variationId=" + variationId +
                ", quantity=" + quantity +
                ", taxClass='" + taxClass + '\'' +
                ", subtotal='" + subtotal + '\'' +
                ", subtotalTax='" + subtotalTax + '\'' +
                ", total='" + total + '\'' +
                ", totalTax='" + totalTax + '\'' +
                ", taxes=" + taxes +
                ", sku='" + sku + '\'' +
                ", price='" + price + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public int getProdudctId() {
        return produdctId;
    }

    public int getVariationId() {
        return variationId;
    }

    public void setVariationId(int variationId) {
        this.variationId = variationId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getTaxClass() {
        return taxClass;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }

    public String getSubtotalTax() {
        return subtotalTax;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public List<Tax> getTaxes() {
        return taxes;
    }

    public String getSku() {
        return sku;
    }

    public float getPrice() {
        return price;
    }
}
