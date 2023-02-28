package learn.wireless_guardian.data.mappers;

import learn.wireless_guardian.models.Site;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SiteMapper implements RowMapper<Site> {

    @Override
    public Site mapRow(ResultSet resultSet, int i) throws SQLException {
        Site site = new Site();
        site.setSiteId(resultSet.getInt("site_id"));
        site.setBusinessId(resultSet.getInt("business_id"));
        site.setResellerOrgId(resultSet.getInt("reseller_org_id"));
        site.setLatitude(resultSet.getFloat("latitude"));
        site.setLongitude(resultSet.getFloat("longitude"));
        site.setServicesSold(resultSet.getString("services_sold"));
        site.setRevenue(resultSet.getInt("revenue"));
        site.setMultipleSites(resultSet.getBoolean("multiple_sites"));
        return site;
    }
}
