package Components.Order;

import Components.Interfaces.ID;
import Components.Order.RefundComponents.LineItemRefund;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

import java.util.List;

public class Refund implements ID {

    private int id;

    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_created_gmt")
    private String dateCreatedGmt;
    private String amount;
    private String reason;
    @JsonProperty("refunded_by")
    private int refundedBy;
    @JsonProperty("refunded_payment")
    private boolean refundedPayment;
    @JsonProperty("line_items")
    List<LineItemRefund> lineItems;
    @JsonProperty("api_refund")
    private boolean apiRefund;

    @JsonCreator
    public Refund() {}

    @Override
    public String toString() {
        return "Refund{" +
                "id=" + id +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                ", amount='" + amount + '\'' +
                ", reason='" + reason + '\'' +
                ", refundedBy=" + refundedBy +
                ", refundedPayment=" + refundedPayment +
                ", lineItems=" + lineItems +
                ", apiRefund=" + apiRefund +
                '}';
    }

    public void setLineItems(List<LineItemRefund> lineItems) {
        this.lineItems = lineItems;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public void setRefundedBy(int refundedBy) {
        this.refundedBy = refundedBy;
    }

    public int getId() {
        return id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    public String getAmount() {
        return amount;
    }

    public String getReason() {
        return reason;
    }

    public int getRefundedBy() {
        return refundedBy;
    }

    public boolean isRefundedPayment() {
        return refundedPayment;
    }

    public List<LineItemRefund> getLineItems() {
        return lineItems;
    }

    public boolean isApiRefund() {
        return apiRefund;
    }
}
