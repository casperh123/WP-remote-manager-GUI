package Components.Order.RefundComponents;

import Components.LineItem;
import com.jsoniter.annotation.JsonCreator;

public class LineItemRefund extends LineItem {

    private Integer refund_total;

    @JsonCreator
    public LineItemRefund() {}



    @Override
    public String toString() {
        return "LineItemRefund{" +
                "refund_total=" + refund_total +
                ", id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

    public Integer getRefund_total() {
        return refund_total;
    }

    public void setRefund_total(Integer refund_total) {
        this.refund_total = refund_total;
    }
}
