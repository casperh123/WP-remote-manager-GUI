package Components.Order.OrderComponents;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class CouponLine implements ID {

    //Read-only
    private int id;
    private String code;

    //Read-only
    private String discount;

    //Read-only
    @JsonProperty("discount_tax")
    private String discountTax;

    @JsonCreator
    public CouponLine() {}

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDiscount() {
        return discount;
    }

    public String getDiscountTax() {
        return discountTax;
    }

    @Override
    public String toString() {
        return "CouponLine{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", discount='" + discount + '\'' +
                ", discountTax='" + discountTax + '\'' +
                '}';
    }
}
