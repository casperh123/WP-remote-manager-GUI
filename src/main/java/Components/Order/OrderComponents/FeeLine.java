package Components.Order.OrderComponents;

import Components.AbstractComponent;
import Components.Tax;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

import java.util.List;

public class FeeLine extends AbstractComponent {

    //Read-only
    @JsonProperty("tax_class")
    private String taxClass;
    @JsonProperty("tax_status")
    private String taxStatus;
    private String total;

    //Read-only
    @JsonProperty("total_tax")
    private String totalTax;

    //Read-only
    private List<Tax> taxes;

    @JsonCreator
    public FeeLine() {}

    public String getTaxClass() {
        return taxClass;
    }

    public String getTaxStatus() {
        return taxStatus;
    }

    public void setTaxStatus(String taxStatus) {
        this.taxStatus = taxStatus;
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

    @Override
    public String toString() {
        return "FeeLine{" +
                "taxClass='" + taxClass + '\'' +
                ", taxStatus='" + taxStatus + '\'' +
                ", total='" + total + '\'' +
                ", totalTax='" + totalTax + '\'' +
                ", taxes=" + taxes +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
