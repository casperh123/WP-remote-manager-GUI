package Components.Product.ProductComponents;

import Components.AbstractComponent;
import com.jsoniter.annotation.JsonCreator;

public class Download extends AbstractComponent {

    private String file;

    @JsonCreator
    public Download() {

    }

    public Download(int id, String name, String file) {
        super(id, name);
        this.file = file;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String toString() {
        return super.toString() + " URL: " + file;
    }
}
