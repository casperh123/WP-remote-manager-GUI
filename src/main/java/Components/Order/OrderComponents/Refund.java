package Components.Order.OrderComponents;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;

public class Refund implements ID {
    //Read-only
    private int id;
    private String reason;
    private String total;

    @JsonCreator
    public Refund() {}

    public int getId() {
        return id;
    }

    public String getReason() {
        return reason;
    }

    public String getTotal() {
        return total;
    }

    @Override
    public String toString() {
        return "Refund{" +
                "id=" + id +
                ", reason='" + reason + '\'' +
                ", total='" + total + '\'' +
                '}';
    }
}
