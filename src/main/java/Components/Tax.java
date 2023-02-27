package Components;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;
public class Tax implements ID {

    private int id;
    private String total;
    private String subtotal;

    @JsonCreator
    public Tax() {}

    @Override
    public String toString() {
        return "Tax{" +
                "id=" + id +
                ", total='" + total + '\'' +
                ", subtotal='" + subtotal + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(String subtotal) {
        this.subtotal = subtotal;
    }
}
