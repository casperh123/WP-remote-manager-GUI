package Components.Order.RefundComponents;

import Components.Tax;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class TaxRefund extends Tax {
    @JsonProperty("refund_total")
    private Integer refundTotal;

    @JsonCreator
    public TaxRefund() {

    }
}
