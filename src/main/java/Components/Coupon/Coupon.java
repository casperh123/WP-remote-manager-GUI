package Components.Coupon;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

import java.util.List;

public class Coupon implements ID {

    //Read-Only
    private int id;

    //mandatory
    private String code;
    private String amount;

    //read-only
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_created_gmt")
    private String dateCreatedGmt;
    @JsonProperty("date_modified")
    private String dateModified;
    @JsonProperty("date_modified_gmt")
    private String dateModifiedGmt;
    @JsonProperty("discount_type")
    private String discountType;
    private String description;
    @JsonProperty("date_expires")
    private String dateExpires;
    @JsonProperty("date_expires_gmt")
    private String dateExpiresGmt;

    //Read-only
    @JsonProperty("usage_count")
    private int usageCount;

    @JsonProperty("individual_use")
    private boolean individualUse;
    @JsonProperty("product_ids")
    private List<Integer> productIds;
    @JsonProperty("excluded_product_ids")
    private
    List<Integer> excludedProductIds;
    @JsonProperty("usage_limit")
    private Integer usage_limit;
    @JsonProperty("usage_limit_per_user")
    private Integer usage_limit_per_user;
    @JsonProperty("limit_usage_to_x_items")
    private Integer limitUsageToXItems;
    @JsonProperty("free_shipping")
    private boolean freeShipping;
    @JsonProperty("product_categories") private
    List<Integer> productCategories;
    @JsonProperty("excluded_product_categories") private
    List<Integer> excludedProductCategories;
    @JsonProperty("exclude_sale_items")
    private boolean excludeSaleItems;
    @JsonProperty("minimum_amount") private
    String minimumAmount;
    @JsonProperty("maximum_amount") private
    String maximumAmount;
    @JsonProperty("email_restrictions")
    private List<String> emailRestrictions;

    //TODO Unfuck String and int return types from json
    /*@JsonProperty("used_by")
    private List<StringOrInt> usedBy;*/

    @JsonCreator
    public Coupon() {}

    @Override
    public String toString() {
        return "Coupon{" +
                "id=" + id +
                ", code='" + code + '\'' +
                ", amount='" + amount + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                ", dateModified='" + dateModified + '\'' +
                ", dateModifiedGmt='" + dateModifiedGmt + '\'' +
                ", discountType='" + discountType + '\'' +
                ", description='" + description + '\'' +
                ", dateExpires='" + dateExpires + '\'' +
                ", dateExpiresGmt='" + dateExpiresGmt + '\'' +
                ", usageCount=" + usageCount +
                ", individualUse=" + individualUse +
                ", productIds=" + productIds +
                ", excludedProductIds=" + excludedProductIds +
                ", usage_limit=" + usage_limit +
                ", usage_limit_per_user=" + usage_limit_per_user +
                ", limitUsageToXItems=" + limitUsageToXItems +
                ", freeShipping=" + freeShipping +
                ", productCategories=" + productCategories +
                ", excludedProductCategories=" + excludedProductCategories +
                ", excludeSaleItems=" + excludeSaleItems +
                ", minimumAmount='" + minimumAmount + '\'' +
                ", maximumAmount='" + maximumAmount + '\'' +
                ", emailRestrictions=" + emailRestrictions +
                '}';
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
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

    public String getDiscountType() {
        return discountType;
    }

    public void setDiscountType(String discountType) {
        this.discountType = discountType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDateExpires() {
        return dateExpires;
    }

    public void setDateExpires(String dateExpires) {
        this.dateExpires = dateExpires;
    }

    public String getDateExpiresGmt() {
        return dateExpiresGmt;
    }

    public void setDateExpiresGmt(String dateExpiresGmt) {
        this.dateExpiresGmt = dateExpiresGmt;
    }

    public int getUsageCount() {
        return usageCount;
    }

    public boolean isIndividualUse() {
        return individualUse;
    }

    public void setIndividualUse(boolean individualUse) {
        this.individualUse = individualUse;
    }

    public List<Integer> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<Integer> productIds) {
        this.productIds = productIds;
    }

    public List<Integer> getExcludedProductIds() {
        return excludedProductIds;
    }

    public void setExcludedProductIds(List<Integer> excludedProductIds) {
        this.excludedProductIds = excludedProductIds;
    }

    public int getUsage_limit() {
        return usage_limit;
    }

    public void setUsage_limit(int usage_limit) {
        this.usage_limit = usage_limit;
    }

    public int getUsage_limit_per_user() {
        return usage_limit_per_user;
    }

    public void setUsage_limit_per_user(int usage_limit_per_user) {
        this.usage_limit_per_user = usage_limit_per_user;
    }

    public int getLimitUsageToXItems() {
        return limitUsageToXItems;
    }

    public void setLimitUsageToXItems(int limitUsageToXItems) {
        this.limitUsageToXItems = limitUsageToXItems;
    }

    public boolean isFreeShipping() {
        return freeShipping;
    }

    public void setFreeShipping(boolean freeShipping) {
        this.freeShipping = freeShipping;
    }

    public List<Integer> getProductCategories() {
        return productCategories;
    }

    public void setProductCategories(List<Integer> productCategories) {
        this.productCategories = productCategories;
    }

    public List<Integer> getExcludedProductCategories() {
        return excludedProductCategories;
    }

    public void setExcludedProductCategories(List<Integer> excludedProductCategories) {
        this.excludedProductCategories = excludedProductCategories;
    }

    public boolean isExcludeSaleItems() {
        return excludeSaleItems;
    }

    public void setExcludeSaleItems(boolean excludeSaleItems) {
        this.excludeSaleItems = excludeSaleItems;
    }

    public String getMinimumAmount() {
        return minimumAmount;
    }

    public void setMinimumAmount(String minimumAmount) {
        this.minimumAmount = minimumAmount;
    }

    public String getMaximumAmount() {
        return maximumAmount;
    }

    public void setMaximumAmount(String maximumAmount) {
        this.maximumAmount = maximumAmount;
    }

    public List<String> getEmailRestrictions() {
        return emailRestrictions;
    }

    public void setEmailRestrictions(List<String> emailRestrictions) {
        this.emailRestrictions = emailRestrictions;
    }
}
