package Components.Order;

import Components.Billing;
import Components.Interfaces.ID;
import Components.LineItem;
import Components.Order.OrderComponents.CouponLine;
import Components.Order.OrderComponents.FeeLine;
import Components.Order.OrderComponents.TaxLine;
import Components.Shipping;
import com.jsoniter.annotation.JsonProperty;

import java.util.List;

public class Order implements ID {

    //Read-only
    private int id;
    @JsonProperty("parent_id")
    private int parentId;

    //Read-only
    private String number;

    //Read-only
    @JsonProperty("order_key")
    private String orderKey;

    //Read-only
    @JsonProperty("created_via")
    private String createdVia;

    //Read-only
    private String version;

    private String status;
    private String currency;

    //Read-only
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_created_gmt")
    private String dateCreatedGmt;
    @JsonProperty("date_modified")
    private String dateModified;
    @JsonProperty("date_modified_gmt")
    private String dateModifiedGmt;

    //Read-only
    @JsonProperty("discount_total")
    private String discountTotal;

    //Read-only
    @JsonProperty("discount_tax")
    private String discountTax;

    //Read-only
    @JsonProperty("shipping_total")
    private String shippingTotal;

    //Read-only
    @JsonProperty("shipping_tax")
    private String shipping_tax;

    //Read-only
    @JsonProperty("cart_tax")
    private String cartTax;

    //Read-only
    private String total;

    //Read-only
    @JsonProperty("total_tax")
    private String totalTax;

    //Read-only
    @JsonProperty("prices_include_tax")
    private boolean pricesIncludeTax;
    @JsonProperty("customer_id")
    private int customerId;

    //Read-only
    @JsonProperty("customer_ip_address")
    private String customerIpAdress;

    //Read-only
    @JsonProperty("customer_user_agent")
    private String customerUserAgent;
    @JsonProperty("customer_note")
    private String customerNote;
    private Billing billing;
    private Shipping shipping;
    @JsonProperty("payment_method")
    private String paymentMethod;
    @JsonProperty("payment_method_title")
    private String paymentMethodTitle;
    @JsonProperty("transaction_id")
    private String transactionId;

    //Read-only
    @JsonProperty("date_paid")
    private String datePaid;

    //Read-only
    @JsonProperty("date_paid_gmt")
    private String datePaidGmt;

    //Read-only
    @JsonProperty("date_completed")
    private String dateCompleted;

    //Read-only
    @JsonProperty("date_completed_gmt")
    private String dateCompletedGmt;

    //Read-only
    @JsonProperty("cart_hash")
    private String cartHash;
    @JsonProperty("line_items")
    private List<LineItem> lineItems;

    //Read-only
    @JsonProperty("tax_lines")
    private List<TaxLine> taxLines;
    @JsonProperty("fee_lines")
    private List<FeeLine> feeLines;
    @JsonProperty("coupon_lines")
    private List<CouponLine> couponLines;

    //Read-only
    private List<Refund> refunds;

    //Write-only
    @JsonProperty("set_paid")
    private boolean setPaid;

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", parentId=" + parentId +
                ", number='" + number + '\'' +
                ", orderKey='" + orderKey + '\'' +
                ", createdVia='" + createdVia + '\'' +
                ", version='" + version + '\'' +
                ", status='" + status + '\'' +
                ", currency='" + currency + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", dateModifiedGmt='" + dateModifiedGmt + '\'' +
                ", discountTotal='" + discountTotal + '\'' +
                ", discountTax='" + discountTax + '\'' +
                ", shippingTotal='" + shippingTotal + '\'' +
                ", shipping_tax='" + shipping_tax + '\'' +
                ", cartTax='" + cartTax + '\'' +
                ", total='" + total + '\'' +
                ", totalTax='" + totalTax + '\'' +
                ", pricesIncludeTax=" + pricesIncludeTax +
                ", customerId='" + customerId + '\'' +
                ", customerIpAdress='" + customerIpAdress + '\'' +
                ", customerUserAgent='" + customerUserAgent + '\'' +
                ", customerNote='" + customerNote + '\'' +
                ", billing=" + billing +
                ", shipping=" + shipping +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", paymentMethodTitle='" + paymentMethodTitle + '\'' +
                ", transactionId='" + transactionId + '\'' +
                ", datePaid='" + datePaid + '\'' +
                ", datePaidGmt='" + datePaidGmt + '\'' +
                ", dateCompleted='" + dateCompleted + '\'' +
                ", dateCompletedGmt='" + dateCompletedGmt + '\'' +
                ", cart_hash='" + cartHash + '\'' +
                ", lineItems=" + lineItems +
                ", taxLines=" + taxLines +
                ", feeLines=" + feeLines +
                ", couponLines=" + couponLines +
                ", refunds=" + refunds +
                ", setPaid=" + setPaid +
                '}';
    }

    public int getId() {
        return id;
    }

    public int getParentId() {
        return parentId;
    }

    public void setParentId(int parentId) {
        this.parentId = parentId;
    }

    public String getNumber() {
        return number;
    }

    public String getOrderKey() {
        return orderKey;
    }

    public String getCreatedVia() {
        return createdVia;
    }

    public String getVersion() {
        return version;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    public String getDateModified() {
        return dateModified;
    }

    public String getDateModifiedGmt() {
        return dateModifiedGmt;
    }

    public String getDiscountTotal() {
        return discountTotal;
    }

    public String getDiscountTax() {
        return discountTax;
    }

    public String getShippingTotal() {
        return shippingTotal;
    }

    public String getShipping_tax() {
        return shipping_tax;
    }

    public String getCartTax() {
        return cartTax;
    }

    public String getTotal() {
        return total;
    }

    public String getTotalTax() {
        return totalTax;
    }

    public boolean isPricesIncludeTax() {
        return pricesIncludeTax;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerIpAdress() {
        return customerIpAdress;
    }

    public String getCustomerUserAgent() {
        return customerUserAgent;
    }

    public String getCustomerNote() {
        return customerNote;
    }

    public void setCustomerNote(String customerNote) {
        this.customerNote = customerNote;
    }

    public Billing getBilling() {
        return billing;
    }

    public void setBilling(Billing billing) {
        this.billing = billing;
    }

    public Shipping getShipping() {
        return shipping;
    }

    public void setShipping(Shipping shipping) {
        this.shipping = shipping;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getPaymentMethodTitle() {
        return paymentMethodTitle;
    }

    public void setPaymentMethodTitle(String paymentMethodTitle) {
        this.paymentMethodTitle = paymentMethodTitle;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getDatePaid() {
        return datePaid;
    }

    public String getDatePaidGmt() {
        return datePaidGmt;
    }

    public String getDateCompleted() {
        return dateCompleted;
    }

    public String getDateCompletedGmt() {
        return dateCompletedGmt;
    }

    public String getCartHash() {
        return cartHash;
    }

    public List<LineItem> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<LineItem> lineItems) {
        this.lineItems = lineItems;
    }

    public List<TaxLine> getTaxLines() {
        return taxLines;
    }

    public List<FeeLine> getFeeLines() {
        return feeLines;
    }

    public void setFeeLines(List<FeeLine> feeLines) {
        this.feeLines = feeLines;
    }

    public List<CouponLine> getCouponLines() {
        return couponLines;
    }

    public void setCouponLines(List<CouponLine> couponLines) {
        this.couponLines = couponLines;
    }

    public List<Refund> getRefunds() {
        return refunds;
    }

    public boolean isSetPaid() {
        return setPaid;
    }

    public void setSetPaid(boolean setPaid) {
        this.setPaid = setPaid;
    }
}
