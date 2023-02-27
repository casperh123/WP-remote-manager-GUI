package Components.Product.ProductComponents;

import com.jsoniter.annotation.JsonCreator;

public class Dimensions {

    private String length;
    private String width;
    private String height;

    @JsonCreator
    public Dimensions() {
    }

    public Dimensions(String length, String width, String height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String toString() {
        return "Height: " + height + " Width: " + width + "Length: " + length;
    }
}
