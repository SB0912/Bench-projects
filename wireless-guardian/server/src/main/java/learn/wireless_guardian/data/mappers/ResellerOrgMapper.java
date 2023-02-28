package learn.wireless_guardian.data.mappers;

import learn.wireless_guardian.models.Business;
import learn.wireless_guardian.models.ResellerOrg;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResellerOrgMapper implements RowMapper<ResellerOrg> {

    @Override
    public ResellerOrg mapRow(ResultSet resultSet, int i) throws SQLException {
        ResellerOrg resellerOrg = new ResellerOrg();
        resellerOrg.setResellerOrgId(resultSet.getInt("reseller_org_id"));
        resellerOrg.setName(resultSet.getString("name"));
        resellerOrg.setParentOrgId(resultSet.getInt("parent_org_id"));
        resellerOrg.setChildOrgId(resultSet.getInt("child_org_id"));
        return resellerOrg;
    }
}
