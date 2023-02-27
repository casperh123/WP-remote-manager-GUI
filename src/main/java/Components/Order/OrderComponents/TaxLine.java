package Components.Order.OrderComponents;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class TaxLine implements ID {
    private int id;
    @JsonProperty("rate_code")
    private String rateCode;
    @JsonProperty("rate_id")
    private int rateId;
    private String label;
    private boolean compound;
    @JsonProperty("tax_total")
    private String taxTotal;
    @JsonProperty("shipping_tax_total")
    private String shippingTaxTotal;

    @JsonCreator
    public TaxLine() {}

    public int getId() {
        return id;
    }

    public String getRateCode() {
        return rateCode;
    }

    public int getRateId() {
        return rateId;
    }

    public String getLabel() {
        return label;
    }

    public boolean isCompound() {
        return compound;
    }

    public String getTaxTotal() {
        return taxTotal;
    }

    public String getShippingTaxTotal() {
        return shippingTaxTotal;
    }

    @Override
    public String toString() {
        return "TaxLine{" +
                "id=" + id +
                ", rateCode='" + rateCode + '\'' +
                ", rateId='" + rateId + '\'' +
                ", label='" + label + '\'' +
                ", compound=" + compound +
                ", taxTotal='" + taxTotal + '\'' +
                ", shippingTaxTotal='" + shippingTaxTotal + '\'' +
                '}';
    }
}
