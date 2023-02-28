package learn.wireless_guardian.models;

public class Site {

    private int siteId;
    private int businessId;
    private int resellerOrgId;
    private float latitude;
    private float longitude;
    private String servicesSold;
    private int revenue;
    private boolean multipleSites;

    public Site() {
    }

    public Site(int siteId, int businessId, int resellerOrgId, float latitude, float longitude, String servicesSold, int revenue, boolean multipleSites) {
        this.siteId = siteId;
        this.businessId = businessId;
        this.resellerOrgId = resellerOrgId;
        this.latitude = latitude;
        this.longitude = longitude;
        this.servicesSold = servicesSold;
        this.revenue = revenue;
        this.multipleSites = multipleSites;
    }

    public int getSiteId() {
        return siteId;
    }

    public void setSiteId(int siteId) {
        this.siteId = siteId;
    }

    public int getBusinessId() {
        return businessId;
    }

    public void setBusinessId(int businessId) {
        this.businessId = businessId;
    }

    public int getResellerOrgId() {
        return resellerOrgId;
    }

    public void setResellerOrgId(int resellerOrgId) {
        this.resellerOrgId = resellerOrgId;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }

    public String getServicesSold() {
        return servicesSold;
    }

    public void setServicesSold(String servicesSold) {
        this.servicesSold = servicesSold;
    }

    public int getRevenue() {
        return revenue;
    }

    public void setRevenue(int revenue) {
        this.revenue = revenue;
    }

    public boolean isMultipleSites() {
        return multipleSites;
    }

    public void setMultipleSites(boolean multipleSites) {
        this.multipleSites = multipleSites;
    }
}
