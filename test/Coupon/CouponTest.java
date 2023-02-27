package Coupon;

import Components.Coupon.Coupon;
import com.jsoniter.JsonIterator;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

public class CouponTest {

    @Test
    public void couponCanBeGeneratedFromJson() {
        Coupon coupon = JsonIterator.deserialize("{\"id\": 719," +
                "\"code\": \"10off\"," +
                "\"amount\": \"10.00\"," +
                "\"date_created\": \"2017-03-21T15:23:00\"," +
                "\"date_created_gmt\": \"2017-03-21T18:23:00\"," +
                "\"date_modified\": \"2017-03-21T15:23:00\"," +
                "\"date_modified_gmt\": \"2017-03-21T18:23:00\"," +
                "\"discount_type\": \"percent\"," +
                "\"description\": \"Mannes man in a mannes\"," +
                "\"date_expires\": \"2017-03-21T18:23:12\"," +
                "\"date_expires_gmt\": \"2017-03-21T18:23:22\"," +
                "\"usage_count\": 150," +
                "\"individual_use\": true," +
                "\"product_ids\": [500, 20, 10]," +
                "\"excluded_product_ids\": [25, 30, 40]," +
                "\"usage_limit\": 500," +
                "\"usage_limit_per_user\": 10," +
                "\"limit_usage_to_x_items\": 5," +
                "\"free_shipping\": false," +
                "\"product_categories\": [2, 3, 4]," +
                "\"excluded_product_categories\": [1, 2, 3]," +
                "\"exclude_sale_items\": true," +
                "\"minimum_amount\": \"100.00\"," +
                "\"maximum_amount\": \"0.00\"," +
                "\"email_restrictions\": [\"casperholten@me.com\"]," +
                "\"used_by\": [6, 7, 8]," +
                "\"meta_data\": []" +
                "}", Coupon.class);

        assert(coupon.getId() == 719);
        assert(coupon.getCode().equals("10off"));
        assert(coupon.getAmount().equals("10.00"));
        assert(coupon.getDateCreated().equals("2017-03-21T15:23:00"));
        assert(coupon.getDateCreatedGmt().equals("2017-03-21T18:23:00"));
        assert(coupon.getDateModified().equals("2017-03-21T15:23:00"));
        assert(coupon.getDateModifiedGmt().equals("2017-03-21T18:23:00"));
        assert(coupon.getDiscountType().equals("percent"));
        assert(coupon.getDescription().equals("Mannes man in a mannes"));
        assert(coupon.getDateExpires().equals("2017-03-21T18:23:12"));
        assert(coupon.getDateExpiresGmt().equals("2017-03-21T18:23:22"));
        assert(coupon.getUsageCount() == 150);
        assert(coupon.isIndividualUse());
        assert(coupon.getProductIds().containsAll(Arrays.asList(500,20,10)));
        assert(coupon.getExcludedProductIds().containsAll(Arrays.asList(25,30,40)));
        assert(coupon.getUsage_limit() == 500);
        assert(coupon).getUsage_limit_per_user() == 10;
        assert(coupon.getLimitUsageToXItems() == 5);
        assert(!coupon.isFreeShipping());
        assert(coupon.getProductCategories().containsAll(Arrays.asList(2,3,4)));
        assert(coupon.getExcludedProductCategories().containsAll(Arrays.asList(1,2,3)));
        assert(coupon.isExcludeSaleItems());
        assert(coupon.getMinimumAmount().equals("100.00"));
        assert(coupon.getMaximumAmount().equals("0.00"));
        assert(coupon.getEmailRestrictions().contains("casperholten@me.com"));
    }
}
