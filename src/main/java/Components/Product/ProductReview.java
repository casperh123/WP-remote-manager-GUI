package Components.Product;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class ProductReview implements ID {

    private int id;
    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_created_gmt")
    private String getDateCreatedGmt;
    @JsonProperty("product_id")
    private int productId;
    private String status;
    private String reviewer;
    @JsonProperty("reviewer_email")
    private String reviewerEmail;
    private String review;
    private int rating;
    private boolean verified;

    @JsonCreator
    public ProductReview() {}

    @Override
    public String toString() {
        return "ProductReview{" +
                "id=" + id +
                ", dateCreated='" + dateCreated + '\'' +
                ", getDateCreatedGmt='" + getDateCreatedGmt + '\'' +
                ", productId=" + productId +
                ", status='" + status + '\'' +
                ", reviewer='" + reviewer + '\'' +
                ", reviewerEmail='" + reviewerEmail + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                ", verified=" + verified +
                '}';
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setReviewer(String reviewer) {
        this.reviewer = reviewer;
    }

    public void setReviewerEmail(String reviewerEmail) {
        this.reviewerEmail = reviewerEmail;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public int getId() {
        return id;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getGetDateCreatedGmt() {
        return getDateCreatedGmt;
    }

    public int getProductId() {
        return productId;
    }

    public String getStatus() {
        return status;
    }

    public String getReviewer() {
        return reviewer;
    }

    public String getReviewerEmail() {
        return reviewerEmail;
    }

    public String getReview() {
        return review;
    }

    public int getRating() {
        return rating;
    }

    public boolean isVerified() {
        return verified;
    }
}
