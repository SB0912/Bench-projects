package learn.wireless_guardian.models;

public class SalesRep {

    private int salesRepId;
    private String firstName;
    private String lastName;
    private String city;
    private String state;
    private int resellerOrgId;

    public SalesRep() {
    }

    public SalesRep(int salesRepId, String firstName, String lastName, String city, String state, int resellerOrgId) {
        this.salesRepId = salesRepId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.city = city;
        this.state = state;
        this.resellerOrgId = resellerOrgId;
    }

    public int getSalesRepId() {
        return salesRepId;
    }

    public void setSalesRepId(int salesRepId) {
        this.salesRepId = salesRepId;
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

    public int getResellerOrgId() {
        return resellerOrgId;
    }

    public void setResellerOrgId(int resellerOrgId) {
        this.resellerOrgId = resellerOrgId;
    }
}
