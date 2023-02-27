package Components.Product.ProductComponents;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;

import java.util.List;

public class Attribute extends AbstractComponent {

    private int position;
    private boolean visible;
    private boolean variation;
    private List<String> options;

    @JsonCreator
    public Attribute() {
    }

    public Attribute(int id, String name, int position, boolean visible, boolean variation, List<String> options) {
        super(id, name);
        this.position = position;
        this.visible = visible;
        this.variation = variation;
        this.options = options;
    }

    public String toString() {
        return super.toString() + " Position: " + position + " Visible: " + visible + " Variation " + variation + " Options" + options;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public boolean isVariation() {
        return variation;
    }

    public void setVariation(boolean variation) {
        this.variation = variation;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
