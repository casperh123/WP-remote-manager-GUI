package Components;

import com.jsoniter.annotation.JsonCreator;
import com.jsoniter.annotation.JsonProperty;

public class Shipping {

    @JsonProperty("first_name")
    private String firstName;
    @JsonProperty("last_name")
    private String lastName;
    private String company;
    @JsonProperty("address_1")
    private String address1;
    @JsonProperty("address_2")
    private String address2;
    private String city;
    private String state;
    private String postcode;
    private String country;

    @JsonCreator
    public Shipping() {}

    public String toString() {
        return "Billing: [First name: " + firstName +
                " Last Name: " + lastName +
                " Company: " + company +
                " Adress 1: " + address1 +
                " Adress 2: " + address2 +
                " City: " + city +
                " State: " + state +
                " Postcode: " + postcode +
                " Country: " + country + "]";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public String getAdress1() {
        return address1;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public String getAddress2() {
        return address2;
    }

    public void setAdress2(String address2) {
        this.address2 = address2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
