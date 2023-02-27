package Components.Product.ProductComponents;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;

public class DefaultAttribute extends AbstractComponent {

    private String option;

    @JsonCreator()
    public DefaultAttribute() {}

    public DefaultAttribute(int id, String name, String options) {
        super(id, name);
        this.option = options;
    }

    public String toString() {
        return super.toString() + " Options: " + option;
    }

    public String getOption() {
        return option;
    }

    public void setOption(String option) {
        this.option = option;
    }
}
