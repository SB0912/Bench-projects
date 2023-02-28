package learn.wireless_guardian.models;

import java.util.ArrayList;
import java.util.List;

public class Business {
    private int businessId;
    private String businessName;
    private String address;
    private String city;
    private String state;
    private int zipCode;
    private String contactEmail;
    private String contactPhone;
    private List<Site> sites = new ArrayList<>();

    public Business() {

    }

    public Business(int businessId, String businessName, String address, String city, String state, int zipCode, String contactEmail, String contactPhone) {
        this.businessId = businessId;
        this.businessName = businessName;
        this.address = address;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.contactEmail = contactEmail;
        this.contactPhone = contactPhone;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public int getZipCode() {
        return zipCode;
    }

    public void setZipCode(int zipCode) {
        this.zipCode = zipCode;
    }

    public String getContactEmail() {
        return contactEmail;
    }

    public void setContactEmail(String contactEmail) {
        this.contactEmail = contactEmail;
    }

    public String getContactPhone() {
        return contactPhone;
    }

    public void setContactPhone(String contactPhone) {
        this.contactPhone = contactPhone;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}
