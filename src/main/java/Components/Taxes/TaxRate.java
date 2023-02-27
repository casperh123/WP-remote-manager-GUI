package Components.Taxes;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

import java.util.Arrays;

public class TaxRate extends AbstractComponent {

    private String country;
    private String state;
    private String[] postcodes;
    private String[] cities;
    private String rate;
    private int priority;
    private boolean compound;
    private boolean shipping;
    private int order;
    @JsonProperty("class")
    private String taxClass;

    @JsonCreator
    public TaxRate() {}

    @Override
    public String toString() {
        return "TaxRate{" +
                "country='" + country + '\'' +
                ", state='" + state + '\'' +
                ", postcodes=" + Arrays.toString(postcodes) +
                ", cities=" + Arrays.toString(cities) +
                ", rate='" + rate + '\'' +
                ", priority=" + priority +
                ", compound=" + compound +
                ", shipping=" + shipping +
                ", order=" + order +
                ", taxClass='" + taxClass + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setState(String state) {
        this.state = state;
    }

    public void setPostcodes(String[] postcodes) {
        this.postcodes = postcodes;
    }

    public void setCities(String[] cities) {
        this.cities = cities;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public void setCompound(boolean compound) {
        this.compound = compound;
    }

    public void setShipping(boolean shipping) {
        this.shipping = shipping;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public void setTaxClass(String taxClass) {
        this.taxClass = taxClass;
    }

    public String getCountry() {
        return country;
    }

    public String getState() {
        return state;
    }

    public String[] getPostcodes() {
        return postcodes;
    }

    public String[] getCities() {
        return cities;
    }

    public String getRate() {
        return rate;
    }

    public int getPriority() {
        return priority;
    }

    public boolean isCompound() {
        return compound;
    }

    public boolean isShipping() {
        return shipping;
    }

    public int getOrder() {
        return order;
    }

    public String getTaxClass() {
        return taxClass;
    }
}
