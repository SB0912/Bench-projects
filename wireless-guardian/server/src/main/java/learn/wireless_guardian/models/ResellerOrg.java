package learn.wireless_guardian.models;

import java.util.ArrayList;
import java.util.List;

public class ResellerOrg {

    private int resellerOrgId;
    private String name;

    private int parentOrgId;
    private int childOrgId;
    private List<SalesRep> salesReps = new ArrayList<>();
    private List<Site> sites = new ArrayList<>();

    public int getResellerOrgId() {
        return resellerOrgId;
    }

    public void setResellerOrgId(int resellerOrgId) {
        this.resellerOrgId = resellerOrgId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(int parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public int getChildOrgId() {
        return childOrgId;
    }

    public void setChildOrgId(int childOrgId) {
        this.childOrgId = childOrgId;
    }

    public List<SalesRep> getSalesReps() {
        return salesReps;
    }

    public void setSalesReps(List<SalesRep> salesReps) {
        this.salesReps = salesReps;
    }

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}
