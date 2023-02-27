package Components.Order;

import Components.Interfaces.ID;
import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class OrderNote implements ID {
    private int id;
    private String author;

    @JsonProperty("date_created")
    private String dateCreated;
    @JsonProperty("date_created_gmt")
    private String dateCreatedGmt;
    private String note;
    @JsonProperty("customer_note")
    private boolean customerNote;
    @JsonProperty("added_by_user")
    private boolean addedByUser;

    @JsonCreator
    public OrderNote() {
    }

    @Override
    public String toString() {
        return "OrderNote{" +
                "id=" + id +
                ", author='" + author + '\'' +
                ", dateCreated='" + dateCreated + '\'' +
                ", dateCreatedGmt='" + dateCreatedGmt + '\'' +
                ", note='" + note + '\'' +
                ", customerNote=" + customerNote +
                ", addedByUser=" + addedByUser +
                '}';
    }

    public void setCustomerNote(boolean customerNote) {
        this.customerNote = customerNote;
    }

    public void setAddedByUser(boolean addedByUser) {
        this.addedByUser = addedByUser;
    }

    public int getId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateCreated() {
        return dateCreated;
    }

    public String getDateCreatedGmt() {
        return dateCreatedGmt;
    }

    public String getNote() {
        return note;
    }

    public boolean isCustomerNote() {
        return customerNote;
    }

    public boolean isAddedByUser() {
        return addedByUser;
    }
}
